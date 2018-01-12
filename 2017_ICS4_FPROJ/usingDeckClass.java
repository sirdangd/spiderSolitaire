import java.awt.*;
import java.util.*;
import hsa.Console;


public class usingDeckClass 
{
    public static void main(String args[]) 
    {
	Console c = new Console();
	
	DeckClass d = new DeckClass();
	
	d.shuffle();
	d.draw(c);
	
	//System.out.println(d.getCardCount());
	
    }
}
