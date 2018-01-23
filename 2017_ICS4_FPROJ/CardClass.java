//programmer : Daniel Dikaleh

//purpose of class : creates cards and can get the characteristics of the card such as its face value, suit value, and whether it is flipped or not

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

class CardClass extends SuitClass
{
    protected int faceValue;
    protected int cardCentreX;
    protected int cardCentreY;
    protected int cardSize;
    protected int suitValue;
    protected int cardHeight;
    protected boolean isShowing;
    protected boolean isFaceUp;
    protected BufferedImage faceUpBi;
    protected BufferedImage faceDownBi;

    public void setFaceValue (int newVal)
    {
	if (newVal >= 1 && newVal <= 13)
	{
	    faceValue = newVal;
	}
	else
	{
	    faceValue = -1;
	}
    }


    public int getFaceValue ()
    {
	return faceValue;
    }


    public void setCardCentre (int x, int y)
    {
	setCenter (x, y);
    }


    public void setCardSize (int cardSize)
    {
	if (cardSize == 1)
	{
	    cardHeight = 60;
	}
	else if (cardSize == 2)
	{
	    cardHeight = 97;
	}
	else if (cardSize == 3)
	{
	    cardHeight = 100;
	}
	else if (cardSize == 4)
	{
	    cardHeight = 120;
	}
	else
	{
	    cardHeight = 0;
	}
    }


    public int getCardSize ()
    {
	return cardHeight;
    }


    public void setSuitValue (int newSuitValue)
    {
	if (newSuitValue >= 1 && newSuitValue <= 4)
	{
	    suitValue = newSuitValue;
	}
    }


    public int getSuitValue ()
    {
	return suitValue;
    }


    public void setFlipped (boolean newFlipped)
    {
	if (isFaceUp == newFlipped)
	{
	    return;
	}
	else
	{
	    isFaceUp = newFlipped;
	}
    }


    public boolean getFlipped ()
    {
	return isFaceUp;
    }


    private String getImageName (int sv, int fv)
    {
	String newSv = "";
	String newFv = "";

	if (sv == 1)
	{
	    newSv = "d";
	}
	else if (sv == 2)
	{
	    newSv = "c";
	}
	else if (sv == 3)
	{
	    newSv = "h";
	}
	else if (sv == 4)
	{
	    newSv = "s";
	}

	if (fv == 1)
	{
	    newFv = "a";
	}
	else if (fv == 10)
	{
	    newFv = "t";
	}
	else if (fv == 11)
	{
	    newFv = "j";
	}
	else if (fv == 12)
	{
	    newFv = "q";
	}
	else if (fv == 13)
	{
	    newFv = "k";
	}
	else
	{
	    newFv = "" + fv;
	}

	return newFv + newSv + ".gif";
    }


    /*
     * public int getMidValueX () { int answerX = (int) ((iCentreX + midValX) / 2);
     * return answerX; }
     *
     *
     * public int getMidValueY () { int answerY = (int) ((iCentreY + midValY) / 2);
     * return answerY; }
     */

    public void draw (Graphics g)
    {

	int x2;
	int y2;
	int midValX;
	int midValY;

	int fontSize = (int) (cardHeight * 0.2);
	Font f = new Font ("SanSerif", Font.PLAIN, fontSize);
	x2 = (int) (iCentreX + (0.7 * cardHeight));
	y2 = iCentreY + cardHeight;
	midValX = (iCentreX + x2) / 2;
	midValY = (iCentreY + y2) / 2;
	if (isFaceUp)
	{
	    g.drawImage (faceUpBi, iCentreX, iCentreY, null);
	}
	else
	{
	    g.drawImage (faceDownBi, iCentreX, iCentreY, null);
	}
    }


    public boolean isPointInside (int x, int y)
    {
	if (x >= getCenterX () && x <= getCenterX () + getWidth () && y >= getCenterY ()
		&& y <= getCenterY () + getHeight ())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public CardClass ()
    {
	faceValue = 12;
	setCardCentre (200, 200);
	suitValue = 2;
	setCardSize (2);
	isShowing = true;
	isFaceUp = true;
    }


    public CardClass (int newFV, int newCCX, int newCCY, int newCS, int newSV)
    {
	setFaceValue (newFV);
	setCardCentre (newCCX, newCCY);
	setCardSize (newCS);
	setSuitValue (newSV);
    }


    public CardClass (int newFV, int newSV, boolean flipped)
    {
	try
	{
	    faceUpBi = ImageIO.read (new File (spiderGame.CODE_BASE.getPath () + "cards/" + getImageName (newSV, newFV)));
	    faceDownBi = ImageIO.read (new File (spiderGame.CODE_BASE.getPath () + "cards/b.gif"));
	}
	catch (IOException e)
	{
	    e.printStackTrace ();
	}
	setFaceValue (newFV);
	setColour (Color.green);
	setSuitValue (newSV);
	isFaceUp = flipped;
	setCardSize (2);
    }
}
