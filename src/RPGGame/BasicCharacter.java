package RPGGAME;

import java.awt.*;

/**
 * Created by Jim on 2/3/2016.
 */
public class BasicCharacter
{
    /**
     * Character name
     * Character level
     * Character health
     * Character maxhealth
     * Character damage
     */
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private Point location;
    private Point previousLocation;
    private String[] texture;
    private Skills[] skillArray;

    public BasicCharacter(String name, int skills)
    {
        this.name = name;
        skillArray = new Skills[skills];
    }

    public String getName()
    {
        return name;
    }

    public void setLevel(int level)
    {
        this.level = level;
        health = (maxHealth = 100 * level);
        damage = this.level;
    }

    public int getLevel()
    {
        return level;
    }

    public void setHealth(int health)
    {
        if (health > maxHealth)
            this.health = maxHealth;
        else
        {
            if (health < 0)
                this.health = 0;
            else
                this.health = health;
        }
    }

    public int getHealth()
    {
        return health;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getDamage()
    {
        return damage;
    }

    public void setLocation(Point location)
    {
        previousLocation = this.location;
        this.location = location;
    }

    public Point getLocation()
    {
        return location;
    }

    public Point getPreviousLocation()
    {
        return previousLocation;
    }

    public void setTexture(String[] texture)
    {
        this.texture = texture;
    }

    public String[] getTexture()
    {
        return texture;
    }

    public void setSkills(Skills skill, int index)
    {
        skillArray[index] = skill;
    }

    public Skills[] getSkillArray()
    {
        return skillArray;
    }
}
