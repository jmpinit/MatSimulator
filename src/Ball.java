import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball extends Entity
{
	protected static Image sprite;
	
	public Ball(World world, int x, int y, int m) throws SlickException
	{
		myWorld = world;
		
		xPos = x;
		yPos = y;
		
		mass = m;
		
		sprite = new Image("res/ball.png");
	}
	
	public void update()
	{
		xPos += xVel;
		yPos += yVel;
	}
	
	public void render()
	{
		sprite.draw((int)xPos-8, (int)yPos-8);
	}
}
