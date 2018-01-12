import hsa.Console;
import java.awt.*;

class usingCardClass
{
    public static void main (String[] args)
    {
	Console c = new Console();
	
	//randInt = (int) (Math.random () * 100) + 1;
	
	//CardClass ca = new CardClass(5, 120, 200, 3, 4);
	
	CardClass ca = new CardClass();
	
	ca.draw(c);
    }
}
