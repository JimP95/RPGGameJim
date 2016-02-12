package RPGGame;

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
     *  A get for max experience
     */
    public int getMaxExperience() {

        return maxExperience;

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
