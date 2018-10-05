/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.service.media.MediaBrowserService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mMediaPlayer.pause();
                        // rewind
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mMediaPlayer.start();
                    }
                }
            };


    private MediaPlayer.OnCompletionListener mCompletionListener = (mediaPlayer) -> {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        List<Word> words = new ArrayList<>();
        words.add( new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one) );
        words.add( new Word("Two", "Ottiko", R.drawable.number_two, R.raw.number_two) );
        words.add( new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three) );
        words.add( new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four) );
        words.add( new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five) );
        words.add( new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six) );
        words.add( new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven) );
        words.add( new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight) );
        words.add( new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine) );
        words.add( new Word("Ten", "na'aacha", R.drawable.number_ten,R.raw.number_ten) );

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <? > arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                //Log.d("NUM_MSG", arg1.toString() + " - " + position);
                Word selectedWord = words.get(position);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, selectedWord.getAudioResId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        // why helper method? consistent resource release logic within activity
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mAudioManager.abandonAudioFocus(afChangeListener);
            mMediaPlayer = null;
        }
    }

}
