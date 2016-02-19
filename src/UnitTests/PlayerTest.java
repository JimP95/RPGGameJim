package UnitTests;

import RPGGame.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 2/19/2016.
 */
public class PlayerTest {

    private static final String playerName = "TestPlayer";
    private static final int skills = 3;
    Player player;

    @Before
    public void setUp() throws Exception {
        this.player = new Player(playerName, skills);
        player.setLevel(5);
        player.setHealth(200);
        player.setExperience(250);
    }

    @Test
    public void testGetExperience() throws Exception
    {
        assertEquals(250, this.player.getExperience());
    }

    @Test
    public void testPlayerConstructor() throws Exception
    {
        assertEquals(playerName, this.player.getName());
        assertEquals(5, this.player.getDamage());
        assertEquals(200, this.player.getHealth());
        assertEquals(5, this.player.getLevel());
    }
}