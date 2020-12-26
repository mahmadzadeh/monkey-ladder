package com.monkeyladder.game.util.random;

import com.monkeyladder.game.util.IntegerRange;

public class RandomBoolean {

    public static Boolean nextRandomTrueWithOneOutOfNChance( Integer n ) {
        return RandomNumberGenerator.next( new IntegerRange( 1, n ) ) == 1;
    }

    public static Boolean trueWithFiftyPercentChance( ) {
        return nextRandomTrueWithOneOutOfNChance( 2 );
    }

}
