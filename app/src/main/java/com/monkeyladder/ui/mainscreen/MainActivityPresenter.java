package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;
import com.monkeyladder.game.UserInputEvaluationResult;

import java.util.ArrayList;
import java.util.List;

class MainActivityPresenter implements MainActivityPresenterContract {

    private final MainActivityViewContract view;
    private final MainActivityModelContract model;
    private final List<Location> selectedLocations = new ArrayList<>();
    private final GameCountDownTimer timer;

    public MainActivityPresenter( MainActivityViewContract viewContract, MainActivityModelContract model,
                                  CountDownTimerParameter countDownTimerParam ) {
        this.view = viewContract;
        this.model = model;
        this.timer = GameCountDownTimer.INSTANCE( this,
                countDownTimerParam.getTimerDurationInMillis(),
                countDownTimerParam.getOneTickInMillis() );
    }

    @Override
    public void collectSelectedLocation( Location location ) {
        model.addSelectedLocation( location );

        if ( model.hasCollectedEnoughUserInput() ) {
            handleActionsForEndOfOneRound();
        }
    }

    @Override
    public void setDisplayGameBoardProgress( int progress ) {
        view.updateDisplayBoardProgressBar( progress );
    }

    public void startDisplayTimer( ) {
        timer.start();
    }

    @Override
    public void onDisplayTimerFinish( ) {
        view.clearScreen();
        view.setReadyToTakeUserInput(true);
    }

    @Override
    public void readyForDisplay( ) {
        view.setReadyToTakeUserInput( false );

        view.displayBoard( model.getCellsThatAreSet() );

        startDisplayTimer();
    }

    private void handleActionsForEndOfOneRound( ) {
        view.clearHighlightedCells();

        UserInputEvaluationResult result = model.evaluateUserInput();
        view.updateGameStateInUI( model.updateGameState( result ) );

        model.resetGame();

        if ( UserInputEvaluationResult.Correct == result ) {
            view.displayUserSelectionCorrectFeedback();
        } else {
            view.displayUserSelectionIncorrectFeedback();
        }

        readyForDisplay();
    }
}
