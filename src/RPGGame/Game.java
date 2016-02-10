package RPGGAME;

import java.awt.*;
import java.util.ArrayList;
import RPGGAME.MenuConsole;

/**
 * Created by Jim on 2/3/2016.
 */
public class Game
{
    private ArrayList<BasicCharacter> characters;
    private Map map;
    private MapCreator mapCreator;
    private MenuConsole menuConsole;

    public Game()
    {
        menuConsole = new MenuConsole();
        map = new Map();
        characters = new ArrayList<BasicCharacter>();
        mapCreator = new MapCreator();
    }

    public void beginGame()
    {
        menuConsole.printToConsole(" || RPGGAME ||\n");

        int input = convertToInteger(menuConsole.showMenu(MenuEnum.STARTMENU, ""));

        switch (input)
        {
            case 1:  startGame();
                break;

            default: beginGame();
        }
    }

    private void startGame()
    {
        String createPlayerString = "Player";
        String createMonsterString = "Monster";

        createCharacter(createPlayerString);

        createCharacter(createMonsterString);

        menuConsole.getInput("  Map has been loaded, press enter to start the game\n  You will be 'P' on the map, and monsters will be 'M'!");

        gameLoop();

    }

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

    public void showMaps()
    {
        int input;

        menuConsole.printToConsole("  You can select one of the follwing maps:\n");
        input = convertToInteger(menuConsole.showMenu(MenuEnum.STARTMAP, mapCreator.getMaps()));
        mapCreator.setMap(mapCreator.getMapFileName(input));
        menuConsole.printToConsole("");
        menuConsole.printToConsole(mapCreator.getMap());
    }

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

    public void createCharacter(String characterType)
    {
        if (characterType == "Player")
        {
            menuConsole.printToConsole("  Name ");

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
