package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;
import com.monkeyladder.game.UserInputEvaluationResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MainActivityPresenter implements MainActivityPresenterContract {

    private final MainActivityViewContract view;
    private final MainActivityModelContract model;
    private final List<Location> selectedLocations = new ArrayList<>();
    private final GameCountDownTimer timer;
    private int count = 10;

    public MainActivityPresenter( MainActivityViewContract viewContract, MainActivityModelContract model,
                                  CountDownTimerParameter countDownTimerParam ) {
        this.view = viewContract;
        this.model = model;
        this.timer = GameCountDownTimer.INSTANCE( this,
                countDownTimerParam.getTimerDurationInMillis(),
                countDownTimerParam.getOneTickInMillis() );
    }

    @Override
    public void addSelectedLocation( Location location ) {
        model.addSelectedLocation( location );

        if ( model.hasCollectedEnoughUserInput() ) {

            view.clearHighlightedCells();

            UserInputEvaluationResult result = model.evaluateUserInput();

            if ( UserInputEvaluationResult.Correct == result ) {
                view.displayUserSelectionCorrectFeedback();
            } else {
                view.displayUserSelectionIncorrectFeedback();
            }

            // clear the feedback image
            view.updateUserInputFeedBackImage();

            model.updateGameState(result);
        }
    }

    @Override
    public List<Location> getSelectedLocations( ) {
        return Collections.unmodifiableList( this.selectedLocations );
    }

    @Override
    public List<LocationData> getCurrentBoard( ) {

        List<LocationData> cellsThatAreSet = model.getCellsThatAreSet();

        return cellsThatAreSet;
    }

    @Override
    public void setDisplayGameBoardProgress( String formatTime ) {
        view.updateDisplayBoardProgressBar( count += 10 );
    }

    public void startDisplayTimer( ) {
        timer.start();
    }

    @Override
    public void endDisplayTimer( ) {
        view.clearScreen();
    }

    @Override
    public void readyForDisplay( ) {
        List<LocationData> cellsThatAreSet = model.getCellsThatAreSet();

        view.displayBoard( cellsThatAreSet );
    }
}
