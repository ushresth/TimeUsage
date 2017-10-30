package com.startup.android.timeusage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

/**
 * Created by Utsah on 10/29/2017.
 */

public class SettingActivity extends AppCompatActivity {

   private ViewPager mViewPager;
   private Setting mSetting;

    public static Intent setting (Context packageContext, String setingId)
    {
        Intent intent = new Intent(packageContext, SettingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_log_pager);

         mViewPager = (ViewPager) findViewById(R.id.activity_log_pager_view_pager);

         mSetting = LogLab.get(this).getSetting();
         FragmentManager fragmentManager = getSupportFragmentManager();

         mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
             @Override
             public Fragment getItem(int position) {
                 String id = mSetting.getId();
                 return SettingFragment.newInstance(id);
             }

             @Override
             public int getCount() {
                 return 1;
             }
         });

         mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {

             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });
     }
}
