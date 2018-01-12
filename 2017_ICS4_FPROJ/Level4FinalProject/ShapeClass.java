import hsa.Console;
import java.awt.*;

public abstract class ShapeClass
{
    protected int iCentreX, iCentreY, iHeight, iWidth;
    protected Color iColour;

    public void setWidth (int iNewWidth)
    {
	iWidth = iNewWidth;
    }


    public void setHeight (int iNewHeight)
    {
	iHeight = iNewHeight;
    }


    public void setCenter (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
    }


    public void setColour (Color cNewColor)
    {

	iColour = cNewColor;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCenterX ()
    {
	return iCentreX;
    }


    public int getCenterY ()
    {
	return iCentreY;
    }


    public Color getColour ()
    {
	return iColour;
    }


    public void erase (Console c)
    {
	Color cOldColour = getColour ();
	iColour = Color.white;
	delay (3000);
	draw (c);
	iColour = cOldColour;
    }


    public abstract void draw (Console c);

    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }


    public ShapeClass ()
    {
	iCentreX = 80;
	iCentreY = 80;
	iHeight = 80;
	iWidth = 80;
	iColour = Color.black;
    }


    public ShapeClass (int newX, int newY, int newH, int newW, Color newClr)
    {
	iCentreX = newX;
	iCentreY = newY;
	iHeight = newH;
	iWidth = newW;
	iColour = newClr;
    }
}
