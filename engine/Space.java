package engine;

/*
 * Space Class
 * Contains weightings of possible moves from this grid sqaure
 * Contains the actual Object that resides on this grid square
 */

import utils.Config;

public class Space
{
	private int[][] weightedMoves;
	private Nothing thing;
	private Config config;

	public Space(Config configValue)
	{
		config = configValue;
		weightedMoves = new int[3][3];
		fillMoveWeights();
		thing = new Nothing(config);
	}

	private void fillMoveWeights()
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				int weight = 1;
				if(i == 1 && j == 1)
				{
					weight = 0;
				}
				weightedMoves[i][j] = weight;
			}
		}
	}

	public void setWeight(Point posValue, int weightValue)
	{
		weightedMoves[posValue.getX()][posValue.getY()] = weightValue;
	}

	public int getWeight(int xValue, int yValue)
	{
		return weightedMoves[xValue][yValue];
	}

	public Nothing getThing()
	{
		return thing;
	}

	public void setThing(Nothing thingValue)
	{
		thing = thingValue;
	}

	public int[][] getWeights()
	{
		return weightedMoves;
	}

	public void decayWeights()
	{
		for(int[] weightRow : weightedMoves)
		{
			for(int w : weightRow)
			{
				w--;
			}
		}
	}
}
