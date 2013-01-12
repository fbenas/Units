public class Point
{

	private int x;
	private int y;

	public Point(int xValue, int yValue)
	{
		x = xValue;
		y = yValue;
	}

	public Point(Point pValue)
	{
		x = pValue.getX();
		y = pValue.getY();
	}

	public Point()
	{
		x = 0;
		y = 0;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setX(int xValue)
	{
		x = xValue;
	}

	public void setY(int yValue)
	{
		y = yValue;
	}

	public void add(int xValue, int yValue)
	{
		x += xValue;
		y += xValue;
	}

	public void sub(int xValue, int yValue)
	{
		x -= xValue;
		y -= yValue;
	}

	public void add(Point pValue)
	{
		x += pValue.getX();
		y += pValue.getY();
	}

	public void sub(Point pValue)
	{
		x -= pValue.getX();
		y -= pValue.getY();
	}
}