package adventureGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer
{
	Image image;
	
	int x; 
	int y;
	int z;
	final int WIDTH = 500, HEIGHT = 500; 
	public ImageLayer(int x, int y, int z, String filename)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		image = Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	//Add two images 
	public void draw(Graphics g)
	{
		for(int i = 0; i < 2; i++)
	   	g.drawImage(image, (int)(x + WIDTH*i - Camera.x / z), (int)(y- Camera.y / z), null);
	}

}