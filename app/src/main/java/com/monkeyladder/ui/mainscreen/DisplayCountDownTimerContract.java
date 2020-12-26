package com.monkeyladder.ui.mainscreen;

public interface DisplayCountDownTimerContract {

    void onTick( long millisUntilFinished );

    void onFinish( );

    void start( );

    void onPause( );
}
