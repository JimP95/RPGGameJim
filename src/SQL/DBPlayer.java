package SQL;

import java.sql.*;
import java.util.ArrayList;

import SQL.DBHelper;

/**
 * Created by POU on 25-02-2016.
 */
public class DBPlayer extends DBHelper
{
    // TODO make it so that the player has the option to save, and not just when he creates a new player
    public void addPlayer(ArrayList<String> playerInfo) throws SQLException
    {
        for (String item : playerInfo)
        {
            System.out.println(item);
        }
        try
        {
            String query;
            query = "insert into rpg_Player(player_ID, player_Name, player_Level, player_Health, player_Damage, player_Experience) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = (PreparedStatement) sqlConnection().prepareStatement(query);
            preparedStmt.setString(1, playerInfo.get(0).trim());
            preparedStmt.setInt(2, Integer.parseInt(playerInfo.get(1).trim()));
            preparedStmt.setInt(3, Integer.parseInt(playerInfo.get(2).trim()));
            preparedStmt.setInt(4, Integer.parseInt(playerInfo.get(3).trim()));
            preparedStmt.setInt(5, Integer.parseInt(playerInfo.get(4).trim()));

            preparedStmt.execute();
            sqlConnection().close();
        }
        catch (Exception e)
        {
            System.err.println("Got an addPlayer exception!");
            System.out.println(e);
        }
    }

    public ArrayList<String> findPlayers() throws  SQLException
    {
        Statement stmt = null;
        ArrayList<String> playerResultSet = new ArrayList<>();
        String query = "SELECT player_ID, player_Name, player_Level, player_Health, player_Damage, player_Experience FROM rpg_player WHERE 1";

        try {
            stmt = sqlConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                playerResultSet.add(String.valueOf(rs.getInt("player_ID")));
                playerResultSet.add(rs.getString("player_Name"));
                playerResultSet.add(String.valueOf(rs.getInt("player_Level")));
                playerResultSet.add(String.valueOf(rs.getInt("player_Health")));
                playerResultSet.add(String.valueOf(rs.getInt("player_Damage")));
                playerResultSet.add(String.valueOf(rs.getInt("player_Experience")));
            }
        }
        catch (Exception e )
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return playerResultSet;
    }

    public void showPlayers() throws SQLException
    {
        try {
            ArrayList<String> playerInfo = findPlayers();
            for(int item = 0; item <= playerInfo.size(); item++) {
                System.out.println("\nPlayer\n" + "================");
                System.out.println("PlayerID: " + playerInfo.get(item++));
                System.out.println("PlayerName: " + playerInfo.get(item++));
                System.out.println("PlayerLevel: " + playerInfo.get(item++));
                System.out.println("PlayerHealth: " + playerInfo.get(item++));
                System.out.println("PlayerDamage: " + playerInfo.get(item++));
                System.out.println("PlayerExperience: " + playerInfo.get(item));
            }
        }
        catch (Exception e)
        {

        }
    }

}
