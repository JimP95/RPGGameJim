package RPGGame;

/**
 * Created by POU on 04-02-2016.
 */
public class Game
{
    public static void main(String [ ] args)
    {
        // Give options: Create player or close
        // Ask for input: Enter name for player
        // Send that to Player class
        // Create player
        // Start Random Map
        // Give options: Output Movement commands
        // E.g. "UP" should get you to a new map
        // 50/50 % chance of getting in combat
        // Start Combat or Continue
        // Player Use one of 3 attacks
        // Monster Attacks with random attack
        // Continue until player wins or dies
        // If player wins, reward with xp/level/health
        // If player dies, start from previously level
        // New WoW expansion complete
        //GameOutput gameoutput = new GameOutput();
        //GameInput gameinput = new GameInput();
        //gameoutput.printWelcomeMessage();
        //String playerName = gameinput.getInput();
        Player player = new Player("s", 200, 1, 0 , 20);
        player.checkLevelUp(1, 0);
        //player.addLevel(player);
        //player.addExperience(player, 200);

    }


}
