package com.startup.android.timeusage;

import android.widget.Button;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Utsah on 10/7/2017.
 */

public class Log {
    private UUID mId;
    private String mTitle;
    private String mComment;
    private Date mDate;
    private String mActivityType;
    private String mDuration;
    private String mPlace;
    private Button mSave;
    private Button mCancel;
    private Button mDelete;


    public Log() {
        this(UUID.randomUUID());
    }

    public Log(UUID id)
    {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getActivityType() {
        return mActivityType;
    }

    public void setActivityType(String activityType) {
        mActivityType = activityType;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String place) {
        mPlace = place;
    }

    public Button getSave() {
        return mSave;
    }

    public void setSave(Button save) {
        mSave = save;
    }

    public Button getCancel() {
        return mCancel;
    }

    public void setCancel(Button cancel) {
        mCancel = cancel;
    }

    public Button getDelete() {
        return mDelete;
    }

    public void setDelete(Button delete) {
        mDelete = delete;
    }

    public String getPhotoFilename()
    {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
