package RPGGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EventListener;
import java.util.Scanner;

/**
 * Created by POU on 04-02-2016.
 */
public class Controls
{
    GameInput gameInput = new GameInput();
    GameOutput gameOutput = new GameOutput();
    Combat combat = new Combat();
    Map map = new Map();

    public void giveCommandsAndTakeInput() throws IOException {
        char[] charargs = map.createMap();
        while (combat.checkCombat() == false)
        {
            gameOutput.printConsoleCommands();
            String keyValue = gameInput.getStringInput();
            switch (keyValue)
            {
                case "1":
                    map.movePlayerBackward(48, charargs);
                    break;
                case "2":
                    map.movePlayerForward(48, charargs);
                    break;
                case "3":
                    map.movePlayerBackward(1, charargs);
                    break;
                case "4":
                    map.movePlayerForward(1, charargs);
                    break;
            }
        }
    }
}
