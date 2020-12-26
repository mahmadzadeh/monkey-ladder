package com.monkeyladder.ui.mainscreen;

import android.os.CountDownTimer;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

public class GameCountDownTimer implements DisplayCountDownTimerContract {

    private final MainActivityPresenterContract presenter;
    private final long oneTickDurationMillis;
    private long showTimeWindowLengthInMillis;
    private CountDownTimer timer;

    private GameCountDownTimer( MainActivityPresenterContract presenter,
                                long showTimeWindowLengthInMillis,
                                long oneTickDurationMillis ) {
        this.presenter = presenter;
        this.showTimeWindowLengthInMillis = showTimeWindowLengthInMillis;
        this.oneTickDurationMillis = oneTickDurationMillis;
    }

    public static GameCountDownTimer INSTANCE( MainActivityPresenterContract presenter,
                                               long singleGameLengthMillis,
                                               long oneTickDurationMillis ) {

        return new GameCountDownTimer( presenter, singleGameLengthMillis, oneTickDurationMillis );
    }

    public static String formatTime( long millisUntilFinished ) {
        return String.format( "%02d:%02d",
                MILLISECONDS.toMinutes( millisUntilFinished ) - HOURS.toMinutes( MILLISECONDS.toHours( millisUntilFinished ) ),
                MILLISECONDS.toSeconds( millisUntilFinished ) - MINUTES.toSeconds( MILLISECONDS.toMinutes( millisUntilFinished ) ) );
    }

    @Override
    public void start( ) {
        this.timer = createTimer( this );
        this.timer.start();
    }

    @Override
    public void onTick( long millisUntilFinished ) {
        presenter.setDisplayGameBoardProgress( formatTime( millisUntilFinished ) );
    }

    @Override
    public void onFinish( ) {
        presenter.endDisplayTimer();
    }

    @Override
    public void onPause( ) {
        if ( timer != null ) {
            timer.cancel();
        }
    }

    private CountDownTimer createTimer( final GameCountDownTimer gameCountDownTimer ) {

        CountDownTimer countDownTimer = new CountDownTimer( this.showTimeWindowLengthInMillis, this.oneTickDurationMillis ) {
            @Override
            public void onTick( long millisUntilFinished ) {
                gameCountDownTimer.onTick( millisUntilFinished );
            }

            @Override
            public void onFinish( ) {
                gameCountDownTimer.onFinish();
            }
        };

        return countDownTimer;
    }

}
