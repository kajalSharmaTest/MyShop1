package myshop.com.myshop.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import myshop.com.myshop.model.Item;
import myshop.com.myshop.model.ItemCategory;
import myshop.com.myshop.model.Product;

/**
 * Created by Admin on 17/11/2018.
 */

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Query("DELETE FROM item")
    void deleteAll();

    @Query("SELECT * from item")
    List<Item> getAllItems();

    @Query("SELECT * from item WHERE id IN(:itemId)")
    List<Item> getProductsByItemId(String[] itemId);

    @Query("SELECT * from item WHERE item_desc IN(:itemDesc)")
    List<Item> getProductsByItemDesc(String[] itemDesc);

}
