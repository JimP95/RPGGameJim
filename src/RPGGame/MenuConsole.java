package RPGGame;

import java.util.Scanner;

/**
 *  MenuConsole - This class is the 'Input/Output' of the game, to the console
 *  @author Jim Poulsen
 *  @version 1.0 2/7/2016
 */
public class MenuConsole
{
    /**
     *  This methods gets the user input
     *  @param promptString - The string that should be printed before user input
     */
    public String getInput(String promptString)
    {
        System.out.print(promptString);

        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }
    /**
     *  Basic method print to console
     *  @param outputString - the output string that should be printed
     */
    public void printToConsole(String outputString)
    {
        for (int i = 0; i < 55; i++)
            System.out.println();

        System.out.println(outputString);
    }

    /**
     *  Basic method print to console without the extra space
     *  @param outputString - the output string that should be printed
     */
    public void printToConsoleWithoutSpace(String outputString)
    {
        System.out.println(outputString);
    }

    /**
     *  Showmenu method that shows the different menus based on the menuType specified
     *  @param menuType - The type of menu to be shown
     *  @param additionalString - The string to be shown as menu if nothing is specified
     */
    public String showMenu(MenuEnum menuType, String additionalString)
    {
        String input = "";
        switch (menuType)
        {
            case STARTMENU: input = getInput("  [1]CreatePlayer\n  [2]ShowPlayersFromDB\n  [3]CloseGame\n\n");
                break;

            case STARTMAP: input = getInput(additionalString);
                break;

            case MOVEMENT:  input = getInput(additionalString + "  [w]->UP [s]->DOWN [a]->LEFT [d]->RIGHT\n  ");
                break;

            case COMBAT:  input = getInput("  Select skill [1][2][3]! " + additionalString);
                break;
        }
        return input;
    }
}
