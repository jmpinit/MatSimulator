import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MatSimulator extends BasicGame
{
	World theWorld;
	
    public Mat_Simulator()
    {
        super("M.A.T. Simulator");
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException
    {
    	theWorld = new World();
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException     
    {
    	theWorld.update();
    }
 
    public void render(GameContainer gc, Graphics g) throws SlickException 
    {
    	theWorld.render();
    }
    
    @Override
    public void keyPressed(int key, char c)
    {
    	try {
			theWorld.keyPressed(key, c);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void mousePressed(int button, int x, int y)
    {
    	try {
			theWorld.mousePressed(button, x, y);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }
 
    public static void main(String[] args) throws SlickException
    {
    	AppGameContainer app = new AppGameContainer(new Mat_Simulator());
        app.setDisplayMode(400, 400, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
