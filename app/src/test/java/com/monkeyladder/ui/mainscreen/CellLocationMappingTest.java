package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.R;
import com.monkeyladder.game.GameLevel;
import com.monkeyladder.game.Location;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CellLocationMappingTest {

    @Test
    public void canCreateInstance( ) {
        CellLocationMapping mapping = new CellLocationMapping();
    }

    @Test
    public void lookingForLocationOfAnInvalidImageResultsInOptionalEmpty( ) {

        CellLocationMapping mapping = new CellLocationMapping();
        assertEquals( Optional.empty(), mapping.locationForResource( -1 ) );
    }

    @Test
    public void lookingForLocationOfValidImageResultsInItLocationReturned( ) {

        CellLocationMapping mapping = new CellLocationMapping();

        Optional<Location> actual = mapping.locationForResource( R.id.text_01 );

        assertNotEquals( Optional.empty(), actual );

        assertEquals( Location.ZeroOne, actual.get() );
    }

    @Test
    public void testSomeValidMappings( ) {

        CellLocationMapping mapping = new CellLocationMapping();

        Map<Integer, Location> testValues = new HashMap<Integer, Location>() {{
            put( R.id.text_02, Location.ZeroTwo );
            put( R.id.text_03, Location.ZeroThree );
            put( R.id.text_11, Location.OneOne );
            put( R.id.text_13, Location.OneThree );
            put( R.id.text_30, Location.ThreeZero );
            put( R.id.text_31, Location.ThreeOne );
            put( R.id.text_34, Location.ThreeFour );
        }};

        testValues.entrySet().stream().forEach( testEntry -> {
                    assertEquals( testEntry.getValue(), mapping.locationForResource( testEntry.getKey() ).get() );
                }
        );
    }

    @Test
    public void givenNullLevel_thenGetRandomLocationForLevelNoRandomLocations() {

        GameLevel level = GameLevel.Null;

        assertEquals( 0, Location.getRandomLocationForLevel( level ).size());
    }

    @Test
    public void givenLevelOne_thenGetRandomLocationForLevelReturnsSingleRandomLocation() {

        GameLevel level = GameLevel.LevelOne;

        assertEquals( 1, Location.getRandomLocationForLevel( level ).size());
    }

    @Test
    public void givenLevelTen_thenGetRandomLocationForLevelReturnsTenRandomLocation() {

        GameLevel level = GameLevel.LevelTen;

        assertEquals( 10, Location.getRandomLocationForLevel( level ).size());
    }

    @Test
    public void givenLevelTen_thenGetRandomLocationForLevelReturnsTenDistinctRandomLocation() {

        GameLevel level = GameLevel.LevelTen;

        Set<Location> randomLocationForLevel = new HashSet<>(Location.getRandomLocationForLevel( level ));

        assertEquals( 10, randomLocationForLevel.size());
    }

    @Test
    public void givenLevelTen_thenGetRandomLocationForLevelDoesNotReturnNullLocation() {

        GameLevel level = GameLevel.LevelTen;

        Set<Location> randomLocationForLevel = new HashSet<>(Location.getRandomLocationForLevel( level ));

        assertFalse( randomLocationForLevel.contains( Location.NonExistentLocation ));
    }

}