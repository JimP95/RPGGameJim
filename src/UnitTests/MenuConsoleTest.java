package UnitTests;

import RPGGame.MenuConsole;
import RPGGame.MenuEnum;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jim on 2/19/2016.
 */
public class MenuConsoleTest {

    MenuConsole menuConsole = new MenuConsole();

    @Test
    public void testShowMenu() throws Exception
    {
        // Ask Michael about waiting for console to finish
        String result = menuConsole.showMenu(MenuEnum.STARTMENU, "");
        assertEquals(" [1]StartGame\n\n  ", result);
    }
}