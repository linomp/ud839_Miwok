package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        List<Word> words = new ArrayList<>();
        words.add( new Word("father", "әpә", R.drawable.family_father, R.raw.family_father) );
        words.add( new Word("mother", "әṭa",R.drawable.family_mother, R.raw.family_mother) );
        words.add( new Word("son", "angsi",R.drawable.family_son, R.raw.family_son) );
        words.add( new Word("daughter", "tune",R.drawable.family_daughter, R.raw.family_daughter) );
        words.add( new Word("older brother", "taachi",R.drawable.family_older_brother, R.raw.family_older_brother) );
        words.add( new Word("younger brother", "chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother) );
        words.add( new Word("older sister", "teṭe",R.drawable.family_older_sister, R.raw.family_older_sister) );
        words.add( new Word("younger sister", "kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister) );
        words.add( new Word("grandmother", "ama",R.drawable.family_grandmother, R.raw.family_grandmother) );
        words.add( new Word("grandfather", "paapa",R.drawable.family_grandfather, R.raw.family_grandfather) );

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
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
                    mMediaPlayer = MediaPlayer.create(getActivity(), selectedWord.getAudioResId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
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
