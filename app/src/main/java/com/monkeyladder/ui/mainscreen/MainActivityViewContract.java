package com.monkeyladder.ui.mainscreen;

import java.util.List;

interface MainActivityViewContract {

    void displayBoard( List<LocationData> board );

    void updateDisplayBoardProgressBar( int progress );

    void clearScreen( );

    void clearHighlightedCells( );

    void displayUserSelectionCorrectFeedback( );

    void displayUserSelectionIncorrectFeedback( );

    void updateUserInputFeedBackImage( );
}
