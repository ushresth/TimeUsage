package com.startup.android.timeusage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Utsah on 10/29/2017.
 */

public class SettingFragment extends Fragment {

    private static final String ARG_SETTING_ID = "setting_id";
    private Setting mSetting;
    private EditText mNameField;
    private EditText mIdField;
    private EditText mEmailField;
    private EditText mGenderField;
    private EditText mCommentField;

    public static SettingFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SETTING_ID, id);

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetting = LogLab.get(getActivity()).getSetting();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogLab.get(getActivity())
                .updateSetting(mSetting);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        mNameField = (EditText)v.findViewById(R.id.user_name);
        mNameField.setText(mSetting.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSetting.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mIdField = (EditText)v.findViewById(R.id.user_id);
        mIdField.setText(mSetting.getId());
        mIdField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSetting.setId(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEmailField = (EditText)v.findViewById(R.id.user_email);
        mEmailField.setText(mSetting.getEmail());
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSetting.setEmail(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGenderField = (EditText)v.findViewById(R.id.user_gender);
        mGenderField.setText(mSetting.getGender());
        mGenderField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSetting.setGender(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCommentField = (EditText)v.findViewById(R.id.user_comment);
        mCommentField.setText(mSetting.getComment());
        mCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSetting.setComment(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
     {
         if (resultCode != Activity.RESULT_OK)
         {
             return;
         }
     }

}
