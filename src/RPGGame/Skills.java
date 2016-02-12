package RPGGame;

/**
 *  Skills - Skills is used to get/set damage and hold the skill array
 *  @author Jim Poulsen
 *  @version 1.0 2/6/2016
 */
public class Skills
{
    private String name;

    private int minDamage;

    private int maxDamage;

    public Skills(String name, int minDamage, int maxDamage)
    {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public String getName()
    {
        return name;
    }

    public int getMinDamage()
    {
        return minDamage;
    }

    public int getMaxDamage()
    {
        return maxDamage;
    }
}
