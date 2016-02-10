package RPGGAME;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by Jim on 2/9/2016.
 */
public class MapCreator extends Map implements IMapTextures
{


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

    public String getMapFileName(int index)
    {
        File[] files = getMapFiles();

        if (index - 1 > files.length -1)
            throw new ArrayIndexOutOfBoundsException("Amount of files: " + files.length + ". Index: " + index + " does not exist.");

        return files[index - 1].getName();
    }

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

    public ArrayList<Point> getTextureLocations(String[] texture)
    {
        ArrayList<Point> points = new ArrayList<Point>();

        for (int y = 0; y < map[0].length; y++)
            for (int x = 0; x < map.length; x++)
                if (map[x][y] == texture)
                    points.add(new Point(x, y));

        return points;
    }

    public void setTextureLocation(String[] texture, Point point)
    {
        map[point.x][point.y] = texture;
    }

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
