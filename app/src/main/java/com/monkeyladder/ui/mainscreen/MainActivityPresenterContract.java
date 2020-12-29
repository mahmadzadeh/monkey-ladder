package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.GameState;
import com.monkeyladder.game.Location;

public interface MainActivityPresenterContract {

    void startOneRound( );

    void startDisplayTimer( );

    void onDisplayTimerFinish( );

    void setDisplayGameBoardProgress( int formatTime );

    void collectSelectedLocation( Location location );

    void endOneRound( );

    GameState getCurrentGameState( );
}
