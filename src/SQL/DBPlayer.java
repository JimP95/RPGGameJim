package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import SQL.DBHelper;

/**
 * Created by POU on 25-02-2016.
 */
public class DBPlayer extends DBHelper
{
    public void addPlayer(ArrayList<String> playerInfo) throws SQLException
    {
        try
        {
            String query;
            //query = "insert into player (Player_ID, PlayerName, Levels, MaxHealth, Damage) VALUES (NULL, ?, ?, ?, ?)";
            query = "insert into rpg_Player(player_ID, player_Name, player_Level, player_Health, player_Damage, player_Experience) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = (PreparedStatement) sqlConnection().prepareStatement(query);
            preparedStmt.setString(1, playerInfo.get(0));
            preparedStmt.setInt(2, Integer.parseInt(playerInfo.get(1)));
            preparedStmt.setInt(3, Integer.parseInt(playerInfo.get(2)));
            preparedStmt.setInt(4, Integer.parseInt(playerInfo.get(3)));
            preparedStmt.setInt(5, Integer.parseInt(playerInfo.get(4)));

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
