/*
 * A public interface for movement on the ground
 */
public interface Move
{
	/*
	 * Thing movement methods and variables
	 * 
	*/

	// Move method gets to decide how to move, then make the required movements
	public abstract boolean move(Ground groundValue);

	// END movement methods and variables
	
}
