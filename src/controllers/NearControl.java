import java.util.Vector;

import org.newdawn.slick.SlickException;

public class NearControl extends Controller
{
	public NearControl(Magnet[] m, Vector<Entity> e, int x, int y) throws SlickException
	{
		super(m, e, x, y);
	}
	
	public void think()
	{
		for(int y=0; y<max_y; y++)
		{
			for(int x=0; x<max_x; x++)
			{
				magnets[y*max_x+x].setMagnitude(0);
				
				for(Entity ent: ents)
				{
					if(World.distance(x*World.SEPARATION+World.OFFSET_X, y*World.SEPARATION+World.OFFSET_Y, ent.getX(), ent.getY())<32)
					{
						magnets[y*max_x+x].setMagnitude(10);
					}
				}
			}
		}
	}
	
	public void render()
	{
		//sprite.draw(cursor_x*32+71, cursor_y*32+71);
	}
	
	public void keyPressed(int key, char c)
	{
		//nothing
	}
}
