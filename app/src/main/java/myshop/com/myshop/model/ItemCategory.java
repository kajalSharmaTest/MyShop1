package myshop.com.myshop.model;

/**
 * Created by Admin on 17/11/2018.
 */


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import myshop.com.myshop.util.MyTypeConverter;
@Entity
public class ItemCategory {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category")
    private String category;

    @NonNull
    @ColumnInfo(name = "items")
    @TypeConverters(MyTypeConverter.class)
    private Item[] items = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

}