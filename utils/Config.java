package utils;

/*
 * public Class Config is used to initalize configuration parameters
 * for the whole system.
 *
 * Methods are added to simply aid the access of these final constants.
 *
 * Config also contains the Debugger Object
 */

public class Config
{
    /* CONSTUCTORS */

    public Config(int debugLevelValue)
    {
        GRID_WIDTH = 40;
        GRID_HEIGHT = 40;
        RESOURCE_AMOUNT = 100;
        CARRY_AMOUNT = 10;
        GAME_TIME = 1000;
        DEBUG = new Logger(debugLevelValue);
        DEBUG.debug("Config", "Debugger set with debugging value of " + debugLevelValue, 1); 
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/

    public final int GRID_WIDTH;
    public final int GRID_HEIGHT;
    public final int CARRY_AMOUNT; 
    public final int GAME_TIME;
    public Logger DEBUG;
    public final int RESOURCE_AMOUNT;
    /* END PRIVATE */
}