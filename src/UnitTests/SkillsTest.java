package UnitTests;

import RPGGame.Skills;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 3/4/2016.
 */
public class SkillsTest {

    private Skills skills;

    @Before
    public void setUp() throws Exception {
        skills = new Skills("TestSkill", 10, 20);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("TestSkill", skills.getName());
    }

    @Test
    public void testGetMinDamage() throws Exception {
        assertEquals(10, skills.getMinDamage());
    }

    @Test
    public void testGetMaxDamage() throws Exception {
        assertEquals(20, skills.getMaxDamage());
    }
}