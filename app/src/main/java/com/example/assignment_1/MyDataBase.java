package com.example.assignment_1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Supplier.class, Customer.class, Deal.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract SupplierDao supplierDao();
    public abstract CustomerDao customerDao();

    public abstract DealDao dealDao();

    private static volatile MyDataBase INSTANCE;

    public static MyDataBase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (MyDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static MyDataBase buildDatabase(Context context){
        return Room.databaseBuilder(context, MyDataBase.class, "MyDataBase").allowMainThreadQueries().build();
    }
}
