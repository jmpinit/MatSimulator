import java.util.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

class World
{
	public static final int OFFSET_X = 72;
	public static final int OFFSET_Y = 72;
	public static final int SEPARATION = 32;
	
	public static final int ARRAY_W = 8;
	public static final int ARRAY_H = 8;
	
	private Magnet[] magnets = new Magnet[ARRAY_W*ARRAY_H];
	private Vector<Entity> ents = new Vector<Entity>();
	
	private Controller controller;
	
	public World() throws SlickException
	{
		for(int y=0; y<ARRAY_H; y++)
		{
			for(int x=0; x<ARRAY_W; x++)
			{
				magnets[y*ARRAY_W+x] = new Magnet(this, x*SEPARATION+OFFSET_X, y*SEPARATION+OFFSET_Y, 10000);
			}
		}
		
		controller = new Goal_Control(magnets, ents, ARRAY_W, ARRAY_H);
	}
	
	public void keyPressed(int key, char c) throws SlickException
	{
		if(key==Input.KEY_R)
		{
			for(int i=0; i<100; i++)
			{
				Ball b = new Ball(this, (int)(Math.random()*400), (int)(Math.random()*400), 1);
				
				b.xAccel(Math.random()*5-2.5);
				b.yAccel(Math.random()*5-2.5);
				
				ents.add(b);
			}
		} else {
			controller.keyPressed(key, c);
		}
	}
	
	public void mousePressed(int button, int x, int y) throws SlickException
	{
		ents.add(new Ball(this, x, y, 1));
	}
	
	public Vector<Entity> getEnts()
	{
		return ents;
	}
	
	public void update() throws SlickException
	{
		controller.think();
		
		for(int i=0; i<ARRAY_W*ARRAY_H; i++)
		{
			magnets[i].update();
		}
		
		for(Entity ent: ents)
		{
			ent.update();
		}
	}
	
	public void render()
	{
		controller.render();
		
		for(int i=0; i<ARRAY_W*ARRAY_H; i++)
		{
			magnets[i].render();
		}
		
		for(Entity ent: ents)
		{
			ent.render();
		}
	}
	
	public Vector<Entity> getNearbyEnts(int x, int y, int radius)
	{
		Vector<Entity> candidates = new Vector<Entity>();
		
		for(Entity ent: ents)
		{
			if(distance(x, y, ent.getX(), ent.getY())<radius)
			{
				candidates.add(ent);
			}
		}
		
		return candidates;
	}
	
	public static double distance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
}
