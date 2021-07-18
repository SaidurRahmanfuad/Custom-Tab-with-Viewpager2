package com.saidur.customtab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter {


    // এখানে যেহেতু আমি viewpager2 use করবো তাই FragmentStateAdapter কে extend করেছি ।
    //টেব এডাপটার এর জন্ন FragmentStateAdapter এক্সটেনড করবে অথবা PagerAdapter;FragmentPagerAdapter deprecated হয়েগিয়েছে
    public TabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        // এখানে আমি টেবের জনন ফ্রাগমেন্ট পজিশান মত সেট করে দিবো ।
        switch (position)
        {
            case 1: return new Frag2();
            case 2: return new Frag3();
        }
        //এটা শুরুতে থাকবে ০ পজিশনে ।
         return new Frag1();
    }

    @Override
    public int getItemCount() {
        //এখানে মোট কয়টি টেব দেখাতে চাই তার সংখা লিখবো ।
        return 3;
    }
}
