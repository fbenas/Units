package utils;

public class AnimObject
{
    public AnimObject(GridType gridTypeValue, int crumbsValue, int unitsValue)
    {
        gridType = gridTypeValue;
        crumbs = crumbsValue;
        units = unitsValue;
    }

    public final GridType gridType;
    public final int crumbs;
    public final int units;
}