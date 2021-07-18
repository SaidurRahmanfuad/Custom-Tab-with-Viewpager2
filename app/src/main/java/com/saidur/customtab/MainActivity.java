package com.saidur.customtab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //আমাদের যা যা লাগবে Tab Layout,TabAdapter,ViewPager2,FragmentManager,TabLayoutMediator method
    TabAdapter tabAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentManager fm;

    TextView tabtag, tabitemcounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager2);
        fm = getSupportFragmentManager();
        tabAdapter = new TabAdapter(fm, getLifecycle());
        viewPager2.setAdapter(tabAdapter);

        prepareTab();
    }

    public void prepareTab() {

        final List<String> colors = new ArrayList<String>() {
            {
                add("#DADADA");
                add("#FFFFFF");
                add("#FFFFFF");
                add("#FFFFFF");
            }
        };
        //যেহেতু আমরা TabLayout লেয়াউটের সাথে ViewPager2 Bind করবো তাই parameter হিসেবে (tabLayout, viewPager2)দিবো ।
        //TabLayoutMediator Method এর মাধ্যমে আমাদের তৈরি করা CustomLayout Bind করবো এবং তাতে Value set করবো।
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View tabView = layoutInflater.inflate(R.layout.custom_tab_items, null, false);

                tabtag = (TextView) tabView.findViewById(R.id.tabtagz);
                tabitemcounts = (TextView) tabView.findViewById(R.id.tabitemcounts);
                //আমাদের CustomLayout টা setCustomView এ Method সেট করবো । তাহলে Tab এর সাথে আমাদের CustomLayout সেট হয়ে গেলো ।
                tab.setCustomView(tabView);

                tab.view.setBackgroundColor(Color.parseColor(colors.get(position)));

                //এবার চেক করি যদি Tab এর position কত হলে কি কি ডাটা সেট করবো ।
                switch (position) {
                    case 0:
                        tabtag.setText("ME");
                        tabitemcounts.setText("70");
                        break;
                    case 1:
                        tabtag.setText("You");
                        tabitemcounts.setText("30");

                        break;
                    case 2:
                        tabtag.setText("Done");
                        tabitemcounts.setText("100");
                        break;

                }
            }
        }).attach();

        //Tab এ  Click করলে বা Tab Select করলে Tab UI তে কি কি Change হবে তা set করেছি ।।
        prepareTabClick_with_Viewpager();
    }

    private void prepareTabClick_with_Viewpager() {


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                final List<String> colors = new ArrayList<String>() {
                    {
                        add("#DADADA");
                        add("#F0FBF6");
                        add("#EEF8FA");

                    }
                };

                if (tab.getPosition() == 0) {
                    tab.view.setBackgroundColor(Color.parseColor(colors.get(0)));
                } else {
                    tab.view.setBackgroundColor(Color.parseColor(colors.get(tab.getPosition())));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                final List<String> white = new ArrayList<String>() {
                    {
                        add("#FFFFFF");
                        add("#FFFFFF");
                        add("#FFFFFF");
                    }
                };
                tab.view.setBackgroundColor(Color.parseColor(white.get(tab.getPosition())));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

         //Tab এ Click করলে যা করবে তা পাওয়ার জন্ন ViewPager2 তে এই Method এড করবো ।
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}