package myshop.com.myshop.view;

import java.util.List;

import myshop.com.myshop.model.Item;
import myshop.com.myshop.model.ItemCategory;

/**
 * Created by Admin on 17/11/2018.
 * Interface to pass Product data from presenter to View.
 */

public interface ProductView {

    void showProducts(List<ItemCategory> products);
    void showSpinner(boolean state);
    void showFilteredItems(List<Item> itemList);
}

