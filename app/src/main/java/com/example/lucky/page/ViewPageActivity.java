package com.example.lucky.page;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lucky.mymessage.R;
import com.example.lucky.page.fragment.Fragment1;
import com.example.lucky.page.fragment.Fragment2;
import com.example.lucky.page.fragment.Fragment3;

public class ViewPageActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3;
    private TextView[] tvs;
    private ViewPager viewPage;
    private Fragment[] fragments = new Fragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        init();
    }

    private void init() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tvs = new TextView[]{tv1, tv2, tv3};
        viewPage = (ViewPager) findViewById(R.id.viewPage);
        fragments[0] = new Fragment1();
        fragments[1] = new Fragment2();
        fragments[2] = new Fragment3();
        setOnClick();
        viewPage.setAdapter(new FragmentPager(getSupportFragmentManager()));
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                selectView(position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void selectView(int num) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setEnabled(true);
        }
        tvs[num].setEnabled(false);
    }

    private void setOnClick() {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setTag(i);
            tvs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPage.setCurrentItem((Integer) view.getTag());
                    selectView((Integer)view.getTag());
                }
            });
        }
    }


    private class FragmentPager extends FragmentPagerAdapter {
        public FragmentPager(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
