package RPGGame;

/**
 * Created by POU on 04-02-2016.
 */
public class Player
{
    private String playerName;
    private int health;
    private int level;
    private int experience;
    private int attackDamage;

    public Player(String newPlayerName, int startHealth, int startLevel, int startExperience, int startAttackDamage)
    {
        playerName = newPlayerName;
        health = startHealth;
        level = startLevel;
        experience = startExperience;
        attackDamage = startAttackDamage;
        System.out.printf("%s %d %d %d %d", playerName, health, level, experience, attackDamage);
    }

    public Player(int startHealth, int startLevel, int startAttackDamage)
    {
        health = startHealth;
        level = startLevel;
        attackDamage = startAttackDamage;
    }

    public int addExperience(Player player, int exp)
    {
        experience += exp;
        System.out.print("    " + exp);
        return experience;
    }

    public int addLevel(Player player)
    {
        level += 1;
        System.out.print("    " + level);
        return level;
    }

    public void checkLevelUp(int currentLevel, int currentExp)
    {
        System.out.println();
        for (double i = 1; i < 10; i++)
        {
            double v = 10 * (Math.pow((double) 1.7, (double) i));
            System.out.print("   " + v);
        }
        System.out.println();
        for (double i = 1; i < 10; i++)
        {
            double v = 10 * (Math.pow((double) 2.5, (double) i));
            System.out.print("   " + v);
        }
    }


}
