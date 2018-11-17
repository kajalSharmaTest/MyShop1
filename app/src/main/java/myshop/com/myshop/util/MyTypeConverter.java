package myshop.com.myshop.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import myshop.com.myshop.model.Item;
import myshop.com.myshop.model.ItemCategory;

/**
 * Created by Admin on 17/11/2018.
 */

public class MyTypeConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static String[] stringToSomeObjectList(String data) {
        if (data == null) {
            return new String[0];
        }

        Type listType = new TypeToken<String[]>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static ArrayList stringToArrayList(String data) {
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<List<ArrayList>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(ItemCategory[] someObjects) {
        return gson.toJson(someObjects);
    }


    @TypeConverter
    public ItemCategory[] toItemcategoryArray(String category) {
        if (category == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ItemCategory[]>() {
        }.getType();
        ItemCategory[] product_array = gson.fromJson(category, type);
        return product_array;
    }

    @TypeConverter
    public static String someObjectListToString(Item[] someObjects) {
        return gson.toJson(someObjects);
    }


    @TypeConverter
    public Item[] toItemArray(String item) {
        if (item == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Item[]>() {
        }.getType();
        Item[] item_array = gson.fromJson(item, type);
        return item_array;
    }


    @TypeConverter
    public static String someObjectListToString(String[] someObjects) {
        return gson.toJson(someObjects);
    }

}
