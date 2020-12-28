package com.monkeyladder.ui.mainscreen;

class CountDownTimerParameter {

    private final int timerDurationInMillis;
    private final int oneTickInMillis;

    public CountDownTimerParameter( int timerDurationInMillis, int oneTickInMillis ) {
        this.timerDurationInMillis = timerDurationInMillis;
        this.oneTickInMillis = oneTickInMillis;
    }

    public static CountDownTimerParameter withDefaultValues( ) {
        return  new CountDownTimerParameter( 1000, 500 );
    }

    public static CountDownTimerParameter of( int timerDurationInMillis, int oneTickInMillis ) {
        return  new CountDownTimerParameter( timerDurationInMillis, oneTickInMillis );
    }

    public int getTimerDurationInMillis( ) {
        return timerDurationInMillis;
    }

    public int getOneTickInMillis( ) {
        return oneTickInMillis;
    }
}
