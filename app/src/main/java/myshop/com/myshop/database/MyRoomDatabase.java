package myshop.com.myshop.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import myshop.com.myshop.model.Item;
import myshop.com.myshop.model.ItemCategory;
import myshop.com.myshop.model.Product;

/**
 * Created by Kajal on 9/17/2018.
 * Main databse class to maintain database instance
 */

@Database(entities ={
        ItemCategory.class,
        Item.class
        }, version = 1,exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

        public  abstract ItemCategoriesDao itemCategoriesDao();
        public  abstract ItemDao itemDao();

        private static volatile MyRoomDatabase INSTANCE;

        public static MyRoomDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (MyRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                MyRoomDatabase.class, "mdatabase")
                                .build();
                    }
                }
            }
            return INSTANCE;
        }


    }
