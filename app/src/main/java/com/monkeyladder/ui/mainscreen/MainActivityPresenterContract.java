package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;

import java.util.List;

public interface MainActivityPresenterContract {

    void collectSelectedLocation( Location location );

    void setDisplayGameBoardProgress( int formatTime );

    void startDisplayTimer( );

    void onDisplayTimerFinish( );

    void readyForDisplay( );
}
