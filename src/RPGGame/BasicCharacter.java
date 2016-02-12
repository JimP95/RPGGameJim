package RPGGame;

import java.awt.*;
/**
 *  BasicCharacter - A class for a basic character with get/set.
 *  @author Jim Poulsen
 *  @version 1.0 2/7/2016
 */
public class BasicCharacter
{
    /**
     * Character name
     * Character level
     * Character health
     * Character maxhealth
     * Character damage
     * Character location
     * texture
     * Skills
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

    /**
     * This is the constructor for BasicCharacter
     * @param name - Character name
     * @param skills - Character skills
     */
    public BasicCharacter(String name, int skills)
    {
        this.name = name;
        skillArray = new Skills[skills];
    }
    /**
     * A get for the name of the character
     */
    public String getName()
    {
        return name;
    }
    /**
     * A set for the characters level and the max health of the character
     * @param level - Character level
     */
    public void setLevel(int level)
    {
        this.level = level;
        health = (maxHealth = 100 * level);
        damage = this.level;
    }
    /**
     * A set for the characters level
     */
    public int getLevel()
    {
        return level;
    }
    /**
     * A set for the health of a character
     * @param health - The amount of health for the character
     */
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
    /**
     * A get for current health
     */
    public int getHealth()
    {
        return health;
    }
    /**
     * A get for maxhealth
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }
    /**
     * A get for damage
     */
    public int getDamage()
    {
        return damage;
    }
    /**
     * Sets the character location
     * @param location - Location to place character on
     */
    public void setLocation(Point location)
    {
        previousLocation = this.location;
        this.location = location;
    }
    /**
     * Get current location of the character
     */
    public Point getLocation()
    {
        return location;
    }
    /**
     * Gets the previous location
     */
    public Point getPreviousLocation()
    {
        return previousLocation;
    }
    /**
     * Set texture of character
     * @param texture - texture of the character
     */
    public void setTexture(String[] texture)
    {
        this.texture = texture;
    }
    /**
     * Get texture
     */
    public String[] getTexture()
    {
        return texture;
    }
    /**
     * Sets the skills
     * @param skill - Skill to set
     * @param index - The index of the skill to set
     */
    public void setSkills(Skills skill, int index)
    {
        skillArray[index] = skill;
    }
    /**
     * Get the skill array
     */
    public Skills[] getSkillArray()
    {
        return skillArray;
    }
}
