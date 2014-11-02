import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Magnet extends Entity
{
	protected static Image sprite_on;
	protected static Image sprite_off;
	
	private int max_strength = 10;
	private int magnitude = 0;
	
	public Magnet(World world, int x, int y, int strength) throws SlickException
	{
		myWorld = world;
		
		xPos = x;
		yPos = y;
		
		max_strength = strength;
		
		sprite_on = new Image("res/magnet_on.png");
		sprite_off = new Image("res/magnet_off.png");
	}
	
	public void update()
	{
		/*
		if(Math.random()*100>70)
		{
			magnitude = 10;
		} else {
			magnitude = 0;
		}*/
		
		Vector<Entity> ents = myWorld.getEnts();
		
		for(Entity ent: ents)
		{
			double distance = World.distance(xPos, yPos, ent.getX(), ent.getY());
			double xDist = xPos-ent.getX();
			double yDist = yPos-ent.getY();
			
			if(distance>32&&magnitude>0)
			{
				//force = q1q2/(4piR^2)
				double accel = magnitude/(4*Math.PI*(distance*distance));
				double angle = Math.atan(yDist/xDist);
				
				if(xDist<0)
					accel = -accel;
				
				ent.xAccel((float)(Math.cos(angle)*accel));
				ent.yAccel((float)(Math.sin(angle)*accel));
			}
		}
	}
	
	public void render()
	{
		if(magnitude>0)
		{
			sprite_on.draw((int)xPos-8, (int)yPos-8);
		} else {
			sprite_off.draw((int)xPos-8, (int)yPos-8);
		}
	}
	
	public void setMagnitude(int m)
	{
		if(m<max_strength)
		{
			magnitude = m;
		} else {
			magnitude = max_strength;
		}
	}
	
	public int getField()
	{
		return magnitude;
	}
}
