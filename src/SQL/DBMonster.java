package SQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by POU on 25-02-2016.
 */
public class DBMonster extends DBHelper
{
    void getMonster(String playerName, int level, int maxHealth, int damage) throws SQLException {

        try
        {
            String query;
            query = "insert into monster (Player_ID, PlayerName, Levels, MaxHealth, Damage) VALUES (NULL, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = (PreparedStatement) sqlConnection().prepareStatement(query);
            preparedStmt.setString(1, playerName);
            preparedStmt.setInt(2, level);
            preparedStmt.setInt(3, maxHealth);
            preparedStmt.setInt(4, damage);

            preparedStmt.execute();
            sqlConnection().close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
}
