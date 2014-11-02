import java.util.Vector;

public abstract class Controller
{
	protected Magnet[] magnets;
	protected Vector<Entity> ents = new Vector<Entity>();
	protected int max_x, max_y;
	
	public Controller(Magnet[] m, Vector<Entity> e, int x, int y)
	{
		magnets = m;
		ents = e;
		max_x = x;
		max_y = y;
	}
	
	public abstract void think();
	public abstract void render();
	public abstract void keyPressed(int key, char c);
	
	public void setStrength(int x, int y, int strength)
	{
		if(x>=0&&x<max_x)
		{
			if(y>=0&&y<max_y)
			{
				magnets[y*max_x+x].setMagnitude(strength);
			}
		}
	}
}
