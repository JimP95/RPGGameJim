package RPGGAME;

import java.util.Scanner;

/**
 * Created by Jim on 2/4/2016.
 */
public class MenuConsole
{

    public String getInput(String promptString)
    {
        System.out.print(promptString);

        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }

    public void printToConsole(String outputString)
    {
        for (int i = 0; i < 55; i++)
            System.out.println();

        System.out.println(outputString);
    }

    public String showMenu(MenuEnum menuType, String additionalString)
    {
        String input = "";
        switch (menuType)
        {
            case STARTMENU: input = getInput(" [1]StartGame\n\n  ");
                break;

            case STARTMAP: input = getInput(additionalString);
                break;

            case MOVEMENT:  input = getInput(additionalString + "[w]->UP [s]->DOWN [a]->LEFT [d]->RIGHT\n  ");
                break;

            case COMBAT:  input = getInput("  Select skill [1][2][3]! " + additionalString);
                break;
        }
        return input;
    }
}
