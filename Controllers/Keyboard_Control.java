import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Keyboard_Control extends Controller
{
	Image sprite;
	int cursor_x, cursor_y;
	
	public Keyboard_Control(Magnet[] m, Vector<Entity> e, int x, int y) throws SlickException
	{
		super(m, e, x, y);
		
		sprite = new Image("res/cursor.png");
	}
	
	public void think()
	{
		
	}
	
	public void render()
	{
		sprite.draw(cursor_x*World.SEPARATION+World.OFFSET_X-8, cursor_y*World.SEPARATION+World.OFFSET_Y-8);
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
			setStrength(cursor_x, cursor_y, 100);
		} else if(key==Input.KEY_X)
		{
			setStrength(cursor_x, cursor_y, 0);
		}
	}
}
