import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;

public class SpiderGame extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    Graphics g;
    Button newGame = new Button ("New Game");
    // Button gameInstruction = new Button ("Instructions");

    DeckClass stack;
    DeckClass fondDeck[] = new DeckClass [8];
    DeckClass tabDeck[] = new DeckClass [10];

    int cardCtr = 0;

    int tabIndex;
    int tabCard;

    boolean drawBoard = false;

    boolean OKtoMove = false;

    public void init ()
    {
	g = getGraphics ();
	setSize (1000, 700);

	initStack ();
	//              initFondation();
	initTableau ();

	add (newGame);
	// add (gameInstruction);

	newGame.addActionListener (this);

	addMouseListener (this);
	addMouseMotionListener (this);
    }


    public void actionPerformed (ActionEvent e)
    {
	Object objSource = e.getSource ();

	if (objSource == newGame)
	{
	    drawBoard = true;
	    repaint ();
	}
	/*
	 * if (objSource == gameInstruction) {
	 *
	 * }
	 */

	repaint ();
    }


    public void paint (Graphics g)
    {
	// System.out.println ("called");

	drawTableau ();
	drawDeck ();

    }


    public void initStack ()
    {
	stack = new DeckClass (2);
	stack.shuffle ();

    }


    public void drawDeck ()
    {
	for (int i = stack.getCardCount () - 1 ; i >= 0 ; i--)
	{
	    stack.setFlip (false, i);
	    stack.draw (g, i, 800, 500);
	}
    }


    public void initFondation ()
    {
	for (int i = 0 ; i < 8 ; i++)
	{
	    fondDeck [i] = new DeckClass ();
	    fondDeck [i].setFlip (false, i);
	    fondDeck [i].draw (g, i, 50 + i * 75, 600);
	}
    }


    public void initTableau ()
    {
	int n, i;
	for (n = 0 ; n < 10 ; n++)
	{
	    tabDeck [n] = new DeckClass ();
	}
	for (n = 0 ; n < 4 ; n++)
	{
	    for (i = 0 ; i < 6 ; i++)
	    {
		initTabPile (n, i);
	    }
	}
	for (n = 4 ; n < 10 ; n++)
	{
	    for (i = 0 ; i < 5 ; i++)
	    {
		initTabPile (n, i);
	    }
	}
	// flipLast ();
    }


    public void drawTableau ()
    {
	flipLast ();
	int n, i;
	for (n = 0 ; n < 10 ; n++)
	{
	    for (i = 0 ; i < tabDeck [n].getCardCount () ; i++)
	    {
		tabDeck [n].draw (g, i, 50 + n * 75, 100 + i * 25);
	    }
	}

	// tabDeck [0].draw (g, 0, 0, 0, false);
	// System.out.println ((tabDeck [0].getTop()).getFaceValue () + ", " + (tabDeck
	// [0].getTop()).getSuitValue ());
    }


    private void initTabPile (int n, int i)
    {
	tabDeck [n].addBot (stack.getTop ());
	stack.removeTop ();
	tabDeck [n].setFlip (false, i);
	// tabDeck [n].draw (g, i, 50 + n * 75, 100 + i * 25, false);
    }


    public void flipLast ()
    {
	int cardIndexToFlip;
	for (int x = 0 ; x < 10 ; x++)
	{
	    cardIndexToFlip = tabDeck [x].getCardCount () - 1;

	    if (cardIndexToFlip >= 0 && !(tabDeck [x].getCard (cardIndexToFlip)).getFlipped ())
	    {
		tabDeck [x].setFlip (true, cardIndexToFlip);
		// tabDeck [x].draw (g, cardIndexToFlip, (this.tabDeck [x]).getCenterX (),
		// (this.tabDeck [x]).getCenterY (), true);
	    }
	}
    }


    public void findMatchingCard (int tabIndex, int tabCard, int x, int y)
    {
	int cardIndexToCheck;
	for (int n = 0 ; n < 10 ; n++)
	{
	    cardIndexToCheck = tabDeck [n].getCardCount () - 1;

	    if (cardIndexToCheck > 0 && (tabDeck [tabIndex].getCard (tabCard)).getFaceValue ()
		    + 1 == (tabDeck [n].getCard (cardIndexToCheck)).getFaceValue () && (tabDeck[n].getCard(cardIndexToCheck)).isPointInside(x,y) == true)
	    {
		tabDeck [n].addBot (tabDeck [tabIndex].getCard (tabCard));
		tabDeck [tabIndex].removeAt (tabCard);
		return;
	    }
	}
    }


    public void mouseMoved (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
	for (int n = 0 ; n < 10 ; n++)
	{
	    for (int i = 0 ; i < tabDeck [n].getCardCount () ; i++)
	    {
		if ((tabDeck [n].getCard (i)).isPointInside (e.getX (), e.getY ()))
		{
		    OKtoMove = true;
		    // (tabDeck[n].getCard(i)).setCenter(e.getX(), e.getY());
		    tabIndex = n;
		    tabCard = i;
		    tabDeck [n].draw (g, i, e.getX (), e.getY ());
		    repaint ();
		}
	    }
	}
    }


    public void mouseReleased (MouseEvent e)
    {
	OKtoMove = false;
	// System.out.println ("released: " + e.getX () + ", " + e.getY ());
	tabDeck [tabIndex].draw (g, tabCard, e.getX (), e.getY ());
	findMatchingCard (tabIndex, tabCard, e.getX(), e.getY());
	repaint ();
    }


    public void mouseDragged (MouseEvent e)
    {
	if (OKtoMove == true)
	{
	    tabDeck [tabIndex].draw (g, tabCard, e.getX (), e.getY ());
	    repaint ();
	}
    }
}
