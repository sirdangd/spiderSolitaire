import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;

public class spiderGame extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    Graphics g;
    Button newGame = new Button ("New Game");
    //Button gameInstruction = new Button ("Instructions");

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

	add (newGame);
	//add (gameInstruction);

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
	}
	/*if (objSource == gameInstruction)
	{

	}*/

	repaint ();
    }


    public void paint (Graphics g)
    {
	if (drawBoard)
	{
	    //System.out.println ("frist line");
	    initStack ();
	    initFondation ();
	    initTableau ();
	    drawDeck ();
	    drawBoard = false;
	    /*for (int n = 0 ; n < 10 ; n++)
	    {
		System.out.println (tabDeck [n].getCardCount ());
	    }
	    for (int i = 0 ; i < 8 ; i++)
	    {
		System.out.println(fondDeck [i].getCardCount());
	    }
	    System.out.println(stack.getCardCount());*/
	}
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
	    stack.draw (g, i, 800, 500, false);
	}
    }


    public void initFondation ()
    {
	for (int i = 0 ; i < 8 ; i++)
	{
	    fondDeck [i] = new DeckClass ();
	    fondDeck [i].draw (g, i, 50 + i * 75, 600, false);
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
	flipLast ();
    }


    private void initTabPile (int n, int i)
    {
	tabDeck [n].addBot (stack.getTop ());
	stack.removeTop ();
	tabDeck [n].draw (g, i, 50 + n * 75, 100 + i * 25, false);
    }


    public void flipLast ()
    {
	int cardIndexToFlip;
	for (int x = 0 ; x < 10 ; x++)
	{
	    cardIndexToFlip = tabDeck [x].getCardCount () - 1;

	    if (cardIndexToFlip > 0 && !(tabDeck [x].getCard (cardIndexToFlip)).getFlipped ())
	    {
		//(tabDeck [x].getCard (cardIndexToFlip)).setFlipped (true);
		tabDeck [x].draw (g, cardIndexToFlip, (this.tabDeck [x]).getCenterX (), (this.tabDeck [x]).getCenterY (), true);
		//draw()
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
		    //(tabDeck[n].getCard(i)).setCenter(e.getX(), e.getY());
		    tabIndex = n;
		    tabCard = i;
		    tabDeck [n].draw (g, i, e.getX (), e.getY (), true);
		    //repaint ();
		}
	    }
	}
    }


    public void mouseReleased (MouseEvent e)
    {
	OKtoMove = false;
	//System.out.println ("released: " + e.getX () + ", " + e.getY ());
	tabDeck [tabIndex].draw (g, tabCard, e.getX (), e.getY (), true);
	//repaint ();
    }


    public void mouseDragged (MouseEvent e)
    {
	if (OKtoMove == true)
	{
	    tabDeck [tabIndex].draw (g, tabCard, e.getX (), e.getY (), true);
	    repaint ();
	}
    }
}
