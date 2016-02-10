package RPGGAME;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Jim on 2/5/2016.
 */
public class Map implements IMapTextures
{

    public final char wallSymbol = 'W', floorSymbol = ' ', heroSymbol = 'Q', monsterSymbol = 'M';

    public String mapDirectory;

    public String[][][] map;

    public Map()
    {
        mapDirectory = System.getProperty("user.dir") + "\\maps\\";
    }



    public ArrayList<String> getLinesFromFile(String filePath)
    {
        File file = new File(filePath);

        ArrayList<String> arrayListOfLines = new ArrayList<String>();

        if (!file.exists() || !file.canRead())
        {
            System.out.println("Can't read " + file);

            return arrayListOfLines;
        }
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;

            while ((line = bufferedReader.readLine()) != null)
                arrayListOfLines.add(line);

        } catch ( IOException e )
        {
            System.out.println("Error reading the file!");
        }
        return arrayListOfLines;
    }
}
