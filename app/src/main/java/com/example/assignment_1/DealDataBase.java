package com.example.assignment_1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Deal_Detail.class}, version = 1, exportSchema = false)
public abstract class DealDataBase extends RoomDatabase {

    public abstract DealDao dealDao();
    private static volatile DealDataBase INSTANCE;

    public static DealDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DealDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static DealDataBase buildDatabase(Context context) {
        return Room.databaseBuilder(context, DealDataBase.class, "deal_database").allowMainThreadQueries().build();
    }
}
