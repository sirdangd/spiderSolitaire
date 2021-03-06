//programmer : Daniel Dikaleh

//purpose of class : creates a Vector called deck, used to add cards inside vector

import java.awt.*;
import java.util.*;

public class DeckClass extends ShapeClass
{

    private Vector deck;

    private void initDeck (int NumOfDecks)
    {

	for (int i = 1 ; i < 14 ; i++)
	{
	    for (int j = 1 ; j < 5 ; j++)
	    {
		for (int k = 0 ; k < NumOfDecks ; k++)
		{
		    CardClass cc = new CardClass (i, j, false);
		    deck.addElement (cc);
		}
	    }
	}
    }


    public boolean isEmpty ()
    {
	return deck.isEmpty ();
    }


    public int getCardCount ()
    {
	deck.trimToSize ();
	return deck.size ();
    }


    public void shuffle ()
    {
	if (!isEmpty ())
	{
	    for (int i = 1 ; i < getCardCount () * 25 ; i++)
	    {
		addTop (getAndRemove ((int) (Math.random () * getCardCount ())));
	    }
	}
    }


    public void draw (Graphics g)
    {
	standardize ();
	if (isEmpty ())
	{
	    g.setColor (super.getColour ());
	    g.drawRect (super.getCenterX () - super.getWidth () / 2, super.getCenterY () - super.getHeight () / 2,
		    super.getWidth (), super.getHeight ());
	}
	else if (!isEmpty ())
	{
	    getTop ().draw (g);
	}
    }


    public void draw (Graphics g, int i, int newX, int newY)
    {
	super.setCenter (newX, newY);
	//standardize ();
	getCard (i).setCenter (newX, newY);

	if (isEmpty ())
	{
	    Color oldColour = super.getColour ();
	    super.setColour (Color.black);
	    g.setColor (super.getColour ());
	    g.drawRect (super.getCenterX () - super.getWidth () / 2, super.getCenterY () - super.getHeight () / 2,
		    super.getWidth (), super.getHeight ());
	    super.setColour (oldColour);
	}
	if (!isEmpty ())
	{

	    // getCard (i).setFlipped (flipped);
	    getCard (i).draw (g);
	}
    }


    public void setFlip (boolean newFlip, int index)
    {
	getCard (index).setFlipped (newFlip);
    }


    public void addTop (CardClass cc)
    {
	deck.add (0, cc);
    }


    public void addBot (CardClass cc)
    {
	deck.addElement (cc);
    }


    public void addAt (CardClass cc, int i)
    {
	if (i >= 0 && i < getCardCount ())
	{
	    deck.add (i, cc);
	}
	else if (i >= getCardCount ())
	{
	    addBot (cc);
	}
    }


    public void removeTop ()
    {
	if (getCardCount () >= 1)
	{
	    deck.remove (0);
	}
    }


    public void removeBot ()
    {
	deck.remove (getCardCount () - 1);
    }


    public void removeAt (int num)
    {
	deck.remove (num);
    }


    public CardClass getCard (int num)
    {
	if (!deck.isEmpty () && num >= 0 && num <= getCardCount () - 1)
	{
	    return (CardClass) deck.elementAt (num);
	}
	else
	{
	    return null;
	}


    }


    public CardClass getAndRemove (int i)
    {
	if (!isEmpty () && i >= 0 && i < getCardCount ())
	{
	    return (CardClass) deck.remove (i);
	}
	else
	{
	    return (CardClass) deck.remove (0);
	}
    }


    public CardClass getTop ()
    {
	return (CardClass) getCard (0);
    }


    public CardClass getBot ()
    {
	if (!isEmpty ())
	{
	    return getCard (getCardCount () - 1);
	}
	else
	{

	    return null;
	}
    }


    public void standardize ()
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    ((CardClass) deck.elementAt (i)).setHeight (super.getHeight ());
	    ((CardClass) deck.elementAt (i)).setCenter (super.getCenterX (), super.getCenterY ());
	}

    }


    public void swap (int a, int b)
    {
	if (a >= 0 && b >= 0 && a < getCardCount () && b < getCardCount ())
	{
	    Collections.swap (deck, a, b);
	}
    }


    public DeckClass ()
    {
	super (300, 300, 100, 70, Color.green);
	deck = new Vector (104);
    }


    public DeckClass (int numOfDecks)
    {
	super (50, 20, 100, 70, Color.green);
	deck = new Vector (104);
	initDeck (numOfDecks);
    }


    public DeckClass (Color clr, int h, int x, int y)
    {
	super (x, y, h, (int) (h * 0.7), clr);
	deck = new Vector (104);
    }


    public DeckClass (int NumOfDecks, Color clr, int h, int x, int y)
    {
	super (x, y, h, (int) (h * 0.7), clr);
	deck = new Vector (104);
	initDeck (NumOfDecks);
    }
}
