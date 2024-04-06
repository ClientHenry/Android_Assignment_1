package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Home_Page extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
  //     searchViewAction();
        optionRecycler();
        dealListRecycler();
        bottomNavigation();

    }

    private void optionRecycler(){
        RecyclerView rc_option = findViewById(R.id.recycler_option);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        rc_option.setLayoutManager(lm);

        ArrayList<Option_Item> optionItems = new ArrayList<>();
        optionItems.add(new Option_Item("Top 5", R.drawable.option_star));
        optionItems.add(new Option_Item("Nature", R.drawable.option_nature));
        optionItems.add(new Option_Item("Gourmet", R.drawable.option_gourmet));
        optionItems.add(new Option_Item("Wonder", R.drawable.option_wonder));
        optionItems.add(new Option_Item("Relic", R.drawable.option_relic));
        rc_option.setAdapter(new OptionAdapter(optionItems));
    }

    private void dealListRecycler(){

        RecyclerView rc = findViewById(R.id.recycler_list);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Deal_List> deal_lists = new ArrayList<>();
        deal_lists.add(new Deal_List("Deal 1", "Category 1", R.drawable.greatwall, 4.5, "2021-10-01", 1000, 0.5));
        deal_lists.add(new Deal_List("Deal 2", "Category 2", R.drawable.harbin, 4.0, "2021-10-02", 2000, 0.3));
        deal_lists.add(new Deal_List("Deal 3", "Category 3", R.drawable.mounteverest, 3.5, "2021-10-03", 3000, 0.2));
        deal_lists.add(new Deal_List("Deal 4", "Category 4", R.drawable.yarlungtsangpo, 3.0, "2021-10-04", 4000, 0.1));
        deal_lists.add(new Deal_List("Deal 5", "Category 5", R.drawable.greatwall, 2.5, "2021-10-05", 5000, 0.0));

        rc.setAdapter(new Deal_List_Adapter(deal_lists));
    }

/*
    private void searchViewAction(){

        SearchView searchView = findViewById(R.id.searchView);
        TextView textView = findViewById(R.id.textView);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }*/

    private void bottomNavigation(){

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_1){

                    Intent intent                                                                                                                                                                                                                                                                                                                                                                                                                                                  = new Intent(Home_Page.this, Customer_Page.class);
                    startActivity(intent);


                    return true;
                }

                else if (item.getItemId() == R.id.item_2){

                    return true;
                }

                else
                    return false;
            }};
        nav.setOnItemSelectedListener(listener);
    }

}