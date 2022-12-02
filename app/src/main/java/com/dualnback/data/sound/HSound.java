package com.dualnback.data.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.monkeyladder.R;

public class HSound extends Sound {

    public HSound( Context context ) {
        soundResource = R.raw.h;
        mediaPlayer = MediaPlayer.create( context, R.raw.h );
    }

    public HSound( int resource ) {
        soundResource = resource;
    }

    @Override
    int getSoundResource( ) {
        return soundResource;
    }
}
