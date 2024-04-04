package com.example.assignment_1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class View_Pager_Adapter extends FragmentPagerAdapter {

    public View_Pager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 1)
            return new View_Pager_Fragment1();

        else
            return new View_Pager_Fragment2();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
