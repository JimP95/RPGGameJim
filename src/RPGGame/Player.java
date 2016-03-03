package RPGGame;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.ArrayList;

/**
 *  Player - Player extends basicCharacter. This is the character
 *  that will be used to play the actual game
 *  @author Jim Poulsen
 *  @version 1.0 2/7/2016
 */
public class Player extends BasicCharacter
{
    private int experience;

    private int maxExperience;

    /**
     * Constructor.
     * @param name - The player name
     * @param skills - How many skills the player has
     */
    public Player (String name, int skills) {

        super(name, skills);

    }

    public ArrayList<String> getPlayerInfo()
    {
        ArrayList<String> playerInfoList = new ArrayList<String>();
        playerInfoList.add(getName());
        playerInfoList.add(String.valueOf(getLevel()));
        playerInfoList.add(String.valueOf(getMaxHealth()));
        playerInfoList.add(String.valueOf(getDamage()));
        playerInfoList.add(String.valueOf(getExperience()));

        return playerInfoList;
    }

    /**
     * Sets experience
     * @param experience - The amount of experience.
     */
    public void setExperience(int experience) {

        if (experience >= maxExperience) {

            this.experience = experience - maxExperience;
            setLevel(getLevel() + 1);

        }
        else
            this.experience = experience;

    }

    /**
     *  A get for experience
     */
    public int getExperience() {

        return experience;

    }

    /**
     * Calls the setLevel function in Character and overrides the function to set the max amount of experience.
     * @param level - The level.
     */
    @Override
    public void setLevel(int level) {

        super.setLevel(level);
        maxExperience = 200 * level;

    }
}
