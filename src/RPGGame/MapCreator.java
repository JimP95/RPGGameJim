package RPGGame;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 *  MapCreator - This class creates the map based on the symbols loaded
 *  @author Jim Poulsen
 *  @version 1.0 2/5/2016
 */
public class MapCreator extends Map implements IMapTextures
{
    /**
     *  This method add the the charArray which is responsible for the map creation.
     *  @param mapFileName - the name of the file
     */
    public void setMap(String mapFileName)
    {
        ArrayList<String> mapFromFile = getLinesFromFile(mapDirectory + "\\" + mapFileName);

        if (!(mapFromFile.size() > 0))
            return;

        map = new String[mapFromFile.get(0).length()][mapFromFile.size()][wallTexture.length];

        for (int y = 0; y < mapFromFile.size(); y++)
        {
            for (int x = 0; x < mapFromFile.get(y).toCharArray().length; x++)
            {
                if (mapFromFile.get(y).toCharArray()[x] == wallSymbol)
                    map[x][y] = wallTexture;
                else if (mapFromFile.get(y).toCharArray()[x] == floorSymbol)
                    map[x][y] = floorTexture;
                else if (mapFromFile.get(y).toCharArray()[x] == heroSymbol)
                    map[x][y] = heroTexture;
                else if (mapFromFile.get(y).toCharArray()[x] == monsterSymbol)
                    map[x][y] = monsterTexture;
            }
        }
    }
    /**
     *  Get the maps
     */
    public String getMaps()
    {
        String fileList = "No maps found!";

        File[] files = getMapFiles();

        if (files.length > 0)
            fileList = "";

        for (int i = 0; i < files.length; i++)
            fileList += "  " + String.valueOf(i + 1) + " - " + files[i].getName() + "\n";

        return fileList + "  ";
    }
    /**
     *  Method that gets the map to string
     */
    public String getMap()
    {
        String stringMap = "  ";

        for (int y = 0; y < map[0].length; y++)
        {
            for (int i = 0; i < map[0][y].length; i++)
            {
                for (int x = 0; x < map.length; x++)
                {
                    stringMap += map[x][y][i];
                }
                stringMap += "\n  ";
            }
        }
        return stringMap;
    }
    /**
     *  Get the map based on input
     *  @param index - Index selected by the user
     */
    public String getMapFileName(int index)
    {
        File[] files = getMapFiles();

        if (index - 1 > files.length -1)
            throw new ArrayIndexOutOfBoundsException("Amount of files: " + files.length + ". Index: " + index + " does not exist.");

        return files[index - 1].getName();
    }
    /**
     *  Get the map files from the directory with the end .map
     */
    public File[] getMapFiles()
    {
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {

                if (name.endsWith(".map"))
                    return true;
                else
                    return false;
            }
        };

        return new File(mapDirectory).listFiles(filenameFilter);

    }
    /**
     *  Get the textures for the map creation
     *  @param texture - The texture found
     */
    public ArrayList<Point> getTextureLocations(String[] texture)
    {
        ArrayList<Point> points = new ArrayList<Point>();

        for (int y = 0; y < map[0].length; y++)
            for (int x = 0; x < map.length; x++)
                if (map[x][y] == texture)
                    points.add(new Point(x, y));

        return points;
    }
    /**
     *  Set the texture based on point
     *  @param texture - The texture found
     *  @param point - The point to set the texture at
     */
    public void setTextureLocation(String[] texture, Point point)
    {
        map[point.x][point.y] = texture;
    }
    /**
     *  Move the texture based on point a to b
     *  @param fromPoint - The point from where the texture should be moved from
     *  @param toPoint - The point where the texture should be moved to
     */
    public String moveTextureLocation(Point fromPoint, Point toPoint)
    {
        String result = "Failure: Wall";

        if (map[toPoint.x][toPoint.y] == floorTexture)
        {
            if (map[fromPoint.x][fromPoint.y] == fightTexture)
                result = "Failure: Fight";
            else
            {

                map[toPoint.x][toPoint.y] = map[fromPoint.x][fromPoint.y];

                map[fromPoint.x][fromPoint.y] = floorTexture;

                result = "Success: Floor";
            }
        }
        else if (map[fromPoint.x][fromPoint.y] == heroTexture && map[toPoint.x][toPoint.y] == monsterTexture)
        {
            map[fromPoint.x][fromPoint.y] = floorTexture;

            result = "Success: Monster";
        }
        else if (map[fromPoint.x][fromPoint.y] == monsterTexture && map[toPoint.x][toPoint.y] == heroTexture)
        {
            map[fromPoint.x][fromPoint.y] = floorTexture;

            result = "Success: Player";
        }
        return result;
    }
}
