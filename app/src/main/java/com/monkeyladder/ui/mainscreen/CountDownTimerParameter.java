package com.monkeyladder.ui.mainscreen;

class CountDownTimerParameter {

    private final long timerDurationInMillis;
    private final long oneTickInMillis;

    public CountDownTimerParameter( long timerDurationInMillis, long oneTickInMillis ) {
        this.timerDurationInMillis = timerDurationInMillis;
        this.oneTickInMillis = oneTickInMillis;
    }

    public static CountDownTimerParameter withDefaultValues( ) {
        return  new CountDownTimerParameter( 1000, 500 );
    }

    public static CountDownTimerParameter of( long timerDurationInMillis, long oneTickInMillis ) {
        return  new CountDownTimerParameter( timerDurationInMillis, oneTickInMillis );
    }

    public long getTimerDurationInMillis( ) {
        return timerDurationInMillis;
    }

    public long getOneTickInMillis( ) {
        return oneTickInMillis;
    }
}
