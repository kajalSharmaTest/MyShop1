package myshop.com.myshop.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import myshop.com.myshop.model.ItemCategory;
import myshop.com.myshop.model.Product;

/**
 * Created by Admin on 17/11/2018.
 */

@Dao
public interface ItemCategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemCategory category);

    @Query("DELETE FROM ItemCategory")
    void deleteAll();

    @Query("SELECT * from ItemCategory")
    List<ItemCategory> getAllItemcategories();

    @Query("SELECT * from ItemCategory WHERE category IN(:catName)")
    List<ItemCategory> getProductsByCategoryName(String[] catName);

}
