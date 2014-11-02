import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Goal_Control extends Controller
{
	Image ImgCursor, ImgGoal;
	private int cursor_x, cursor_y;
	private int goal_x, goal_y;
	//private double xLast, yLast;
	
	public Goal_Control(Magnet[] m, Vector<Entity> e, int x, int y) throws SlickException
	{
		super(m, e, x, y);
		
		ImgCursor = new Image("res/cursor.png");
		ImgGoal = new Image("res/goal.png");
	}
	
	public void think()
	{
		for(int i=0; i<max_x*max_y; i++)
		{
			magnets[i].setMagnitude(0);
		}
		
		if(ents.size()>0)
		{
			Entity tracked = ents.get(0);
			int x = ((int)tracked.getX()-World.OFFSET_X)/World.SEPARATION;
			int y = ((int)tracked.getY()-World.OFFSET_Y)/World.SEPARATION;
			
			magnetLine(100, goal_x, goal_y, x, y);
		}
	}
	
	private void magnetLine(int m, int x0, int y0, int x1, int y1)
	{
		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);
		
		int sx, sy;
		
		if(x0<x1)
			sx = 1;
		else
			sx = -1;
		
		if(y0<y1)
			sy = 1;
		else
			sy = -1;
		
		int err = dx-dy;
		
		while(!(x0==x1&&y0==y1))
		{
			if(x0>=0&&x0<max_x&&y0>=0&&y0<max_y)
				magnets[y0*max_x+x0].setMagnitude(m);
			
			int e2 = 2*err;
			if(e2>-dy)
			{
				err = err-dy;
				x0 = x0+sx;
			}
			
			if(e2<dx)
			{
				err = err+dx;
				y0 = y0+sy;
			}
		}
	}
	
	public void render()
	{
		ImgGoal.draw(goal_x*World.SEPARATION+World.OFFSET_X-10, goal_y*World.SEPARATION+World.OFFSET_Y-10);
		ImgCursor.draw(cursor_x*World.SEPARATION+World.OFFSET_X-8, cursor_y*World.SEPARATION+World.OFFSET_Y-8);
	}
	
	public void keyPressed(int key, char c)
	{
		if(key==Input.KEY_LEFT)
		{
			if(cursor_x>0)
			{
				cursor_x--;
			}
		} else if(key==Input.KEY_UP)
		{
			if(cursor_y>0)
			{
				cursor_y--;
			}
		} else if(key==Input.KEY_RIGHT)
		{
			if(cursor_x<max_x-1)
			{
				cursor_x++;
			}
		} else if(key==Input.KEY_DOWN)
		{
			if(cursor_y<max_y-1)
			{
				cursor_y++;
			}
		} else if(key==Input.KEY_Z)
		{
			setGoal(cursor_x, cursor_y);
		}
	}
	
	private void setGoal(int x, int y)
	{
		goal_x = x;
		goal_y = y;
	}
}
