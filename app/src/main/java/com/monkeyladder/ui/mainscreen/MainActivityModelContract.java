package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;
import com.monkeyladder.game.UserInputEvaluationResult;

import java.util.List;

interface MainActivityModelContract {

    List<LocationData> getCellsThatAreSet( );

    void addSelectedLocation( Location location );

    boolean hasCollectedEnoughUserInput( );

    UserInputEvaluationResult evaluateUserInput( );

    void updateGameState( UserInputEvaluationResult result );
}
