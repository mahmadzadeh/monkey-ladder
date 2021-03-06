package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.GameState;
import com.monkeyladder.game.PlayerLives;

import java.util.List;

interface MainActivityViewContract {

    void displayBoard( List<LocationData> board );

    void clearHighlightedCells( );

    void clearScreen( );

    void updateDisplayBoardProgressBar( int progress );

    void displayUserSelectionCorrectFeedback( );

    void displayUserSelectionIncorrectFeedback( );

    void updateUserInputFeedBackImage( );

    void updateGameStateInUI( GameState gameState );

    void setReadyToTakeUserInput( boolean isReady );

    void onGameEnd( );

    void updateLivesInUI( PlayerLives lives );
}
