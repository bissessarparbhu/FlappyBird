package adventureGame;

import java.awt.*;

public class Sprite
{
	int x;
	int y;
	
	Animation[] anim;
	
	int pose = 0;
	
	boolean moving = false;
	
	
	public Sprite(int x, int y, String name, String[] poses, int count, int duration)
	{
		this.x = x;
		this.y = y;
		
		anim = new Animation[poses.length];
		
		for(int i = 0; i < poses.length; i++)
			
			anim[i] = new Animation(name + "_" + poses[i], count, duration);
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	
	public void moveUp(int dist)
	{
		y -= dist;
		
		pose = 0;
		
      moving = true;		
	}
	
	public void moveDown(int dist)
	{
		y += dist;
		
		pose = 0;

      moving = true;		
}

	public void moveLeft(int dist)
	{
		x -= dist;
		
		pose = 0;

      moving = true;		
   }
	
	public void moveRight(int dist)
	{
		x += dist;
		
		pose = 0;
	
      moving = true;		
   }
	
	public void draw(Graphics g)
	{
      if(moving)  
      	
      	g.drawImage(anim[pose].getCurrentImage(), x, y, null);
      
      else
      	
      	g.drawImage(anim[pose].getStillImage(), x, y, null);
      
      moving = false;
      	
	}

}