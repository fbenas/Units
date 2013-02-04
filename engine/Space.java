package engine;

/*
 * Space Class
 * Contains weightings of possible moves from this grid sqaure
 * Contains the actual Object that resides on this grid square
 */

import utils.Config;
import utils.GridType;
import java.util.ArrayList;

public class Space
{
	private int[][] weightedMoves;
	private Nothing thing;
	private Config config;
	private ArrayList<Unit> units;

	public Space(Config configValue)
	{
		config = configValue;
		weightedMoves = new int[3][3];
		fillMoveWeights();
		thing = new Nothing(config);
		units = new ArrayList<Unit>();
	}

	private void fillMoveWeights()
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				int weight = 2;
				if(i == 1 && j == 1)
				{
					weight = 1;
				}
				weightedMoves[i][j] = weight;
			}
		}
	}

	public Nothing getThing()
	{
		return thing;
	}

	public void setThing(Nothing thingValue)
	{
		thing = thingValue;
	}

	public int getUnitCount()
	{
		return units.size();
	}

	public Nothing getUnit(String nameValue)
	{
		for(Unit unit : units)
		{
			if(unit.getName() == nameValue)
			{
				return unit;
			}
		}
		return new Nothing(config);
	}

	public void removeUnit(Unit unitValue)
	{
		units.remove(unitValue);
	}

	public void addUnit(Unit unitValue)
	{
		// Check to see if it's already there
		if(getUnit(unitValue.getName()).getType() != GridType.UNIT)
		{
			units.add(unitValue);
		}
	}

	public int[][] getWeights()
	{
		return weightedMoves;
	}

	public int getWeight(int xValue, int yValue)
	{
		return weightedMoves[xValue][yValue];
	}

	public void addWeight(int xValue, int yValue)
	{
		if(weightedMoves[xValue][yValue] < config.MAX_CRUMB_AMOUNT)
		{
			weightedMoves[xValue][yValue] += config.CRUMB_AMOUNT;
		}
	}

	public void decayWeights()
	{
		for(int i=0; i< weightedMoves.length; i++)
		{
			for(int j=0; j<weightedMoves[i].length; j++ )
			{
				if(weightedMoves[i][j] > 2)
				{
					weightedMoves[i][j]-= config.DECAY_AMOUNT;
				}
				else
				{
					weightedMoves[i][j]=1;
				}
			}
		}
	}
}
