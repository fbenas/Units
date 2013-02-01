package utils;

public class AnimObject
{
    public AnimObject(GridType gridTypeValue, int[][] crumbsValue)
    {
        gridType = gridTypeValue;
        crumbs = crumbsValue;
    }

    public final GridType gridType;
    public final int[][] crumbs;
}