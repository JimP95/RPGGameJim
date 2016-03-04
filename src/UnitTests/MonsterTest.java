package UnitTests;

import RPGGame.Monster;
import RPGGame.Skills;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 3/4/2016.
 */
public class MonsterTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetSkill() throws Exception {
        Monster monster = new Monster("TestMonster", 1);
        Skills skill = new Skills("TestSkill", 10, 20);
        monster.setSkills(skill, 0);
        assertEquals(1, monster.getSkillArray().length);
    }

}