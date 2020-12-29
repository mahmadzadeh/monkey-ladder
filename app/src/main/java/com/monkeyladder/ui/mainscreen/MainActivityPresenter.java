package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.GameState;
import com.monkeyladder.game.Location;
import com.monkeyladder.game.UserInputEvaluationResult;

class MainActivityPresenter implements MainActivityPresenterContract {

    private final MainActivityViewContract view;
    private final MainActivityModelContract model;
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
    public void startOneRound( ) {
        view.setReadyToTakeUserInput( false );

        view.displayBoard( model.getCellsThatAreSet() );

        startDisplayTimer();
    }

    public void startDisplayTimer( ) {
        timer.start();
    }

    @Override
    public void collectSelectedLocation( Location location ) {
        model.addSelectedLocation( location );

        if ( model.hasCollectedEnoughUserInput() ) {
            endOneRound();
        }
    }

    @Override
    public void setDisplayGameBoardProgress( int progress ) {
        view.updateDisplayBoardProgressBar( progress );
    }

    @Override
    public void onDisplayTimerFinish( ) {
        view.clearScreen();
        view.setReadyToTakeUserInput( true );
    }

    @Override
    public void endOneRound( ) {
        view.clearHighlightedCells();

        UserInputEvaluationResult result = model.evaluateUserInput();

        GameState gameState = model.updateGameState( result );

        view.updateGameStateInUI( gameState );

        model.resetGame();

        if ( UserInputEvaluationResult.Correct == result ) {
            view.displayUserSelectionCorrectFeedback();
        } else {
            view.displayUserSelectionIncorrectFeedback();
            view.updateLivesInUI( gameState.getLives() );
        }

        if ( model.isEndGame( result ) ) {
            view.onGameEnd();
        } else {
            startOneRound();
        }
    }

    @Override
    public GameState getCurrentGameState( ) {
        return model.getCurrentGameState();
    }
}
