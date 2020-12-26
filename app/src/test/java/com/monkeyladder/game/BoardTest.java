package com.monkeyladder.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {


    private List<Cell> onCells = new ArrayList<>();

    @Test
    public void givenBoardSize_thenConstructorBuildsBoardCorrectSize() {
        BoardSize boardSize = BoardSize.FourByFive;
        Board board = new Board( boardSize );

        List<Cell> grid = board.getCellsThatAreNotEmpty();

        assertEquals( 0,   grid.size() );
    }
    @Test
    public void givenBoardSizeAndOnCellsWithNothingInIt_thenConstructorBuildsBoardWWithAllDefaultCellData() {
        BoardSize boardSize = BoardSize.FourByFive;

        onCells = new ArrayList<>();

        Board board = new Board( boardSize, onCells );

        List<Cell> notEmptyCells = board.getCellsThatAreNotEmpty();

        assertTrue( notEmptyCells.isEmpty() );
    }

    @Test
    public void givenBoardSizeAndOnCells_thenConstructorBuildsBoardWithOnCellsSet() {
        BoardSize boardSize = BoardSize.FourByFive;

        Cell expectedZerZeroCell = new Cell( Location.ZeroZero, CellData.Eight );
        Cell expectedZeroOneCell = new Cell( Location.ZeroOne, CellData.Nine );
        Cell expectedThreeFourCell = new Cell( Location.ThreeFour, CellData.Eleven );

        onCells = Arrays.asList( expectedZerZeroCell, expectedZeroOneCell, expectedThreeFourCell );

        List<Cell> actualGrid  = new Board( boardSize, onCells ).getCellsThatAreNotEmpty();

        assertTrue( actualGrid.contains( expectedZerZeroCell ) );

//        Cell actualZeroZeroCell = actualGrid.get( Location.ZeroZero.getRow() ).get( Location.ZeroZero.getCol() );
//
//        assertEquals( expectedZerZeroCell, actualZeroZeroCell );
//
//        Cell actualZeroOneCell = actualGrid.get( Location.ZeroOne.getRow() ).get( Location.ZeroOne.getCol() );
//
//        assertEquals( actualZeroOneCell, expectedZeroOneCell );
//
//        Cell actualThreeFourCell = actualGrid.get( Location.ThreeFour.getRow() ).get( Location.ThreeFour.getCol() );

//        assertEquals( actualThreeFourCell, expectedThreeFourCell );
    }



}