package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;

import java.util.List;

public interface MainActivityPresenterContract {

    void addSelectedLocation( Location location );

    List<Location> getSelectedLocations( );

    List<LocationData> getCurrentBoard( );

    void setDisplayGameBoardProgress( int formatTime );

    void startDisplayTimer( );

    void onDisplayTimerFinish( );

    void readyForDisplay( );
}
