package RPGGame;

/**
 *  CombatOutput - This class extends Combat and is only used to create the 'Console Interface'.
 *  @author Jim Poulsen
 *  @version 1.0 2/7/2016
 */
public class CombatOutput extends Combat
{
    public CombatOutput(BasicCharacter character1, BasicCharacter character2) {
        super(character1, character2);
    }

    /**
     *  A method to create all the output when the combat begins
     */
    public String createCombatScene() {

        int charsAmount = 75;

        charsAmount -= 10;
        charsAmount -= player.getName().toCharArray().length;
        charsAmount -= monster.getName().toCharArray().length;

        combat =   "  |||||||||||||||||||||||||||||PLAYER VS MONSTER|||||||||||||||||||||||||||||\n" +
                "  || (" + player.getName() + ")" + addEmptySpace(charsAmount) + "(" + monster.getName() + ") ||\n";

        charsAmount = 75;
        charsAmount -= 20;
        charsAmount -= String.valueOf(player.getLevel()).toCharArray().length;
        charsAmount -= String.valueOf(monster.getLevel()).toCharArray().length;

        combat +=  "  || Level: " + player.getLevel() + addEmptySpace(charsAmount) + "Level: " + monster.getLevel() + " ||\n";

        charsAmount = 75;
        charsAmount -= 24;
        charsAmount -= String.valueOf(player.getHealth()).toCharArray().length;
        charsAmount -= String.valueOf(player.getMaxHealth()).toCharArray().length;
        charsAmount -= String.valueOf(monster.getHealth()).toCharArray().length;
        charsAmount -= String.valueOf(monster.getMaxHealth()).toCharArray().length;

        combat +=  "  || Health: " + player.getHealth() + "/" + player.getMaxHealth() +
                addEmptySpace(charsAmount) + "Health: " + monster.getHealth() + "/" + monster.getMaxHealth() + " ||\n";

        for (int i = 0; i < player.getSkillArray().length + monster.getSkillArray().length; i++) {

            if (i <= player.getSkillArray().length - 1) {

                charsAmount = 75;
                charsAmount -= 20;
                charsAmount -= player.getSkillArray()[i].getName().toCharArray().length;
                charsAmount -= String.valueOf(player.getSkillArray()[i].getMinDamage() * player.getDamage()).toCharArray().length;
                charsAmount -= String.valueOf(player.getSkillArray()[i].getMaxDamage() * player.getDamage()).toCharArray().length;

                combat +=  "  || Skill[" + (i + 1) + "]: " + player.getSkillArray()[i].getName() +
                        " (" + (player.getSkillArray()[i].getMinDamage() * player.getDamage()) +
                        "," + (player.getSkillArray()[i].getMaxDamage() * player.getDamage()) + ")";

                if (i <= monster.getSkillArray().length - 1) {

                    charsAmount -= 14;

                    charsAmount -= monster.getSkillArray()[i].getName().toCharArray().length;
                    charsAmount -= String.valueOf(monster.getSkillArray()[i].getMinDamage() * monster.getDamage()).toCharArray().length;
                    charsAmount -= String.valueOf(monster.getSkillArray()[i].getMaxDamage() * monster.getDamage()).toCharArray().length;

                    combat +=  addEmptySpace(charsAmount) + "Skill[" + (i + 1) + "]: " + monster.getSkillArray()[i].getName() +
                            " (" + (monster.getSkillArray()[i].getMinDamage() * monster.getDamage())
                            + "," + (monster.getSkillArray()[i].getMaxDamage() * monster.getDamage()) + ") ||\n";
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
