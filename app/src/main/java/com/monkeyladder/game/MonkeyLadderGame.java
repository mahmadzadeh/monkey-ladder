package com.monkeyladder.game;

import java.util.ArrayList;
import java.util.List;

import static com.monkeyladder.game.util.random.RandomBoardGenerator.nextTrialForLevel;

public class MonkeyLadderGame {

    private static final BoardSize DEFAULT_BOARD_SIZE = BoardSize.FourByFive;
    private final GameState gameState;
    private Board board;
    private List<Location> userSelectedLocations;

    public MonkeyLadderGame( Board board, GameLevel level ) {
        this.board = board;
        this.userSelectedLocations = new ArrayList<>();
        this.gameState = new GameState( PlayerLives.Default, level, 0 );
    }

    public MonkeyLadderGame( GameLevel level ) {
        this( new Board( DEFAULT_BOARD_SIZE, nextTrialForLevel( level ) ), level );
    }

    public List<Cell> getCurrentCellsSetOnBoard( ) {

        return board.getCellsThatAreNotEmpty();
    }

    public void addUserSelectedLocation( Location location ) {
        userSelectedLocations.add( location );
    }

    public void resetUserSelectedLocations( ) {
        userSelectedLocations = new ArrayList<>();
    }

    public boolean hasEnoughUserSelectedInput( ) {
        return userSelectedLocations.size() == gameState.getLevel().cellCount();
    }

    public UserInputEvaluationResult evaluate( ) {

        List<Cell> nonEmptyCells = board.getCellsThatAreNotEmpty();

        for ( int index = 0; index < nonEmptyCells.size(); index++ ) {
            Cell cell = nonEmptyCells.get( index );
            Location location = userSelectedLocations.get( index );
            if ( cell.getLocation() != location ) {
                return UserInputEvaluationResult.Incorrect;
            }
        }

        return UserInputEvaluationResult.Correct;
    }

    public GameState updateGameState( UserInputEvaluationResult result ) {
        gameState.updateGameStateBasedOnResult( result );

        return gameState;
    }

    public void reset( ) {
        this.board = new Board( this.DEFAULT_BOARD_SIZE, nextTrialForLevel( gameState.getLevel() ) );

        resetUserSelectedLocations();
    }
}
