package com.monkeyladder.game.util.random;

import com.monkeyladder.game.util.IntegerRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class RandomNumberGenerator {

    private final static Random random = new Random( System.currentTimeMillis() );

    public static Integer next( IntegerRange range ) {
        return random.nextInt( range.upperBound() - range.lowerBound() + 1 ) + range.lowerBound();
    }

    public static Set<Integer> next_N_DistinctRandomIntsWithinRange( int n, IntegerRange range ) {
        return generate( n, range, new HashSet<>() );
    }

    public static List<Integer> next_N_DistinctRandomIntsWithinRangeInRandomOrder( int n, IntegerRange range ) {
        Set<Integer> integers = generate( n, range, new HashSet<>() );
        List<Integer> result = new ArrayList<>( integers );
        Collections.shuffle( result );

        return result;
    }

    // tail rec
    private static Set<Integer> generate( int n, IntegerRange range, Set<Integer> acc ) {
        if ( n == 0 )
            return acc;
        else {
            int sizeBefore = acc.size();
            acc.add( next( range ) );
            return generate( n - ( acc.size() - sizeBefore ), range, acc );
        }
    }
}

