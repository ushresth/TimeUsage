package com.startup.android.timeusage;

import android.support.v4.app.Fragment;

/**
 * Created by Utsah on 10/7/2017.
 */

public class LogListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new LogListFragment();
    }
}
