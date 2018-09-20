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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        List<Word> words = new ArrayList<>();
        words.add( new Word("One", "Lutti") );
        words.add( new Word("Two", "Ottiko") );
        words.add( new Word("Three", "tolookosu") );
        words.add( new Word("Four", "oyyisa") );
        words.add( new Word("Five", "massokka") );
        words.add( new Word("Six", "temmokka") );
        words.add( new Word("Seven", "kenekaku") );
        words.add( new Word("Eight", "kawinta") );
        words.add( new Word("Nine", "wo'e") );
        words.add( new Word("Ten", "na'aacha") );

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
