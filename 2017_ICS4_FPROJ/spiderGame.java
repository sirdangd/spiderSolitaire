//programer : Daniel Dikaleh

//Game Name : Spider Solitare

import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

public class spiderGame extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    Graphics g;
    //Button newGame = new Button ("New Game");
    // Button gameInstruction = new Button ("Instructions");
    TextField score = new TextField (10);
    long startTime = System.currentTimeMillis ();
    TextField timer = new TextField (10);

    DeckClass stack; //deck class used for the main stack
    DeckClass fondDeck[] = new DeckClass [8]; //deck class used for foundation deck
    DeckClass tabDeck[] = new DeckClass [10]; //deck class used for the tableau
    DeckClass tempDeck; //deck class used for temporary deck
    
    public static URL CODE_BASE;

    int cardCtr = 0;
    int fondCtr = 0; //counter used to know which foundation I am currently on

    int fondSeqScore = 0;
    int faceUpScore = 0;

    int tabIndex; //global counter to know which tab colounm I am currently on | used for tempDeck
    int tabCard; //global counter to know which card index I am currently on | used for tempDeck

    boolean drawBoard = false;

    boolean OKtoMove = false;

    public void init ()
    {
	CODE_BASE = getCodeBase();    
    
	g = getGraphics ();
	setSize (1000, 700);
	setBackground(Color.green);

	initStack (); //initializes stack
	initFondation (); //initializes foundation
	initTableau (); //initializes tableau

	//add (newGame);
	// add (gameInstruction);
	add (score);
	add (timer);

	//newGame.addActionListener (this);

	addMouseListener (this);
	addMouseMotionListener (this);


    }


    public void actionPerformed (ActionEvent e)
    {
	Object objSource = e.getSource ();

	/*if (objSource == newGame)
	{
	    drawBoard = true;
	    repaint ();
	}*/

	repaint ();
    }


    public void paint (Graphics g)
    {
	//constatly updates the board

	int elapsedSeconds = (int) (System.currentTimeMillis () - startTime) / 1000;
	timer.setText ("Time: " + String.valueOf (elapsedSeconds));

	drawTableau ();
	drawDeck ();
	checkFondation ();
	drawFond ();


    }


    public void initStack ()
    {
	stack = new DeckClass (2);
	stack.shuffle ();
	//initializes 2 decks (104 cards) and shuffles the deck

    }


    public void drawDeck ()  //draws the stack in the bottom right corner
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
	    //fondDeck [i].draw (g, i, 50 + i * 75, 600);
	}
    }


    public void drawFond ()
    {
	for (int i = 0 ; i < 8 ; i++)
	{
	    if (!fondDeck [i].isEmpty ())
	    {
		for (int n = 0 ; n < 13 ; n++)
		{
		    fondDeck [i].draw (g, n, 50 + i * 75, 500);
		}
	    }
	}
    }


    public void initTableau ()  //makes a new DeckClass() in each index of the array and initializes cards inside it
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
	for (n = 0 ; n < 10 ; n++)
	{
	    (tabDeck [n].getBot ()).setFlipped (true);
	}
    }


    public void drawTableau ()  //used to draw the tableau
    {

	int n, i;
	for (n = 0 ; n < 10 ; n++)
	{
	    for (i = 0 ; i < tabDeck [n].getCardCount () ; i++)
	    {
		tabDeck [n].draw (g, i, 50 + n * 85, 100 + i * 25);
	    }
	}
    }


    private void initTabPile (int n, int i)  //adds cards in tableau
    {
	tabDeck [n].addBot (stack.getTop ());
	stack.removeTop ();
	tabDeck [n].setFlip (false, i);
    }


    public void flipLast ()  //everytime mouse is released, it goes through all the tableaus and flips the last
    {
	int cardIndexToFlip;
	for (int x = 0 ; x < 10 ; x++)
	{
	    cardIndexToFlip = tabDeck [x].getCardCount () - 1;

	    if (cardIndexToFlip >= 0 && !(tabDeck [x].getCard (cardIndexToFlip)).getFlipped ())
	    {
		tabDeck [x].setFlip (true, cardIndexToFlip);
		faceUpScore = faceUpScore + 150;
	    }
	}
    }


    public void checkFondation ()  //checks tabealus constantly to see if foundation was created
    {
	boolean completed = false;
	for (int n = 0 ; n < 10 ; n++)
	{
	    CardClass lastCard = tabDeck [n].getBot ();
	    if (tabDeck [n].getCardCount () >= 13)
	    {
		for (int i = tabDeck [n].getCardCount () - 1 ; i > tabDeck [n].getCardCount () - 14 ; i--)
		{
		    if (tabDeck [n].getCard (i) != null && tabDeck [n].getCard (i) != null && (tabDeck [n].getCard (i)).getSuitValue () == (tabDeck [n].getCard (i + 1)).getSuitValue () && (tabDeck [n].getCard (i)).getFaceValue () + 1 == (tabDeck [n].getCard (i + 1)).getFaceValue () && (tabDeck [n].getCard (i + 1)).getFlipped ())
		    {
			if (i == tabDeck [n].getCardCount () - 14)
			{
			    fondDeck [fondCtr].addTop (tabDeck [n].getCard (i));
			    fondDeck [fondCtr].addTop (tabDeck [n].getCard (i + 1));
			    completed = true;
			}
			else
			{
			    fondDeck [fondCtr].addTop (tabDeck [n].getCard (i));
			}
		    }
		    else
		    {
			for (int x = 0 ; x < fondDeck [fondCtr].getCardCount () ; x++)
			{
			    fondDeck [fondCtr].removeTop ();
			}
			completed = false;
			break;
		    }
		}
		if (completed)
		{
		    for (int i = tabDeck [n].getCardCount () - 1 ; i > tabDeck [n].getCardCount () - 14 ; i--)
		    {
			fondDeck [fondCtr].removeBot ();
		    }
		    fondSeqScore = fondSeqScore + 1040;
		    fondCtr++;
		}
	    }
	}
    }


    public void findMatchingCard (int x, int y)  //used to see if card that I dragged is on another card or not, if so, it checks if it can match or not, if not it goes back to its original place
    {
	int len;
	for (int n = 0 ; n < 10 ; n++)
	{
	    CardClass lastCard = tabDeck [n].getBot ();
	    if (lastCard != null && lastCard.isPointInside (x, y)
		    && (tempDeck.getTop ()).getFaceValue () + 1 == lastCard.getFaceValue ())
	    {
		len = tempDeck.getCardCount ();
		for (int i = 0 ; i < len ; i++)
		{
		    tabDeck [n].addBot (tempDeck.getTop ());
		    tempDeck.removeTop ();
		}
		return;
	    }

	}
	len = tempDeck.getCardCount ();
	for (int i = 0 ; i < tempDeck.getCardCount () ; i++)
	{
	    tabDeck [tabIndex].addBot (tempDeck.getTop ());
	    tempDeck.removeTop ();
	}
    }


    public void checkIfGroup (int tab, int whichCard, int lastCard)  //if i press a card that is not the last card on a tableau it checks if what i clicked can be turned into a group or not
    {
	boolean completed = false;
	for (int i = whichCard ; i < lastCard ; i++)
	{
	    if (tabDeck [tab].getCard (i) != null && tabDeck [tab].getCard (i + 1) != null
		    && (tabDeck [tab].getCard (i)).getSuitValue () == (tabDeck [tab].getCard (i + 1)).getSuitValue ()
		    && (tabDeck [tab].getCard (i)).getFaceValue () == (tabDeck [tab].getCard (i + 1)).getFaceValue () + 1)
	    {
		if (i == lastCard - 1)
		{
		    tempDeck.addBot (tabDeck [tab].getCard (i));
		    tempDeck.addBot (tabDeck [tab].getCard (i + 1));
		    completed = true;
		}
		else
		{
		    tempDeck.addBot (tabDeck [tab].getCard (i));
		}
	    }
	    else
	    {
		for (int n = 0 ; n < tempDeck.getCardCount () ; n++)
		{
		    tempDeck.removeTop ();
		}
		completed = false;
		break;
	    }
	}
	if (completed)
	{
	    for (int i = whichCard ; i <= lastCard ; i++)
	    {
		tabDeck [tab].removeBot ();
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
	int lastCard;
	for (int n = 0 ; n < 10 ; n++)
	{
	    lastCard = tabDeck [n].getCardCount () - 1;
	    if ((tabDeck [n].getCard (lastCard)).isPointInside (e.getX (), e.getY ()) == true)
	    {
		OKtoMove = true;
		tabIndex = n;
		tabCard = lastCard;
		tempDeck = new DeckClass ();
		tempDeck.addTop (tabDeck [n].getCard (lastCard));
		tabDeck [n].removeAt (lastCard);
		tempDeck.draw (g, 0, e.getX (), e.getY ());
		repaint ();
		return;
	    }
	    for (int i = 0 ; i < lastCard ; i++)
	    {
		if (tabDeck [n].getCard (i) != null && (tabDeck [n].getCard (i)).isPointInside (e.getX (), e.getY ()) == true
			&& (tabDeck [n].getCard (i)).getFlipped () == true)
		{
		    OKtoMove = true;
		    tabIndex = n;
		    tabCard = i;
		    tempDeck = new DeckClass ();
		    checkIfGroup (n, i, lastCard);
		    repaint ();
		}
	    }

	}
	if ((stack.getCard (0)).isPointInside (e.getX (), e.getY ()))
	{
	    for (int n = 0 ; n < 10 ; n++)
	    {
		tabDeck [n].addBot (stack.getTop ());
		stack.removeTop ();
		repaint ();
	    }
	    flipLast ();
	}
    }


    public void mouseReleased (MouseEvent e)
    {
	if (OKtoMove)
	{
	    OKtoMove = false;
	    if (!tempDeck.isEmpty ())
	    {
		for (int n = 0 ; n < tempDeck.getCardCount () ; n++)
		{
		    tempDeck.draw (g, n, e.getX (), e.getY () + 25 * n);
		}
		findMatchingCard (e.getX (), e.getY ());
	    }
	    flipLast ();
	    score.setText ("Score: " + (fondSeqScore + faceUpScore));
	    repaint ();
	}
    }


    public void mouseDragged (MouseEvent e)
    {
	if (OKtoMove == true)
	{
	    for (int n = 0 ; n < tempDeck.getCardCount () ; n++)
	    {
		tempDeck.draw (g, n, e.getX (), e.getY () + 25 * n);
	    }
	    repaint ();
	}
    }
}
