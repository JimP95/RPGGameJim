package RPGGame;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeFunction.function;


/**
 * Created by POU on 04-02-2016.
 */
public class Map
{
    private Path mapPath = Paths.get("C:\\Users\\POU\\Desktop\\Maps", "Map1.txt");
    private Charset charset = Charset.forName("ISO-8859-1");
    private char[] charArray = new char[0];

    public char[] createMap()
    {
        System.out.println();
        System.out.print("Map created");
        System.out.println();
        try
        {
            String theString = "";

            File file = new File("C:\\Users\\POU\\Desktop\\Maps\\Map1.txt");
            Scanner scanner = new Scanner(file);

            theString = scanner.nextLine();

            while (scanner.hasNextLine())
            {
                theString = theString + "\n" + scanner.nextLine();

                charArray = theString.toCharArray();
            }
            System.out.print(charArray);

        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return charArray;
    }

    public void movePlayerForward(int moveIndex, char[] charArg)
    {
        char last = charArray[charArray.length-1];
        int xPosition = new String(charArray).indexOf("X");

        // Copy sub-array starting at pos to pos+1

        System.arraycopy(charArray, xPosition, charArg, xPosition + moveIndex, charArray.length - xPosition - moveIndex - 1);

        charArray[xPosition] = last;
        System.out.println();
        System.out.print(charArg);

    }

    public void movePlayerBackward(int moveIndex, char[] charArg)
    {
        char last = charArray[charArray.length-1];
        int xPosition = new String(charArray).indexOf("X");

        // Copy sub-array starting at pos to pos+1

        System.arraycopy(charArray, xPosition, charArg, xPosition - moveIndex, charArray.length - xPosition - moveIndex);

        charArray[xPosition] = last;
        System.out.println();
        System.out.print(charArg);

    }


    public void movePlayer2(int moveIndex, char[] charArg)
    {
        StringBuilder newString = new StringBuilder();

        //newString
    }
}
