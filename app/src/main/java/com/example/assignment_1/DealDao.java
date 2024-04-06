package com.example.assignment_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DealDao {

    @Insert
    void insertAll(Deal_Detail... deals);

    @Query("SELECT * FROM deal_detail")
    List<Deal_Detail> getAll();

    @Query("SELECT * FROM deal_detail WHERE id = :id")
    Deal_Detail getDealById(int id);

    @Query("SELECT * FROM deal_detail WHERE name = :name")
    Deal_Detail getDealByName(String name);

    @Query("SELECT * FROM deal_detail WHERE category = :category")
    List<Deal_Detail> getDealByCategory(String category);

    @Query("SELECT * FROM deal_detail WHERE star = :star")
    List<Deal_Detail> getDealByStar(double star);

    @Query("SELECT * FROM deal_detail WHERE Date = :Date")
    List<Deal_Detail> getDealByDate(String Date);

    @Query("SELECT * FROM deal_detail WHERE price = :price")
    List<Deal_Detail> getDealByPrice(double price);

    @Query("SELECT * FROM deal_detail WHERE discount = :discount")
    List<Deal_Detail> getDealByDiscount(double discount);

    @Query("SELECT * FROM deal_detail WHERE image_rsc = :image_rsc")
    List<Deal_Detail> getDealByImage(int image_rsc);

    @Delete
    void delete(Deal_Detail deal);

    @Update
    void update(Deal_Detail deal);
}
