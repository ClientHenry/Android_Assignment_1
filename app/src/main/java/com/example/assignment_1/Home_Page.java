package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Home_Page extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        searchViewAction();
        optionRecycler();

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

    }
}