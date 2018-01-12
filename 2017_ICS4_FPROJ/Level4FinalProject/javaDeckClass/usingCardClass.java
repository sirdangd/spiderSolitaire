import hsa.Console;
import java.awt.*;

class usingCardClass
{
    public static void main (String[] args)
    {
	Console c = new Console();
	
	//randInt = (int) (Math.random () * 100) + 1;
	
	CardClass ca = new CardClass(10, 3, true);
	
	//CardClass ca = new CardClass();
	
	ca.draw(c);
    }
}
