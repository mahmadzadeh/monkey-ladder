package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;

import java.util.List;

public interface MainActivityPresenterContract {

    void addSelectedLocation( Location location );

    List<Location> getSelectedLocations( );

    List<LocationData> getCurrentBoard( );

    void setDisplayGameBoardProgress( String formatTime );

    void startDisplayTimer( );

    void endDisplayTimer( );

    void readyForDisplay( );
}
