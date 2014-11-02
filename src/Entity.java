public abstract class Entity
{
	protected World myWorld;
	
	protected int mass;
	
	protected double xPos;
	protected double yPos;
	protected double xVel;
	protected double yVel;
	
	public abstract void update();
	public abstract void render();
	
	public int getMass()
	{
		return mass;
	}
	
	public double getX()
	{
		return xPos;
	}
	
	public double getY()
	{
		return yPos;
	}
	
	public double getXVel()
	{
		return xVel;
	}
	
	public double getYVel()
	{
		return yVel;
	}
	
	public void xAccel(double accel)
	{
		xVel += accel;
	}
	
	public void yAccel(double accel)
	{
		yVel += accel;
	}
}
