package com.startup.android.timeusage;

import java.util.UUID;

/**
 * Created by Utsah on 10/29/2017.
 */

public class Setting {
    private String mName;
    private String mId;
    private String mEmail;
    private String mGender;
    private String mComment;

    public Setting()
     {}

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }
    public void setEmail(String email) {
        mEmail = email;
    }

    public String getGender() {
        return mGender;
    }
    public void setGender(String gender) {
        mGender = gender;
    }

    public String getComment() {
        return mComment;
    }
    public void setComment(String comment) {
        mComment = comment;
    }
}
