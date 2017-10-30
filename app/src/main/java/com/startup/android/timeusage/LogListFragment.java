package com.startup.android.timeusage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Utsah on 10/7/2017.
 */

public class LogListFragment extends Fragment {

    private RecyclerView mLogRecyclerView;
    private LogAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_list, container, false);
        mLogRecyclerView = (RecyclerView) view
                .findViewById(R.id.log_recycler_view);
        mLogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_log_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_log:
                Log log = new Log();
                LogLab.get(getActivity()).addLog(log);
                Intent intent = LogPagerActivity
                        .newIntent(getActivity(), log.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_setting:
                Setting setting = new Setting();
                Setting mSetting = LogLab.get(getActivity()).getSetting();

                Intent settingIntent = SettingActivity
                        .setting(getActivity(), mSetting.getId());
                startActivity(settingIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        LogLab logLab = LogLab.get(getActivity());
        List<Log> logs = logLab.getLogs();

        if (mAdapter == null) {
            mAdapter = new LogAdapter(logs);
            mLogRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setLogs(logs);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class LogHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Log mLog;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mPlaceTextView;

        public LogHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_log_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_log_date_text_view);
            mPlaceTextView = (TextView)
                    itemView.findViewById(R.id.list_item_place_text_view);
        }

        public void bindLog(Log log) {
            mLog = log;
            mTitleTextView.setText(mLog.getTitle());
            mDateTextView.setText(mLog.getDate().toString());
            mPlaceTextView.setText(mLog.getPlace());
        }

        @Override
        public void onClick(View v) {
            Intent intent = LogPagerActivity.newIntent(getActivity(), mLog.getId());
            startActivity(intent);
        }
    }

    private class LogAdapter extends RecyclerView.Adapter<LogHolder> {

        private List<Log> mLogs;

        public LogAdapter(List<Log> logs) {
            mLogs = logs;
        }

        @Override
        public LogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_log, parent, false);
            return new LogHolder(view);
        }
        @Override
        public void onBindViewHolder(LogHolder holder, int position) {
            Log log = mLogs.get(position);
            holder.bindLog(log);
        }

        @Override
        public int getItemCount() {
            return mLogs.size();
        }

        public void setLogs(List<Log> logs) {
            mLogs = logs;
        }
    }
}
