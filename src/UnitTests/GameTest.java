package UnitTests;

import RPGGame.Game;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 2/19/2016.
 */
public class GameTest
{
    Game game;
    private static final String playerType = "Player";
    @Test
    public void testCreateCharacter() throws Exception
    {
        game = new Game();
        game.createCharacter(playerType);
    }

    @Test
    public void testShowMaps() throws Exception
    {
        game.showMaps();
    }

}