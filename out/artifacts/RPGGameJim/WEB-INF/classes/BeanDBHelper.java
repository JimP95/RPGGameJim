import SQL.DBHelper;
import SQL.DBPlayer;

import javax.faces.bean.ManagedBean;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Jim on 3/3/2016.
 */
@ManagedBean
public class BeanDBHelper
{
    private String playerName;
    private String playerLevel;
    private String playerHealth;
    private String playerDamage;
    private String playerExperience;

    public BeanDBHelper()
    {

    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public String getPlayerLevel()
    {
        return playerLevel;
    }

    public void setPlayerLevel(String playerLevel)
    {
        this.playerLevel = playerLevel;
    }

    public String getPlayerHealth()
    {
        return playerHealth;
    }

    public void setPlayerHealth(String playerHealth)
    {
        this.playerHealth = playerHealth;
    }

    public String getPlayerDamage()
    {
        return playerDamage;
    }

    public void setPlayerDamage(String playerDamage)
    {
        this.playerDamage = playerDamage;
    }

    public String getPlayerExperience()
    {
        return playerExperience;
    }

    public void setPlayerExperience(String playerExperience)
    {
        this.playerExperience = playerExperience;
    }

    public void createPlayerInDB() throws SQLException {
        ArrayList<String> playerInfoList = new ArrayList<>();
        playerInfoList.add(playerName);
        playerInfoList.add(playerLevel);
        playerInfoList.add(playerHealth);
        playerInfoList.add(playerDamage);
        playerInfoList.add(playerExperience);

        DBPlayer dbPlayer = new DBPlayer();
        dbPlayer.addPlayer(playerInfoList);
    }

    public ArrayList<String> showPlayersFromDB() throws SQLException {
        DBPlayer dbPlayer = new DBPlayer();
        ArrayList<String> playerInfo = dbPlayer.findPlayers();
        return playerInfo;
    }
}
