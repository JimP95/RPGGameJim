package RPGGame;

import java.util.Scanner;

/**
 * Created by POU on 04-02-2016.
 */
public class GameInput
{
    public String getInput() {
        String value = null;
        try {
            Scanner scanner = new Scanner(System.in);
            value = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return value;
    }
}
