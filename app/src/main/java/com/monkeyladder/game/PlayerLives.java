package com.monkeyladder.game;

public enum  PlayerLives {
    Zero(0),
    One(1),
    Two(2),
    Three(3),
    Default(3);

    int lifeCount;
    PlayerLives( int i ) {
        lifeCount =i;
    }

    public static PlayerLives fromLifeCount(int count ){
        for ( PlayerLives lives: PlayerLives.values() ) {
            if(lives.lifeCount == count ){
                return lives;
            }
        }

        throw new IllegalArgumentException(String.format( "invalid life count give %s", count ));
    }
}
