package SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by POU on 29-02-2016.
 */
public class DBCombatHistory extends DBHelper
{
    public void addCombat(String playerName, String monsterName, String winner)
    {
        try
        {
            String query;
            query = "INSERT INTO rpg_combathistory(combatPlayer_Name, combatMonster_Name, combatWinner_Name) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = (PreparedStatement) sqlConnection().prepareStatement(query);
            preparedStmt.setString(1, playerName);
            preparedStmt.setString(2, monsterName);
            preparedStmt.setString(3, winner);

            preparedStmt.execute();
            sqlConnection().close();
        }
        catch (SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void showCombatHistory(String playerName) throws SQLException {
        Statement stmt = null;
        String query = "SELECT combatPlayer_Name, combatMonster_Name, combatWinner_Name FROM rpg_combathistory WHERE combatPlayer_Name = ?";
        PreparedStatement preparedStmt = (PreparedStatement) sqlConnection().prepareStatement(query);
        preparedStmt.setString(1, playerName);
        try {
            ResultSet rs = preparedStmt.executeQuery();
            System.out.println("  Combat History\n" + "  =================================================\n");
            while (rs.next()) {
                String playername  = rs.getString("combatPlayer_Name");
                String monstername  = rs.getString("combatMonster_Name");
                String winnername  = rs.getString("combatWinner_Name");
                System.out.println("  " + playername + " VS. " + monstername + " Winner was: " + winnername + "\n");

            }
        }
        catch (SQLException e ) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
