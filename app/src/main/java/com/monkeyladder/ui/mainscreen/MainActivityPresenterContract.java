package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;

import java.util.List;

public interface MainActivityPresenterContract {

    void startOneRound( );

    void startDisplayTimer( );

    void onDisplayTimerFinish( );

    void setDisplayGameBoardProgress( int formatTime );

    void collectSelectedLocation( Location location );

    void endOneRound( );
}
