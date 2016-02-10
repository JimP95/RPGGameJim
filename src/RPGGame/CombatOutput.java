package RPGGAME;

/**
 * Created by Jim on 2/9/2016.
 */
public class CombatOutput extends Combat
{
    public CombatOutput(BasicCharacter character1, BasicCharacter character2) {
        super(character1, character2);
    }

    public String createCombatScene() {

        int charsAmount = 65;

        charsAmount -= 10;
        charsAmount -= character1.getName().toCharArray().length;
        charsAmount -= character2.getName().toCharArray().length;

        combat =   "  |||||||||||||||||||||||||||||PLAYER VS MONSTER|||||||||||||||||||||||||||||\n" +
                "  || (" + character1.getName() + ")" + addEmptySpace(charsAmount) + "(" + character2.getName() + ") ||\n";

        charsAmount = 75;
        charsAmount -= 20;
        charsAmount -= String.valueOf(character1.getLevel()).toCharArray().length;
        charsAmount -= String.valueOf(character2.getLevel()).toCharArray().length;

        combat +=  "  || Level: " + character1.getLevel() + addEmptySpace(charsAmount) + "Level: " + character2.getLevel() + " ||\n";

        charsAmount = 75;
        charsAmount -= 24;
        charsAmount -= String.valueOf(character1.getHealth()).toCharArray().length;
        charsAmount -= String.valueOf(character1.getMaxHealth()).toCharArray().length;
        charsAmount -= String.valueOf(character2.getHealth()).toCharArray().length;
        charsAmount -= String.valueOf(character2.getMaxHealth()).toCharArray().length;

        combat +=  "  || Health: " + character1.getHealth() + "/" + character1.getMaxHealth() +
                addEmptySpace(charsAmount) + "Health: " + character2.getHealth() + "/" + character2.getMaxHealth() + " ||\n";

        for (int i = 0; i < character1.getSkillArray().length + character2.getSkillArray().length; i++) {

            if (i <= character1.getSkillArray().length - 1) {

                charsAmount = 75;
                charsAmount -= 20;
                charsAmount -= character1.getSkillArray()[i].getName().toCharArray().length;
                charsAmount -= String.valueOf(character1.getSkillArray()[i].getMinDamage() * character1.getDamage()).toCharArray().length;
                charsAmount -= String.valueOf(character1.getSkillArray()[i].getMaxDamage() * character1.getDamage()).toCharArray().length;

                combat +=  "  || Skill[" + (i + 1) + "]: " + character1.getSkillArray()[i].getName() +
                        " (" + (character1.getSkillArray()[i].getMinDamage() * character1.getDamage()) +
                        "," + (character1.getSkillArray()[i].getMaxDamage() * character1.getDamage()) + ")";

                if (i <= character2.getSkillArray().length - 1) {

                    charsAmount -= 14;

                    charsAmount -= character2.getSkillArray()[i].getName().toCharArray().length;
                    charsAmount -= String.valueOf(character2.getSkillArray()[i].getMinDamage() * character2.getDamage()).toCharArray().length;
                    charsAmount -= String.valueOf(character2.getSkillArray()[i].getMaxDamage() * character2.getDamage()).toCharArray().length;

                    combat +=  addEmptySpace(charsAmount) + "Skill[" + (i + 1) + "]: " + character2.getSkillArray()[i].getName() +
                            " (" + (character2.getSkillArray()[i].getMinDamage() * character2.getDamage())
                            + "," + (character2.getSkillArray()[i].getMaxDamage() * character2.getDamage()) + ") ||\n";
                }
                else {

                    combat += addEmptySpace(charsAmount) + " ||\n";

                }

            }

        }
        combat += "  |||FIGHT||FIGHT||FIGHT||FIGHT||FIGHT|||FIGHT||FIGHT||FIGHT||FIGHT||FIGHT|||\n";
        combat += "  |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";


        return combat;

    }
}
