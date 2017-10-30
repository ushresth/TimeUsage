package com.startup.android.timeusage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Utsah on 10/7/2017.
 */

public class LogFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_LOG_ID = "log_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 1;

    private Log mLog;
    private File mPhotoFile;
    private EditText mTitleField;
    private EditText mCommentField;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Button mDateButton;
    private Spinner mActivityTypeSpinner;
    private EditText mDurationField;
    private EditText mPlaceField;
    private Button mSaveButton;
    private Button mCancelButton;
    private Button mDeleteButton;


    public static LogFragment newInstance(UUID logId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LOG_ID, logId);
        LogFragment fragment = new LogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID logId = (UUID) getArguments().getSerializable(ARG_LOG_ID);
        mLog = LogLab.get(getActivity()).getLog(logId);
        mPhotoFile = LogLab.get(getActivity()).getPhotoFile(mLog);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mLog.getTitle() != null) {
            LogLab.get(getActivity())
                    .updateLog(mLog);
        }
        else
        {
            LogLab.get(getActivity())
                    .deleteLog(mLog);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        mTitleField = (EditText) v.findViewById(R.id.log_title);
        mTitleField.setText(mLog.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mLog.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCommentField = (EditText) v.findViewById(R.id.log_comment);
        mCommentField.setText(mLog.getComment());
        mCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mLog.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.log_date);
        updateDate();
        mDateButton.setText(mLog.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mLog.getDate());
                dialog.setTargetFragment(LogFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        PackageManager packageManager = getActivity().getPackageManager();

        mPhotoButton = (ImageButton) v.findViewById(R.id.activity_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.activity_photo);
        updatePhotoView();

        mActivityTypeSpinner = (Spinner) v.findViewById(R.id.activity_type_spinner);

        mActivityTypeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.log_activity_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mActivityTypeSpinner.setAdapter(adapter);
        if (mLog.getActivityType() != null)
            mActivityTypeSpinner.setSelection(Integer.parseInt(mLog.getActivityType()));

        mDurationField = (EditText) v.findViewById(R.id.activity_duration);
        mDurationField.setText(mLog.getDuration());
        mDurationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLog.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlaceField = (EditText) v.findViewById(R.id.activity_place);
        mPlaceField.setText(mLog.getPlace());
        mPlaceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLog.setPlace(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSaveButton = (Button) v.findViewById(R.id.log_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LogLab.get(getActivity()).updateLog(mLog);
                getActivity().onBackPressed();
            }
        });

        mCancelButton = (Button) v.findViewById(R.id.log_cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v)
              {
                  LogLab.get(getActivity());
                  getActivity().onBackPressed();
              }
         });

        mDeleteButton = (Button) v.findViewById(R.id.log_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogLab.get(getActivity()).deleteLog(mLog);
                getActivity().onBackPressed();
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mLog.setDate(date);
            updateDate();
            mDateButton.setText(mLog.getDate().toString());
        }
        else if (requestCode == REQUEST_PHOTO)
        {
            updatePhotoView();
        }
    }

    private void updateDate()
    {
        mDateButton.setText(mLog.getDate().toString());
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       mLog.setActivityType(Integer.toString(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
