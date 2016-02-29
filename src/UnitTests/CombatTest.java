package UnitTests;

import RPGGame.BasicCharacter;
import RPGGame.Combat;
import RPGGame.Monster;
import RPGGame.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 2/19/2016.
 */
public class CombatTest {

    Player player = new Player("PlayerTest", 3);
    Monster monster = new Monster("MonsterTest", 1);
    Combat combat = new Combat(player, monster);

    @Test
    public void testAddEmptySpace() throws Exception
    {
        String result = combat.addEmptySpace(5);
        assertEquals("     ",result);
    }

    @Test
    public void testAttack() throws Exception
    {
        String result = combat.attack("2", true);
        assertEquals("  You hit Monster1 for 1000!", result);
    }


}