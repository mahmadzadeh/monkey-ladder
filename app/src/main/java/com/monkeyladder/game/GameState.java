package com.monkeyladder.game;

class GameState {

    private PlayerLives lives;
    private GameLevel level;
    private int score;

    public GameState( PlayerLives lives, GameLevel level, int score ) {
        this.lives = lives;
        this.level = level;
        this.score = score;
    }

    public PlayerLives getLives( ) {
        return lives;
    }

    public GameLevel getLevel( ) {
        return level;
    }

    public int getScore( ) {
        return score;
    }

    public void updateGameStateBasedOnResult( UserInputEvaluationResult result ) {
        if ( UserInputEvaluationResult.Correct == result ) {
            score += level.cellCount();

            // if next level up is empty optional then they have reached the last level
            level = level.nextLevelUp().orElse( GameLevel.LevelSixteen );
        } else {
            lives = PlayerLives.fromLifeCount( --lives.lifeCount );
            score -= level.cellCount();
            level = level.previousLevelDown().orElse( GameLevel.LevelOne );
        }
    }
}