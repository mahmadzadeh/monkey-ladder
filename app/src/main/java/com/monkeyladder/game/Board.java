package com.monkeyladder.game;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Cell>> grid;
    private final BoardSize size;

    public Board( BoardSize size ) {
        this.grid = generateGrid( size );
        this.size = size;
    }

    public Board( BoardSize size, List<Cell> onCells ) {
        this.grid = generateGrid( size, onCells );
        this.size = size;
    }

    private List<List<Cell>> generateGrid( BoardSize size, List<Cell> onCells ) {
        final List<List<Cell>> defaultGrid = generateGrid( size );

        onCells.stream()
                .forEach( cell -> defaultGrid
                        .get( cell.getLocation().getRow() )
                        .set( cell.getLocation().getCol(), new Cell( cell ) ) );

        return defaultGrid;
    }

    private List<List<Cell>> generateGrid( BoardSize size ) {
        List<List<Cell>> g = new ArrayList<>();

        for ( int row = 0; row < size.rowCount(); row++ ) {
            List<Cell> columns = new ArrayList<>();
            for ( int col = 0; col < size.colCount(); col++ ) {
                columns.add( new Cell( Location.of( row, col ), CellData.EmptyCellData ) );
            }
            g.add( columns );
        }
        return g;
    }

    public List<Cell> getCellsThatAreNotEmpty( ) {
        List<Cell> nonEmptyCells = new ArrayList<>();

        for ( int row = 0; row < size.rowCount(); row++ ) {
            for ( int col = 0; col < size.colCount(); col++ ) {
                Cell cell = grid.get( row ).get( col );
                if ( cell.getData() != CellData.EmptyCellData ) {
                    nonEmptyCells.add( cell );
                }
            }
        }
        return nonEmptyCells;
    }
}
