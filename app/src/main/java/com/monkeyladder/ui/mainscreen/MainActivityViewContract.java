package com.monkeyladder.ui.mainscreen;

import java.util.List;

interface MainActivityViewContract {

    void display( List<LocationData> board );

    void updateDisplayBoardProgressBar( int progress );


    void clearScreen( );
}
