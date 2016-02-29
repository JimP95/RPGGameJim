package RPGGame;

import jdk.internal.util.xml.impl.Input;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *  Game - This class mainly holds the gameloop which runs the entire game.
 *  @author Jim Poulsen
 *  @version 1.0 2/7/2016
 */
public class Game
{
    private ArrayList<BasicCharacter> characters;
    private Map map;
    private MapCreator mapCreator;
    private MenuConsole menuConsole;
    private InputStream inputStream;

    /**
     *  A constructor for Game
     */
    public Game()
    {
        menuConsole = new MenuConsole();
        map = new Map();
        characters = new ArrayList<BasicCharacter>();
        mapCreator = new MapCreator();
    }

    /**
     *  This method is for a quick output to the console and start the menu options
     */
    public void beginGame()
    {
        menuConsole.printToConsole(" || RPGGAME MENU ||\n");

        int input = convertToInteger(menuConsole.showMenu(MenuEnum.STARTMENU, ""));

        switch (input)
        {
            case 1:  startGame();
                break;

            default: beginGame();
        }
    }
    /**
     *  When the user has written a name and selected a map then this method will be
     *  called to start the actual game
     */
    private void startGame()
    {
        String createPlayerString = "Player";
        String createMonsterString = "Monster";

        createCharacter(createPlayerString);

        createCharacter(createMonsterString);

        menuConsole.getInput("  Map loaded\n\n  You will be 'P' on the map, and monsters will be 'M'!\n\n  Press enter to start!");

        gameLoop();

    }
    /**
     *  Private method to start the actual loop
     */
    private void gameLoop()
    {
        while(true)
        {
            menuConsole.printToConsole(mapCreator.getMap());

            processUserInput(menuConsole.showMenu(MenuEnum.MOVEMENT, "  Press w,a,s,d to move "));

            menuConsole.printToConsole(mapCreator.getMap());

            BasicCharacter[] charactersFighting = characterFight();

            if (charactersFighting == null)
                moveMonsters();

            charactersFighting = characterFight();

            if (charactersFighting != null)
            {
                CombatOutput combat = new CombatOutput(charactersFighting[0], charactersFighting[1]);

                menuConsole.printToConsole(mapCreator.getMap());
                menuConsole.getInput("  !!!!!!!!!!!!!\n  MONSTER HAS APPEARED\n  PRESS ENTER TO START COMBAT\n  !!!!!!!!!!!!!\n");


                while (combat.getWinner() == null)
                {
                    if (charactersFighting[0].getHealth() == charactersFighting[0].getMaxHealth())
                        menuConsole.printToConsole(combat.createCombatScene());
                    else
                        menuConsole.printToConsole(combat.createCombatScene());

                    String result = combat.attack(menuConsole.showMenu(MenuEnum.COMBAT, ""), true);

                    if (combat.getWinner() == null)
                    {
                        result += combat.attack("1", false);

                        if (combat.getWinner() != null)
                        {
                            result += "\n  " + combat.getWinner().getName() + " is the winner!!\n";

                            menuConsole.printToConsole(combat.createCombatScene());

                        }
                        else
                            menuConsole.printToConsole(combat.createCombatScene());

                    }
                    else
                    {
                        menuConsole.printToConsole(combat.createCombatScene());

                        result +=   "\n  " + combat.getWinner().getName() + " is the winner!!\n" +
                                "\n EarnedExperience: " + (combat.getWinner().getHealth() * 5) + " experience!\n";
                    }

                    menuConsole.getInput(result + "\n  Press enter to keep playing");

                }

                BasicCharacter winner = combat.getWinner(), loser = combat.getLoser();

                if (winner instanceof Player) {

                    ((Player) winner).setExperience((((Player) winner).getExperience() + winner.getHealth() * 5));

                    winner.setTexture(map.heroTexture);

                    winner.setHealth(winner.getMaxHealth());

                    characters.remove(loser);

                    for (BasicCharacter basicCharacter : characters)
                        if (!(basicCharacter instanceof Player))
                            basicCharacter.setLevel(winner.getLevel());

                }
                else
                {
                    winner.setTexture(map.monsterTexture);

                    winner.setHealth(winner.getMaxHealth());

                    loser.setHealth(loser.getMaxHealth());

                    loser.setTexture(map.heroTexture);

                    loser.setLocation(loser.getPreviousLocation());

                    mapCreator.setTextureLocation(loser.getTexture(),loser.getLocation());
                }

                mapCreator.setTextureLocation(winner.getTexture(), winner.getLocation());

                if (characters.size() == 1)
                    spawnExtraMonsters(winner.getLevel(), winner.getLevel());
            }
        }
    }
    /**
     * This private method return the two characters fighting
     */
    private BasicCharacter[] characterFight()
    {
        BasicCharacter[] charactersFighting = new BasicCharacter[2];

        int x = 0;

        for (int i = 0; i < characters.size(); i++)
            if (characters.get(i).getTexture() == map.fightTexture)
            {
                charactersFighting[x] = characters.get(i);

                x++;
            }

        if (x < 1)
            return null;
        else
        {
            if (charactersFighting[0] instanceof Monster)
            {
                BasicCharacter monster = charactersFighting[0];

                charactersFighting[0] = charactersFighting[1];

                charactersFighting[1] = monster;
            }
        }

        return charactersFighting;

    }
    /**
     * This private method moves gets the user input based on where the
     * user wants to go
     * @param input - The player input
     */
    private void processUserInput(String input)
    {
        if (input.toCharArray().length == 1)
        {
            switch (input.toCharArray()[0])
            {
                case 'w':   for (BasicCharacter hero : characters)
                    if (hero instanceof Player)
                        movePlayer(hero, new Point(0, -1));

                    break;

                case 's':   for (BasicCharacter hero : characters)
                    if (hero instanceof Player)
                        movePlayer(hero, new Point(0, 1));

                    break;

                case 'a':   for (BasicCharacter hero : characters)
                    if (hero instanceof Player)
                        movePlayer(hero, new Point(-1, 0));

                    break;

                case 'd':   for (BasicCharacter hero : characters)
                    if (hero instanceof Player)
                        movePlayer(hero, new Point(1, 0));

                    break;
            }
        }
    }
    /**
     *  Randomly move the monsters around
     */
    private void moveMonsters()
    {
        for (BasicCharacter monster : characters)
            if (monster instanceof Monster)
            {
                int xMovement = (int) (Math.random() * 3) - 1,
                        yMovement = (int) (Math.random() * 3) - 1;

                movePlayer(monster, new Point(xMovement, yMovement));
            }
    }
    /**
     * This private method moves the player around based on the points given
     * @param basicCharacter - The character(Player) to move
     * @param point - How much the player should be moved
     */
    private void movePlayer(BasicCharacter basicCharacter, Point point)
    {
        Point oldLocation = basicCharacter.getLocation();

        Point newLocation = new Point(point.x + basicCharacter.getLocation().x, point.y + basicCharacter.getLocation().y);

        String result = mapCreator.moveTextureLocation(oldLocation, newLocation);

        if (result.contains("Success"))
        {
            basicCharacter.setLocation(newLocation);

            if (result.contains("Player") || result.contains("Monster"))
            {
                for (BasicCharacter characterCollided : characters)
                    if (basicCharacter.getLocation().equals(characterCollided.getLocation()))
                        characterCollided.setTexture(map.fightTexture);

            }

        }

    }
    /**
     * Based on the map loaded, spawn monsters
     * @param amount - The amount of monsters to spawn
     * @param level - The level of the monsters
     */
    private void spawnExtraMonsters(int amount, int level)
    {
        if (amount > 5)
            amount = 5;

        for (int i = 0; i < amount; i++)
        {
            BasicCharacter monster = new Monster("MONSTER" + String.valueOf(i + 1), 1);

            monster.setLevel(level);

            monster.setSkills(new Skills("Cyclone", 5, 10), 0);

            monster.setTexture(map.monsterTexture);

            monster.setLocation(mapCreator.getTextureLocations(map.floorTexture).get(mapCreator.getTextureLocations(map.floorTexture).size() - 1));

            characters.add(monster);

            mapCreator.setTextureLocation(monster.getTexture(), monster.getLocation());
        }
    }
    /**
     *  Show maps to user
     */
    public void showMaps()
    {
        int input;

        menuConsole.printToConsole("  You can select one of the follwing maps:\n");
        input = convertToInteger(menuConsole.showMenu(MenuEnum.STARTMAP, mapCreator.getMaps()));
        mapCreator.setMap(mapCreator.getMapFileName(input));
        menuConsole.printToConsole("");
        menuConsole.printToConsole(mapCreator.getMap());
    }
    /**
     *  Convert a string to int
     *  @param string - The string to convert
     */
    private int convertToInteger(String string)
    {
        int value;

        try
        {
            value = Integer.parseInt(string);
        }
        catch(NumberFormatException e)
        {
            return -1;
        }

        return value;

    }
    /**
     *  This methods creates a character
     *  @param characterType - String of either Player or Monster
     */
    public void createCharacter(String characterType)
    {
        if (characterType == "Player")
        {
            menuConsole.printToConsole("  Hi there and thank you for playing!\n\n  Please write a name for your character");

            Player player = new Player(menuConsole.getInput("  Choose your name: "), 3);

            player.setLevel(1);
            player.setSkills(new Skills("Punch", 10, 45), 0);
            player.setSkills(new Skills("Kick", 30, 35), 1);
            player.setSkills(new Skills("!!NUKE!!", 1000, 10000), 2);

            player.setTexture(map.heroTexture);

            showMaps();

            if (mapCreator.getTextureLocations(player.getTexture()).size() == 0)
            {
                if (mapCreator.getTextureLocations(map.floorTexture).size() == 0)
                {
                    menuConsole.getInput("Error using map, no floor textures detected.\nPress 'ENTER' to start over...");

                    characters.clear();

                    createCharacter(characterType = "Player");

                }
                else
                {
                    player.setLocation(mapCreator.getTextureLocations(map.floorTexture).get(0));

                    characters.add(player);

                    mapCreator.setTextureLocation(player.getTexture(), player.getLocation());
                }

            }
            else
            {
                player.setLocation(mapCreator.getTextureLocations(player.getTexture()).get(0));

                characters.add(player);
            }

        }
        else
        {
            Monster monster = new Monster("MONSTER1", 1);

            monster.setLevel(1);

            monster.setSkills(new Skills("Punch", 5, 10), 0);

            monster.setTexture(map.monsterTexture);

            if (mapCreator.getTextureLocations(monster.getTexture()).size() == 0)
            {
                if (mapCreator.getTextureLocations(map.floorTexture).size() == 0)
                {
                    menuConsole.getInput("Error using map, no floor textures detected.\nPress 'ENTER' to start over...");

                    characters.clear();

                    createCharacter(characterType = "Monster");
                }
                else
                {
                    monster.setLocation(mapCreator.getTextureLocations(map.floorTexture).get(mapCreator.getTextureLocations(map.floorTexture).size() - 1));

                    characters.add(monster);

                    mapCreator.setTextureLocation(monster.getTexture(), monster.getLocation());
                }

            }
            else
            {
                for (int i = 0; i < mapCreator.getTextureLocations(map.monsterTexture).size(); i++)
                {
                    monster = new Monster("MONSTER" + String.valueOf(i + 1), 1);

                    monster.setLevel(1);

                    monster.setSkills(new Skills("Basic", 1, 10), 0);

                    monster.setTexture(map.monsterTexture);

                    monster.setLocation(mapCreator.getTextureLocations(monster.getTexture()).get(i));

                    characters.add(monster);

                }

            }

        }

    }
}
