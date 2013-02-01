package engine;

/*
 * A public interface for movement of an object through a grid
 */

public interface Move
{
    // The move method contains the information required
    // to decide how the object will move
    // A Ground object, containing the contents of a grid
    // is passed as a parameter so the object has perception
    // of the grid that it resides in.
    public abstract boolean move(Space[][] gridSpaceValue);
}