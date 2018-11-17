package myshop.com.myshop.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.util.List;

import myshop.com.myshop.R;
import myshop.com.myshop.database.MyRoomDatabase;
import myshop.com.myshop.model.Item;
import myshop.com.myshop.model.ItemCategory;
import myshop.com.myshop.model.Product;
import myshop.com.myshop.network.ApiService;
import myshop.com.myshop.view.ProductView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Admin on 17/11/2018.
 * Presenter to reteive data from DB and pass it to view interface to be displayed through activity
 */

public class ProductPresenter {

    private ProductView productView;
    private Context mContext;
    private ApiService apiService;

    public void loadProductsFromServer() {
        apiService
                .getAPI()
                .getProducts()
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Product data = response.body();

                        if (data != null && data.getItemCategories() != null) {
                            DatabaseTaskRunnerToInsertInDB runner = new DatabaseTaskRunnerToInsertInDB();
                            runner.execute(data);
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        try {System.out.println(t.getMessage());
                            t.printStackTrace();
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /*
     * Intialise ProductPresenter instance and interface
     * @Param view : view through which to display the data in UI.
     * @Param ctx : activity instance passed from mainActivity class
     */
    public ProductPresenter(ProductView view, Context ctx) {
        this.productView = view;
        this.mContext = ctx;
        if (this.apiService == null) {
            this.apiService = new ApiService();
        }

    }

    public void showSpinner(boolean state){
        productView.showSpinner(state);
    }

    /*
     * initialise AsyncTask to retrieve all products from DB stored in Products table
     */
    public void loadProducts() {
        DatabaseTaskRunner runner = new DatabaseTaskRunner();
        runner.execute();
    }

    public void loadProductsBasedOnItemId(String id) {
        DatabaseTaskRunnerForId runner = new DatabaseTaskRunnerForId();
        String[] idStr =  {id};
        runner.setIds(idStr);
        runner.execute();
    }

//    public void loadProductsBasedOnCategory(String category){
//    DatabaseTaskRunnerForCategory runner = new DatabaseTaskRunnerForCategory();
//        runner.execute();
//    }

    /*
    * AsyncTask to retrieve all products from DB stored in Products table.
    */
    private class DatabaseTaskRunner extends AsyncTask<Void, Void, List<ItemCategory>> {

        @Override
        protected void onPostExecute(List<ItemCategory> itemList) {
            productView.showProducts(itemList);
        }

        @Override
        protected List<ItemCategory> doInBackground(Void... voids) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
            List<ItemCategory> categoriesList = myRoomDatabase.itemCategoriesDao().getAllItemcategories();
            return categoriesList;
        }
    }

    private class DatabaseTaskRunnerForId extends AsyncTask<Void, Void, List<Item>> {
           String [] ids =null;
           public void setIds(String[] ids){
               this.ids = ids;
    }
        @Override
        protected void onPostExecute(List<Item> itemList) {
            productView.showFilteredItems(itemList);
        }

        @Override
        protected List<Item> doInBackground(Void... voids) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
            List<Item> categoriesList = myRoomDatabase.itemDao().getProductsByItemId(this.ids);
            return categoriesList;
        }
    }
    /*
     * AsyncTask to insert the data loaded from server in database.
     */
    private class DatabaseTaskRunnerToInsertInDB extends AsyncTask<Product, String, String> {


        @Override
        protected String doInBackground(Product... params) {
            insertIntoDB(params[0]);
            return "";
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("DB result::"+result);
            loadProducts();

        }
    }

    private boolean insertIntoDB(Product product) {
        try {
            ItemCategory[] categoryList = product.getItemCategories();
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(this.mContext);
            for (int i = 0; i < categoryList.length; i++) {
                myRoomDatabase.itemCategoriesDao().insert(categoryList[i]);
                Item[] items = categoryList[i].getItems();
                for (int j = 0; j < items.length; j++) {
                    myRoomDatabase.itemDao().insert(items[j]);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;

        }
        return true;
    }

}
