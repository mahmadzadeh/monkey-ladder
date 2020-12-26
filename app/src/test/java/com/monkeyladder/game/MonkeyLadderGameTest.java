package com.monkeyladder.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonkeyLadderGameTest {

    private MonkeyLadderGame monkeyLadderGame;

    @Test
    public void constructor() {
        monkeyLadderGame = new MonkeyLadderGame(new Board( BoardSize.FourByFive ), GameLevel.LevelTen);
    }

}