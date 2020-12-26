package com.monkeyladder.game;

import java.util.List;

import static com.monkeyladder.game.util.random.RandomBoardGenerator.nextTrialForLevel;

public class MonkeyLadderGame {

    private static final BoardSize DEFAULT_BOARD_SIZE = BoardSize.FourByFive;
    private final Board board;
    private final GameLevel currentLevel;

    public MonkeyLadderGame( Board board, GameLevel level ) {
        this.board = board;
        currentLevel = level;
    }

    public MonkeyLadderGame( GameLevel level ) {
        this( new Board( DEFAULT_BOARD_SIZE, nextTrialForLevel( level ) ), level );
    }

    public List<Cell> getCurrentCellsSetOnBoard( ) {
        return board.getCellsThatAreNotEmpty();
    }
}
