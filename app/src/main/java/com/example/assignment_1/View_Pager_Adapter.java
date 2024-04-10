package com.example.assignment_1;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class View_Pager_Adapter extends FragmentPagerAdapter {

    private int product_id;
    private Context mContext;

    public View_Pager_Adapter(FragmentManager fm, int product_id, Context context) {
        super(fm);
        this.product_id = product_id;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        View_Pager_Fragment view_pager_fragment = new View_Pager_Fragment();

        Product x = MyDataBase.getInstance(mContext).productDao().getProduct(product_id);

        if(position == 0){
            view_pager_fragment.setImage(x.getImage_1());

        }
        else if(position == 1){
            view_pager_fragment.setImage(x.getImage_2());
        }
        else if(position == 2){
            view_pager_fragment.setImage(x.getImage_3());
        }
        else{
            view_pager_fragment.setImage(x.getImage_4());
        }

        return view_pager_fragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}
