import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;

public class spiderGame extends Applet implements ActionListener, MouseListener, MouseMotionListener {
	Graphics g;
	Button newGame = new Button("New Game");
	// Button gameInstruction = new Button ("Instructions");

	DeckClass stack;
	DeckClass fondDeck[] = new DeckClass[8];
	DeckClass tabDeck[] = new DeckClass[10];
	DeckClass tempDeck;

	int cardCtr = 0;

	int tabIndex;
	int tabCard;

	boolean drawBoard = false;

	boolean OKtoMove = false;

	public void init() {
		g = getGraphics();
		setSize(1000, 700);

		initStack();
		//initFondation ();
		initTableau();

		add(newGame);
		// add (gameInstruction);

		newGame.addActionListener(this);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object objSource = e.getSource();

		if (objSource == newGame) {
			drawBoard = true;
			repaint();
		}

		repaint();
	}

	public void paint(Graphics g) {
		// System.out.println ("called");

		drawTableau();
		drawDeck();

	}

	public void initStack() {
		stack = new DeckClass(2);
		stack.shuffle();

	}

	public void drawDeck() {
		for (int i = stack.getCardCount() - 1; i >= 0; i--) {
			stack.setFlip(false, i);
			stack.draw(g, i, 800, 500);
		}
	}

	public void initFondation() {
		for (int i = 0; i < 8; i++) {
			fondDeck[i] = new DeckClass();
			fondDeck[i].draw(g, i, 50 + i * 75, 600);
		}
	}

	public void initTableau() {
		int n, i;
		for (n = 0; n < 10; n++) {
			tabDeck[n] = new DeckClass();
		}
		for (n = 0; n < 4; n++) {
			for (i = 0; i < 6; i++) {
				initTabPile(n, i);
			}
		}
		for (n = 4; n < 10; n++) {
			for (i = 0; i < 5; i++) {
				initTabPile(n, i);
			}
		}
	}

	public void drawTableau() {
		flipLast();
		int n, i;
		for (n = 0; n < 10; n++) {
			for (i = 0; i < tabDeck[n].getCardCount(); i++) {
				tabDeck[n].draw(g, i, 50 + n * 75, 100 + i * 25);
			}
		}
	}

	private void initTabPile(int n, int i) {
		tabDeck[n].addBot(stack.getTop());
		stack.removeTop();
		tabDeck[n].setFlip(false, i);
	}

	public void flipLast() {
		int cardIndexToFlip;
		for (int x = 0; x < 10; x++) {
			cardIndexToFlip = tabDeck[x].getCardCount() - 1;

			if (cardIndexToFlip >= 0 && !(tabDeck[x].getCard(cardIndexToFlip)).getFlipped()) {
				tabDeck[x].setFlip(true, cardIndexToFlip);
			}
		}
	}

	public void findMatchingCard(int x, int y) {
		int cardIndexToCheck;
		for (int n = 0; n < 10; n++) {
			cardIndexToCheck = tabDeck[n].getCardCount() - 1;

			if (cardIndexToCheck > 0
					&& (tempDeck.getTop()).getFaceValue() + 1 == (tabDeck[n].getCard(cardIndexToCheck)).getFaceValue()
					&& (tabDeck[n].getCard(cardIndexToCheck)).isPointInside(x, y) == true) {
				tabDeck[n].addBot(tempDeck.getTop());
				tempDeck.removeAt(0);
				return;
			}
		}
	}

	public void checkIfGroup(int tab, int whichCard, int lastCard) {
		for (int i = whichCard; i < lastCard; i++) {
			if((tabDeck[tab].getCard(i).getSuitValue() == (tabDeck[tab].getCard(i+1).getSuitValue() && (tabDeck[tab].getCard(i).getFaceValue() + 1 == (tabDeck[tab].getCard(i+1).getFaceValue()) {
				tempDeck.addTop(tabDeck[tab].getCard(i+1));
				tempDeck.addTop(tabDeck[tab].getCard(i));
			} else {
				for (int n = 0 ; n < tempDeck.getCardCount() ; n++) {
					tempDeck.removeAt(n);
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		int lastCard;
		for (int n = 0; n < 10; n++) {
			lastCard = tabDeck[n].getCardCount() - 1;
			for (int i = 0; i < lastCard; i++) {
				if ((tabDeck[n].getCard(i)).isPointInside(e.getX(), e.getY())) {
					checkIfGroup(n, i, lastCard);
				}
			}
			if ((tabDeck[n].getCard(lastCard)).isPointInside(e.getX(), e.getY())) {
				OKtoMove = true;
				tabIndex = n;
				tabCard = lastCard;
				tempDeck = new DeckClass();
				tempDeck.addTop(tabDeck[n].getCard(lastCard));
				tabDeck[n].removeAt(lastCard);
				tempDeck.draw(g, 0, e.getX(), e.getY());
				repaint();
			}
		}
		if ((stack.getCard(0)).isPointInside(e.getX(), e.getY())) {
			for (int n = 0; n < 10; n++) {
				tabDeck[n].addBot(stack.getTop());
				stack.removeTop();
				repaint();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (OKtoMove) {
			OKtoMove = false;
			for (int n = 0; n < temDeck.getCardCount(); n++) {
				tempDeck.draw(g, n, e.getX(), e.getY() + 25 * n);
			}
			tempDeck.draw(g, 0, e.getX(), e.getY());
			findMatchingCard(e.getX(), e.getY());
			repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (OKtoMove == true) {
			for (int n = 0; n < temDeck.getCardCount(); n++) {
				tempDeck.draw(g, n, e.getX(), e.getY() + 25 * n);
			}
			repaint();
		}
	}
}
