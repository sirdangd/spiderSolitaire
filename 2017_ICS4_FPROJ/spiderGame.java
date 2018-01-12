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

    boolean drawBoard = false;

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
	    initStack ();
	    initFondation ();
	    initTableau ();
	    drawDeck ();
	}
    }


    public void initStack ()
    {
	stack = new DeckClass (2, Color.green, 50, 800, 500);
	stack.shuffle ();
	//System.out.println (stack.getCardCount ());

    }


    public void drawDeck ()
    {
	stack.draw (g, cardCtr, 800, 500, false);
    }


    public void initFondation ()
    {
	for (int i = 0 ; i < 8 ; i++)
	{
	    fondDeck [i] = new DeckClass ();
	    //g.setColor (Color.black);
	    //g.drawRect ((int) (100 + i * 75), 550, (int) (80 * 0.25), (int) (80 * 0.25) * (4 / 5));
	}
    }


    public void initTableau ()
    {
	for (int n = 0 ; n < 10 ; n++)
	{
	    tabDeck [n] = new DeckClass ();
	    for (int i = 0 ; i < 6 ; i++)
	    {
		if (i == 5 && n < 4)
		{
		    tabDeck [n].addBot (stack.getTop ());
		    //stack.removeAt(cardCtr);
		    stack.removeTop();
		    tabDeck [n].draw (g, i, 50 + n * 75, 100 + i * 50, false);
		    cardCtr++;
		}
		if (i < 5)
		{
		    tabDeck [n].addBot (stack.getTop ());
		    //stack.removeAt(cardCtr);
		    stack.removeTop();
		    tabDeck [n].draw (g, i, 50 + n * 75, 100 + i * 50, false);
		    cardCtr++;
		}

		
	    }
	}
	for(int n = 0 ; n < 10 ; n++)
	{
	    System.out.println(tabDeck[n].getCardCount());
	}
	//flipLast();
    }


    public void flipLast ()
    {
	//boolean foundFlip;
	//int tabCtr;
	int numOfCards;
	for (int x = 0 ; x < 10 ; x++)
	{
	    //foundFlip = false;
	    //tabCtr = 0;
	    numOfCards = tabDeck [x].getCardCount ();
	    if ((tabDeck [x].getCard (numOfCards - 1)).getFlipped () == false)
	    {
		(tabDeck [x].getCard (numOfCards - 1)).setFlipped (true);
		//foundFlip = true;
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
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseDragged (MouseEvent e)
    {
    }
}
