package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import SQL.DBHelper;

/**
 * Created by POU on 25-02-2016.
 */
public class DBPlayer extends DBHelper
{
    void addPlayer(String playerName, int level, int maxHealth, int damage) throws SQLException {

        try
        {
            String query;
            query = "insert into player (Player_ID, PlayerName, Levels, MaxHealth, Damage) VALUES (NULL, ?, ?, ?, ?)";

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

    void updatePlayer(String playerName, int level, int maxHealth, int damage) throws SQLException {

        try
        {
            String query;
            query = "insert into player (Player_ID, PlayerName, Levels, MaxHealth, Damage) VALUES (NULL, ?, ?, ?, ?)";

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

    void deletePlayer(String playerName, int level, int maxHealth, int damage) throws SQLException {

        try
        {
            String query;
            query = "insert into kunder (Player_ID, PlayerName, Levels, MaxHealth, Damage) VALUES (NULL, ?, ?, ?, ?)";

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
