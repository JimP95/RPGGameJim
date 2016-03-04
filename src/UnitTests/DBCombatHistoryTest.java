package UnitTests;

import SQL.DBCombatHistory;
import SQL.DBHelper;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Jim on 3/3/2016.
 */
public class DBCombatHistoryTest {

    /**
     * Remember to have Mysql Server running before testing this
     *
     * These unit tests have only been to check console output
     */
    private DBCombatHistory dbCombatHistory;
    private static final String testPlayer = "TestPlayer";
    private static final String testMonster = "Monster1";
    private static final String testWinner = "TestPlayer";

    @Before
    public void setUp() throws SQLException {
        dbCombatHistory = new DBCombatHistory();
    }

    @Test
    public void testAddCombat() throws SQLException
    {
        dbCombatHistory.addCombat(testPlayer, testMonster, testWinner);
    }


    @Test
    public void testShowCombatHistory() throws SQLException
    {
        dbCombatHistory.showCombatHistory(testPlayer);
    }
}