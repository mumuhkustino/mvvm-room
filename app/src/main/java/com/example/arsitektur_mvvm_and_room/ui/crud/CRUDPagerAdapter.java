package com.example.arsitektur_mvvm_and_room.ui.crud;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.arsitektur_mvvm_and_room.ui.crud.delete.DeleteFragment;
import com.example.arsitektur_mvvm_and_room.ui.crud.insert.InsertFragment;
import com.example.arsitektur_mvvm_and_room.ui.crud.select.SelectFragment;
import com.example.arsitektur_mvvm_and_room.ui.crud.update.UpdateFragment;

public class CRUDPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public CRUDPagerAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InsertFragment.newInstance();
            case 1:
                return SelectFragment.newInstance();
            case 2:
                return UpdateFragment.newInstance();
            case 3:
                return DeleteFragment.newInstance();
            default:
                return null;
        }
    }

    public void setCount(int tabCount) {
        this.tabCount = tabCount;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}