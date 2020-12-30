package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class CellDataMapping {

    private final Map<Integer, Integer> mapping;


    public CellDataMapping( ) {
        mapping = Collections.unmodifiableMap( initLookupMap() );
    }

    private Map<Integer, Integer> initLookupMap( ) {
        Map<Integer, Integer> mapping = new HashMap<>();
        mapping.put( 1, R.drawable.one );
        mapping.put( 2, R.drawable.two );
        mapping.put( 3, R.drawable.three );
        mapping.put( 4, R.drawable.four );
        mapping.put( 5, R.drawable.five );
        mapping.put( 6, R.drawable.six );
        mapping.put( 7, R.drawable.seven );
        mapping.put( 8, R.drawable.eight );
        mapping.put( 9, R.drawable.nine );
        mapping.put( 10, R.drawable.ten );
        mapping.put( 11, R.drawable.eleven );
        mapping.put( 12, R.drawable.twelve );
        mapping.put( 13, R.drawable.thirteen );
        mapping.put( 14, R.drawable.fourteen );
        mapping.put( 15, R.drawable.fifteen );
        mapping.put( 16, R.drawable.two );
        mapping.put( 17, R.drawable.two );
        mapping.put( 18, R.drawable.two );
        mapping.put( 19, R.drawable.two );
        mapping.put( 20, R.drawable.two );
        return mapping;
    }

    public Optional<Integer> drawableResourceIdFor( int id ) {
        return Optional.ofNullable( mapping.get( id ) );
    }

    public Map<Integer, Integer> getMapping( ) {
        return this.mapping; // not worried about internal data being modified as we're returning unmodifiable maps
    }
}
