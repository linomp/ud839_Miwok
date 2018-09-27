package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResId;
    private boolean mHasImage;
    private int mAudioResId;

    public Word(String defaultTranslation, String miwokTranslation, int imageResId, int audioResId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResId = imageResId;
        mHasImage = true;
        mAudioResId = audioResId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioResId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mHasImage = false;
        mAudioResId = audioResId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResId() {
        return mImageResId;
    }
    
    public int getAudioResId() {
        return mAudioResId;
    }

    public boolean hasImage(){return mHasImage;} 
}
