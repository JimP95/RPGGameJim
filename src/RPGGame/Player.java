package RPGGame;

/**
 * Created by POU on 04-02-2016.
 */
public class Player
{
    private int health;
    private int level;
    private int experience;
    private int attackDamage;

    public Player()
    {
        health = 200;
        level = 1;
        experience = 0;
        attackDamage = 20;
    }

    public Player(int startHealth, int startLevel, int startAttackDamage)
    {
        health = startHealth;
        level = startLevel;
        attackDamage = startAttackDamage;
    }

    public void addExperience(int exp)
    {

    }

    public void addLevel()
    {
        
    }
}
