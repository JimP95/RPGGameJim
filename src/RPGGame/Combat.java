package RPGGame;

import SQL.DBCombatHistory;

import java.sql.SQLException;

/**
 *  Combat - Create a combat based on two characters Player and Monster
 *  @author Jim Poulsen
 *  @version 1.0 2/5/2016
 */
public class Combat
{
    /**
     *  A player character and a monster Character
     */
    public BasicCharacter player, monster;
    /**
     *  A winner and a loser Character
     */
    public BasicCharacter win, lose;
    /**
     *  This is for the combat output with a set a string of characters
     */
    public String combat;

    private DBCombatHistory combatdb;

    /**
     *  A constructor
     *  @param player - The player character
     *  @param monster - The monster character
     */
    public Combat(BasicCharacter player, BasicCharacter monster)
    {
        this.player = player;
        this.monster = monster;
        combatdb = new DBCombatHistory();
    }

    /**
     *  This method is to add emptySpace easily
     *  @param - The number of spaces needed
     */
    public String addEmptySpace(int spaces) {

        String stringOfEmptySpace = "";

        for (int i = 0; i < spaces; i++)
            stringOfEmptySpace += " ";

        return stringOfEmptySpace;
    }

    /**
     *  This method is used for attacking in combat.
     *  @param input - The index of the skill
     *  @param playerAttack - This parameter is to determine if it's the monster who's attacking or the player
     */
    public String attack(String input, boolean playerAttack) {

        String result = "  You didn't die";

        int damage = 0;

        if (input.toCharArray().length == 1 && playerAttack) {

            switch (input.toCharArray()[0]) {

                case '1':   damage = calculateCharacterDamage(player, 0);
                    monster.setHealth(monster.getHealth() - damage);
                    result = "  You hit " + monster.getName() + " for " + damage + "!";
                    break;

                case '2':   damage = calculateCharacterDamage(player, 1);
                    monster.setHealth(monster.getHealth() - damage);
                    result = "  You hit " + monster.getName() + " for " + damage + "!";
                    break;

                case '3':   damage = calculateCharacterDamage(player, 2);
                    monster.setHealth(monster.getHealth() - damage);
                    result = "  You hit " + monster.getName() + " for " + damage + "!";
                    break;

            }

        }
        else if (input.toCharArray().length == 1 && !playerAttack) {

            damage = calculateCharacterDamage(monster, 0);

            player.setHealth(player.getHealth() - damage);

            result = "\n  " + monster.getName() + " hit you for " + damage + "!";

        }

        if (player.getHealth() == 0)
        {
            win = monster;
            lose = player;
            try {
                combatdb.addCombat(player.getName(), monster.getName(), monster.getName());
            }
            catch(Exception e)
            {
                System.err.println(e);
            }
        }
        else if (monster.getHealth() == 0)
        {
            win = player;
            lose = monster;
            try {
                combatdb.addCombat(player.getName(), monster.getName(), player.getName());
            }
            catch(Exception e)
            {
                System.err.println(e);
            }
        }

        return result + "\n";

    }

    /**
     *  This method is to calculate the character damage
     *  @param character - This is the character
     *  @param skillIndex - The index of the skill
     */
    public int calculateCharacterDamage(BasicCharacter character, int skillIndex) {

        int damage = (int) (Math.random() * (character.getSkillArray()[skillIndex].getMaxDamage() - character.getSkillArray()[skillIndex].getMinDamage()) + 1);

        damage += character.getSkillArray()[skillIndex].getMinDamage();

        damage *= character.getDamage();

        return damage;
    }

    /**
     *  A get for winner
     */
    public BasicCharacter getWinner() {

        return win;

    }

    /**
     *  A get for Loser
     */
    public BasicCharacter getLoser() {

        return lose;

    }


}
