import hsa.Console;
import java.awt.*;

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
	    cardHeight = 80;
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


    /*public int getMidValueX ()
    {
    int answerX = (int) ((iCentreX + midValX) / 2);
    return answerX;
    }


    public int getMidValueY ()
    {
    int answerY = (int) ((iCentreY + midValY) / 2);
    return answerY;
    }*/

    public void draw (Console c)
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
	    if (suitValue == 1)
	    {
		DiamondClass d = new DiamondClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.red);
		}
		d.setCenter (midValX, midValY);
		d.setHeight ((int) (cardHeight * 0.25));
		//d.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		d.draw (c);
	    }
	    else if (suitValue == 2)
	    {
		ClubClass cl = new ClubClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.black);
		}
		cl.setCenter (midValX, midValY);
		cl.setHeight ((int) (cardHeight * 0.25));
		//cl.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		cl.draw (c);
	    }
	    else if (suitValue == 3)
	    {
		HeartClass h = new HeartClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.red);
		}
		h.setCenter (midValX, midValY);
		h.setHeight ((int) (cardHeight * 0.25));
		//h.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		h.draw (c);
	    }
	    else if (suitValue == 4)
	    {
		SpadeClass s = new SpadeClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.black);
		}
		s.setCenter (midValX, midValY);
		s.setHeight ((int) (cardHeight * 0.25));
		//s.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		s.draw (c);
	    }

	    Color oldColour = getColour ();
	    setColour (Color.black);
	    c.setColour (iColour);
	    c.drawRect (iCentreX, iCentreY, (int) (cardHeight * 0.7), cardHeight);
	    setColour (oldColour);

	    if (faceValue == 1)
	    {
		c.setColor (iColour);
		c.setFont (f);
		c.setColor (iColour);
		c.drawString ("A", iCentreX + 10, iCentreY + fontSize);
	    }

	    else if (faceValue == 11)
	    {
		c.setColor (iColour);
		c.setFont (f);
		c.drawString ("J", iCentreX + 10, iCentreY + fontSize);
	    }
	    else if (faceValue == 12)
	    {
		c.setColor (iColour);
		c.setFont (f);
		c.drawString ("Q", iCentreX + 10, iCentreY + fontSize);
	    }
	    else if (faceValue == 13)
	    {
		c.setColor (iColour);
		c.setFont (f);
		c.drawString ("K", iCentreX + 10, iCentreY + fontSize);
	    }
	    else
	    {
		c.setColor (iColour);
		c.setFont (f);
		String cardValStr = "" + faceValue;
		c.drawString (cardValStr, iCentreX + 10, iCentreY + fontSize);
	    }
	}
	else
	{
	    c.setColour(iColour);
	    c.fillRect(iCentreX, iCentreY, (int)(cardHeight * 0.7), cardHeight);
	    Color oldColour = getColour ();
	    setColour (Color.black);
	    c.setColour (iColour);
	    c.drawRect (iCentreX, iCentreY, (int) (cardHeight * 0.7), cardHeight);
	    setColour (oldColour);
	}
    }


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
	    if (suitValue == 1)
	    {
		DiamondClass d = new DiamondClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.red);
		}
		d.setCenter (midValX, midValY);
		d.setHeight ((int) (cardHeight * 0.25));
		//d.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		d.draw (g);
	    }
	    else if (suitValue == 2)
	    {
		ClubClass cl = new ClubClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.black);
		}
		cl.setCenter (midValX, midValY);
		cl.setHeight ((int) (cardHeight * 0.25));
		//cl.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		cl.draw (g);
	    }
	    else if (suitValue == 3)
	    {
		HeartClass h = new HeartClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.red);
		}
		h.setCenter (midValX, midValY);
		h.setHeight ((int) (cardHeight * 0.25));
		//h.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		h.draw (g);
	    }
	    else if (suitValue == 4)
	    {
		SpadeClass s = new SpadeClass ();
		if (iColour != Color.white)
		{
		    setColour (Color.black);
		}
		s.setCenter (midValX, midValY);
		s.setHeight ((int) (cardHeight * 0.25));
		//s.setWidth ((int) ((cardHeight * 0.25) * (4 / 5)));
		s.draw (g);
	    }

	    Color oldColour = getColour ();
	    setColour (Color.black);
	    g.setColor (iColour);
	    g.drawRect (iCentreX, iCentreY, (int) (cardHeight * 0.7), cardHeight);
	    setColour (oldColour);

	    if (faceValue == 1)
	    {
		g.setColor (iColour);
		g.setFont (f);
		g.setColor (iColour);
		g.drawString ("A", iCentreX + 10, iCentreY + fontSize);
	    }

	    else if (faceValue == 11)
	    {
		g.setColor (iColour);
		g.setFont (f);
		g.drawString ("J", iCentreX + 10, iCentreY + fontSize);
	    }
	    else if (faceValue == 12)
	    {
		g.setColor (iColour);
		g.setFont (f);
		g.drawString ("Q", iCentreX + 10, iCentreY + fontSize);
	    }
	    else if (faceValue == 13)
	    {
		g.setColor (iColour);
		g.setFont (f);
		g.drawString ("K", iCentreX + 10, iCentreY + fontSize);
	    }
	    else
	    {
		g.setColor (iColour);
		g.setFont (f);
		String cardValStr = "" + faceValue;
		g.drawString (cardValStr, iCentreX + 10, iCentreY + fontSize);
	    }
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
	setFaceValue (newFV);
	setSuitValue (newSV);
	isFaceUp = flipped;
	setCardSize (2);
    }
}
