package RPGGame;

import java.awt.event.KeyEvent;

/**
 * Created by POU on 04-02-2016.
 */
public class Combat
{
    public boolean checkCombat()
    {
        boolean combatIs = false;
        double d = Math.random();
        System.out.print(d);
        if (d > 0.9)
        {
            combatIs = true;
        }
        else
        {
            startCombat();
        }
        System.out.print(combatIs);
        return combatIs;
    }

    public void startCombat()
    {

    }
}
