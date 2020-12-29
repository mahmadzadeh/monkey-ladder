package com.monkeyladder.game;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MonkeyLadderGameTest {

    Location expectedLocation_1 = Location.ZeroZero;
    Location expectedLocation_2 = Location.ZeroOne;
    Cell cellToBeSelected_1 = new Cell( expectedLocation_1, CellData.One );
    Cell cellToBeSelected_2 = new Cell( expectedLocation_2, CellData.Two );
    private MonkeyLadderGame monkeyLadderGame;

    @Test
    public void constructor( ) {
        monkeyLadderGame = new MonkeyLadderGame( new Board( BoardSize.FourByFive ), GameLevel.LevelTen, PlayerLives.Default );
    }

    @Test
    public void givenLevelOne_whenCollectedOneUserSelectedLocation_thenGameHasEnoughUserInputLoc( ) {
        monkeyLadderGame = new MonkeyLadderGame( new Board( BoardSize.FourByFive ), GameLevel.LevelOne, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( Location.ZeroZero );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );
    }

    @Test
    public void givenLevelOne_whenUserSelectsInvalidCell_thenEvaluateReturnsIncorrect( ) {

        Location expectedLocation = Location.ZeroZero;
        Location userSelectedLocation = Location.ZeroThree;

        Cell cellToBeSelected = new Cell( expectedLocation, CellData.One );

        Board board = new Board( BoardSize.FourByFive, Arrays.asList( cellToBeSelected ) );

        monkeyLadderGame = new MonkeyLadderGame( board, GameLevel.LevelOne, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( userSelectedLocation );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        assertEquals( UserInputEvaluationResult.Incorrect, monkeyLadderGame.evaluate() );
    }


    @Test
    public void givenLevelOne_whenUserSelectsValidCell_thenEvaluateReturnsCorrect( ) {

        Location expectedLocation = Location.ZeroZero;

        Cell cellToBeSelected = new Cell( expectedLocation, CellData.One );

        Board board = new Board( BoardSize.FourByFive, Arrays.asList( cellToBeSelected ) );

        monkeyLadderGame = new MonkeyLadderGame( board, GameLevel.LevelOne, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( expectedLocation );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        assertEquals( UserInputEvaluationResult.Correct, monkeyLadderGame.evaluate() );
    }

    @Test
    public void givenLevelTwo_whenUserSelectsCellIncorrectOrder_thenEvaluateReturnsIncorrect( ) {

        Location expectedLocation_1 = Location.ZeroZero;
        Location expectedLocation_2 = Location.ZeroOne;

        Cell cellToBeSelected_1 = new Cell( expectedLocation_1, CellData.One );
        Cell cellToBeSelected_2 = new Cell( expectedLocation_2, CellData.Two );

        Board board = new Board( BoardSize.FourByFive, Arrays.asList( cellToBeSelected_1, cellToBeSelected_2 ) );

        monkeyLadderGame = new MonkeyLadderGame( board, GameLevel.LevelTwo, PlayerLives.Default );

        // selection order is in reverse order
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        assertEquals( UserInputEvaluationResult.Incorrect, monkeyLadderGame.evaluate() );
    }

    @Test
    public void givenLevelTwo_whenUserSelectsCellCorrectOrder_thenEvaluateReturnsCorrect( ) {

        Location expectedLocation_1 = Location.ZeroZero;
        Location expectedLocation_2 = Location.ZeroOne;

        Cell cellToBeSelected_1 = new Cell( expectedLocation_1, CellData.One );
        Cell cellToBeSelected_2 = new Cell( expectedLocation_2, CellData.Two );

        Board board = new Board( BoardSize.FourByFive, Arrays.asList( cellToBeSelected_1, cellToBeSelected_2 ) );

        monkeyLadderGame = new MonkeyLadderGame( board, GameLevel.LevelTwo, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 ); // order are
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        assertEquals( UserInputEvaluationResult.Correct, monkeyLadderGame.evaluate() );
    }

    @Test
    public void givenLevelFive_whenUserSelectsCellIncorrectOrder_thenEvaluateReturnsIncorrect( ) {

        Location expectedLocation_1 = Location.ZeroZero;
        Location expectedLocation_2 = Location.ZeroOne;
        Location expectedLocation_3 = Location.ThreeZero;
        Location expectedLocation_4 = Location.TwoOne;
        Location expectedLocation_5 = Location.OneFour;

        Cell cellToBeSelected_1 = new Cell( expectedLocation_1, CellData.One );
        Cell cellToBeSelected_2 = new Cell( expectedLocation_2, CellData.Two );
        Cell cellToBeSelected_3 = new Cell( expectedLocation_3, CellData.Three );
        Cell cellToBeSelected_4 = new Cell( expectedLocation_4, CellData.Four );
        Cell cellToBeSelected_5 = new Cell( expectedLocation_5, CellData.Five );

        Board board = new Board( BoardSize.FourByFive,
                Arrays.asList(
                        cellToBeSelected_1,
                        cellToBeSelected_2,
                        cellToBeSelected_3,
                        cellToBeSelected_4,
                        cellToBeSelected_5 ) );

        monkeyLadderGame = new MonkeyLadderGame( board, GameLevel.LevelFive, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_3 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_5 ); // selected location 5 before 4
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_4 );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        assertEquals( UserInputEvaluationResult.Incorrect, monkeyLadderGame.evaluate() );

        //Reset and test with correct order of selection
        monkeyLadderGame.resetUserSelectedLocations();

        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_3 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_4 );
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_5 );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        assertEquals( UserInputEvaluationResult.Correct, monkeyLadderGame.evaluate() );
    }

    @Test
    public void playOneRoundOnFourByFiveBoardAndProvideCorrectSelection_thenGameStateIsUpdated( ) {
        Location expectedLocation_1 = Location.ZeroZero;
        Location expectedLocation_2 = Location.ZeroOne;

        Cell cellToBeSelected_1 = new Cell( expectedLocation_1, CellData.One );
        Cell cellToBeSelected_2 = new Cell( expectedLocation_2, CellData.Two );

        Board board = new Board( BoardSize.FourByFive, Arrays.asList( cellToBeSelected_1, cellToBeSelected_2 ) );

        monkeyLadderGame = new MonkeyLadderGame( board, GameLevel.LevelTwo, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 ); // order are
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 );

        assertTrue( monkeyLadderGame.hasEnoughUserSelectedInput() );

        UserInputEvaluationResult result = monkeyLadderGame.evaluate();

        assertEquals( UserInputEvaluationResult.Correct, result );

        monkeyLadderGame.updateGameState( result );
    }

    @Test
    public void givenAtLevelThreeAndNoLivesLeft_thenWrongSelectionWillEndGame( ) {

        OneRoundResult gameRunResult = playOneBadRound( GameLevel.LevelTwo ); // 2 - 1 = 1 life left

        assertEquals( GameLevel.LevelOne, gameRunResult.gameState.getLevel() );
        assertFalse( monkeyLadderGame.isGameEnd( gameRunResult.result ) );

        gameRunResult = playOneBadFollowUpRound();  // 1 - 1 = no life left

        assertEquals( GameLevel.LevelOne, gameRunResult.gameState.getLevel() );
        assertEquals( UserInputEvaluationResult.Incorrect, gameRunResult.result );
        assertFalse( monkeyLadderGame.isGameEnd( gameRunResult.result ) );

        gameRunResult = playOneBadFollowUpRound();  //last mistake - game end
        assertTrue( monkeyLadderGame.isGameEnd( gameRunResult.result ) );
    }

    private OneRoundResult playOneBadRound( GameLevel gameLevel ) {

        Board board = new Board( BoardSize.FourByFive, Arrays.asList( cellToBeSelected_1, cellToBeSelected_2 ) );

        monkeyLadderGame = new MonkeyLadderGame( board, gameLevel, PlayerLives.Default );

        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 ); // wrong order are
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 );

        UserInputEvaluationResult result = monkeyLadderGame.evaluate();

        return new OneRoundResult( monkeyLadderGame.updateGameState( result ), result );
    }

    private OneRoundResult playOneBadFollowUpRound( ) {
        monkeyLadderGame.reset();

        monkeyLadderGame.addUserSelectedLocation( expectedLocation_2 ); // wrong order are
        monkeyLadderGame.addUserSelectedLocation( expectedLocation_1 );

        UserInputEvaluationResult result = monkeyLadderGame.evaluate();

        return new OneRoundResult( monkeyLadderGame.updateGameState( result ), result );
    }

    private class OneRoundResult {
        private GameState gameState;
        private UserInputEvaluationResult result;

        public OneRoundResult( GameState gameState, UserInputEvaluationResult result ) {
            this.gameState = gameState;
            this.result = result;
        }
    }

}