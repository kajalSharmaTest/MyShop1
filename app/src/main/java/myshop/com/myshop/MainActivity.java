package myshop.com.myshop;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import myshop.com.myshop.adapter.ProductDataAdapter;
import myshop.com.myshop.model.Item;
import myshop.com.myshop.model.ItemCategory;
import myshop.com.myshop.model.Product;
import myshop.com.myshop.presenter.ProductPresenter;
import myshop.com.myshop.view.ProductView;

public class MainActivity extends AppCompatActivity implements ProductView , SearchView.OnQueryTextListener{
    private ProductPresenter mProductPresenter;
    private ProductView mProductView;
    RecyclerView recyclerView;
    LinearLayout ll_no_prod;
    LinearLayout ll_prod_list;
    private Toolbar tbMainSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProductView = this;
        tbMainSearch = (Toolbar)findViewById(R.id.tb_toolbarsearch);
        mProductPresenter = new ProductPresenter(this,this);
        mProductPresenter.showSpinner(true);
        mProductPresenter.loadProductsFromServer();
         ll_prod_list =(LinearLayout)findViewById(R.id.prod_list_container);
         ll_no_prod =(LinearLayout)findViewById(R.id.no_prod_container);
         recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
    }

    @Override
    public void showProducts(List<ItemCategory> mCategoryList){
        ArrayList<Item> mItemList = new ArrayList<>();
        if(mCategoryList!=null && mCategoryList.size()>0) {
            ll_prod_list.setVisibility(View.VISIBLE);
            ll_no_prod.setVisibility(View.GONE);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layoutManager);
            for(int i=0;i<mCategoryList.size();i++){
                for(int j=0;j<mCategoryList.get(i).getItems().length;j++) {
                    mItemList.add(mCategoryList.get(i).getItems()[j]);
                }
            }
            ProductDataAdapter adapter = new ProductDataAdapter(this, mItemList);
            recyclerView.setAdapter(adapter);
        } else {
            ll_prod_list.setVisibility(View.GONE);
            ll_no_prod.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showFilteredItems(List<Item> itemList){
        ArrayList<Item> mItemList = new ArrayList<>();
        if(itemList!=null && itemList.size()>0) {
            ll_prod_list.setVisibility(View.VISIBLE);
            ll_no_prod.setVisibility(View.GONE);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layoutManager);
            mItemList.addAll(itemList);
            ProductDataAdapter adapter = new ProductDataAdapter(this, mItemList);
            recyclerView.setAdapter(adapter);
        } else {
            ll_prod_list.setVisibility(View.GONE);
            ll_no_prod.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem mSearchmenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearchmenuItem.getActionView();
        searchView.setQueryHint("enter item id");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query ==null ||  query.isEmpty() ){
            mProductPresenter.loadProducts();
        } else {
            mProductPresenter.loadProductsBasedOnItemId(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText ==null ||  newText.isEmpty() ){
            mProductPresenter.loadProducts();
        } else {
            mProductPresenter.loadProductsBasedOnItemId(newText);
        }
        return true;
    }

    @Override
    public void showSpinner(boolean state) {

    }
}
