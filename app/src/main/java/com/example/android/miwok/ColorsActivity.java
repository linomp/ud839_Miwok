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

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        List<Word> words = new ArrayList<>();
        words.add( new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red) );
        words.add( new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green) );
        words.add( new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown) );
        words.add( new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray) );
        words.add( new Word("black", "kululli", R.drawable.color_black, R.raw.color_black) );
        words.add( new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white) );
        words.add( new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow) );
        words.add( new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow) );

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <? > arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                //Log.d("NUM_MSG", arg1.toString() + " - " + position);
                //MediaPlayer mediaPlayer = MediaPlayer.create(NumbersActivity.this, R.raw.number_one);
                Word selectedWord = words.get(position);
                MediaPlayer mediaPlayer = MediaPlayer.create(ColorsActivity.this, selectedWord.getAudioResId());
                mediaPlayer.start();
            }
        });
    }
}
