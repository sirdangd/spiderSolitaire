


import hsa.Console;
import java.awt.*;

public abstract class SuitClass extends ShapeClass
{

    // Override in Shape
    public void setWidth (int iNewWidth)
    {
	super.setWidth (iNewWidth);
	super.setHeight ((int) (5 * iNewWidth / 4));
    }


    // Override in Shape
    public void setHeight (int iNewHeight)
    {
	super.setHeight (iNewHeight);
	super.setWidth ((int) (4 * iNewHeight / 5));
    }
}
