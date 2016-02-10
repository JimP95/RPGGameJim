package RPGGAME;

/**
 * Created by Jim on 2/4/2016.
 */
public class Combat
{
    public BasicCharacter character1, character2;

    public BasicCharacter win, lose;

    public String combat;

    public Combat(BasicCharacter character1, BasicCharacter character2) {

        this.character1 = character1;
        this.character2 = character2;
    }

    public String addEmptySpace(int spaces) {

        String stringOfEmptySpace = "";

        for (int i = 0; i < spaces; i++)
            stringOfEmptySpace += " ";


        return stringOfEmptySpace;
    }

    public String attack(String input, boolean playerAttack) {

        String result = "  You passed the round";

        int damage = 0;

        if (input.toCharArray().length == 1 && playerAttack) {

            switch (input.toCharArray()[0]) {

                case '1':   damage = calculateCharacterDamage(character1, 0);
                    character2.setHealth(character2.getHealth() - damage);
                    result = "  You hit " + character2.getName() + " for " + damage + "!";
                    break;

                case '2':   damage = calculateCharacterDamage(character1, 1);
                    character2.setHealth(character2.getHealth() - damage);
                    result = "  You hit " + character2.getName() + " for " + damage + "!";
                    break;

                case '3':   damage = calculateCharacterDamage(character1, 2);
                    character2.setHealth(character2.getHealth() - damage);
                    result = "  You hit " + character2.getName() + " for " + damage + "!";
                    break;

            }

        }
        else if (input.toCharArray().length == 1 && !playerAttack) {

            damage = calculateCharacterDamage(character2, 0);

            character1.setHealth(character1.getHealth() - damage);

            result = "\n  " + character2.getName() + " hit you for " + damage + "!";

        }

        if (character1.getHealth() == 0) {

            win = character2;
            lose = character1;

        }
        else if (character2.getHealth() == 0) {

            win = character1;
            lose = character2;

        }

        return result + "\n";

    }

    private int calculateCharacterDamage(BasicCharacter character, int skillIndex) {

        int damage = (int) (Math.random() * (character.getSkillArray()[skillIndex].getMaxDamage() - character.getSkillArray()[skillIndex].getMinDamage()) + 1);

        damage += character.getSkillArray()[skillIndex].getMinDamage();

        damage *= character.getDamage();

        return damage;
    }

    public BasicCharacter getWinner() {

        return win;

    }

    public BasicCharacter getLoser() {

        return lose;

    }


}
