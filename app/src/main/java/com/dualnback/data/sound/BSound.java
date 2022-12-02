package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.monkeyladder.R;

public class BSound extends Sound {

    public BSound( Context context ) {
        soundResource = R.raw.b;
        mediaPlayer = MediaPlayer.create( context, R.raw.b );
    }

    public BSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
