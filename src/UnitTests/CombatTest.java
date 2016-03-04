package UnitTests;

import RPGGame.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 2/19/2016.
 */
public class CombatTest {

    private Player player;
    private Monster monster;
    private Combat combat;
    private Skills skills;

    @Before
    public void setUp() throws Exception {
        player = new Player("PlayerTest", 1);
        monster = new Monster("MonsterTest", 1);
        combat = new Combat(player, monster);
        skills = new Skills("TestSkill", 1000, 10000);
    }

    @Test
    public void testAddEmptySpace() throws Exception
    {
        String result = combat.addEmptySpace(5);
        assertEquals("     ",result);
    }

    @Test
    public void testCalculateCharacterDamage() throws Exception
    {
        player.setSkills(skills, 0);
        int result = combat.calculateCharacterDamage(player, 0);
        assertEquals(1000, 1000, result);
    }

}