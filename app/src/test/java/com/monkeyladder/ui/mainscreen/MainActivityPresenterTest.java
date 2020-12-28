package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Board;
import com.monkeyladder.game.Location;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    List<LocationData> data = new ArrayList<>();

    @Mock
    private MainActivityViewContract mockView;

    @Mock
    private MainActivityModelContract mockModel;

    @Mock
    private Board mockGameBoard;

    private MainActivityPresenter sut;

    private CountDownTimerParameter countDownTimerParam;


    @Before
    public void setUp( ) {
        countDownTimerParam = CountDownTimerParameter.of( 10, 1 );

        sut = new MainActivityPresenter( mockView, mockModel, countDownTimerParam );
    }

    @Test
    public void addSelectedLocation_addsItemsInOrderTheyAreEntered( ) {
        Location firstLoc = Location.ZeroZero;
        Location secondLoc = Location.ZeroOne;

        sut.addSelectedLocation( firstLoc );
        sut.addSelectedLocation( secondLoc );

        List<Location> locations = sut.getSelectedLocations();

        assertEquals( firstLoc, locations.get( 0 ) );
        assertEquals( secondLoc, locations.get( 1 ) );
    }

    @Test
    public void getCurrentBoardReturnsTheCurrentLocationOnBoardThatAreSet( ) {

        when( mockModel.getCellsThatAreSet() ).thenReturn( data );

        List<LocationData> currentBoard = sut.getCurrentBoard();

        assertNotNull( currentBoard );
        assertTrue( currentBoard.isEmpty() );
    }


}