//Created by Parbhu Bissessar
//CMP 428 Spring 2019 5/28/2019

package adventureGame;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.applet.Applet;
import java.applet.AudioClip;

public class GameIntro extends Applet implements Runnable, KeyListener
{
	
	final int WIDTH = 1000, HEIGHT = 500;
	
	boolean up_pressed = false;
	boolean dw_pressed = false;
	boolean gameOver = false;
	
	Thread t;
	
	Image    offscreen_img;
	Graphics offscreen_g;
	AudioClip music;
	
	Bird birdImg = new Bird(50,250); //image of the bird
	Rect bird = new Rect(60, 260, 40, 20); //bird rectangle
	
	Rect ground = new Rect(0, 480, 1000, 50);
	
	
	
	//Parallax Images
	ImageLayer imgSky    = new ImageLayer(0, 0, 50 , "sky.PNG");
	ImageLayer imgOne    = new ImageLayer(0, 0, 40 , "one.PNG");
	ImageLayer imgTwo    = new ImageLayer(0, 0, 10 , "two.PNG");
	ImageLayer imgFour   = new ImageLayer(0, 0, 2 , "four.PNG");
	ImageLayer imgThree  = new ImageLayer(0, 0, 1 , "three.PNG");
	
	//Pipes Images
	Image imgPipeBottom  = Toolkit.getDefaultToolkit().getImage("p_lt_1.PNG");
	Image imgPipeTop     = Toolkit.getDefaultToolkit().getImage("ptop.PNG");
	Image imgGameOver    = Toolkit.getDefaultToolkit().getImage("game.PNG");
	
	


	//create pipe array
	Rect[] pipeArrTop = new Rect[30];
	Rect[] pipeArrBottom = new Rect[30];
	

	
	public void init()
	{
		//drawing the images off screen
		offscreen_img = this.createImage(WIDTH, HEIGHT);
		offscreen_g   = offscreen_img.getGraphics();
		
		music = getAudioClip(getCodeBase(), "Gravity Bass.wav");
		music.loop();
		
		//create the pipes 
		for(int i = 0; i < pipeArrTop.length; i++) {
			  int randomLocation = new Random().nextInt(200);
			  pipeArrBottom[i] = new Rect(400 + (i*200), 200 + randomLocation, 50, 400); 
			  pipeArrTop[i] = new Rect(400 + (i*200), -300 + randomLocation , 50, 400); 
		}
		
		
		
		
		requestFocus();
		addKeyListener(this);
		t = new Thread(this);
		t.start();

	}
	
	public void run()
	{

		while(true)
		{
			//while the game isnt over, do this
			if (!gameOver) {
				
				//Move the bird down when the game is playing by default
				 birdImg.moveDown(1); 
				 bird.moveDown(1);
				 
				 
				//If you press up, the bird will go up
				if(up_pressed) {  
					bird.moveUp(3);
					birdImg.moveUp(3);
					Camera.moveRight(1);
				}
				
				//If you press down, the bird will go down
				if(dw_pressed) {  
					bird.moveDown(3);
					birdImg.moveDown(3);
					Camera.moveRight(1);
				}
				

				//Move the top and bottom pipes left when the game is playing 
				for(int i = 0; i < pipeArrTop.length; i++) {
					pipeArrTop[i].moveLeft(2);
					pipeArrBottom[i].moveLeft(2);
				}
				
				
				
				//If the bird touch the top pipe, bird dies
				for (int i=0; i < pipeArrTop.length; i ++) {
					if (bird.touches(pipeArrTop[i])) {
						gameOver = true;
						music.stop();
				    }
				}
				
				//if the bird touch the bottom pipe, bird dies
				for (int i=0; i < pipeArrBottom.length; i ++) {
					if (bird.touches(pipeArrBottom[i])) {
						gameOver = true;
						music.stop();
				    }
				}
				
				//If the bird touches the ground, bird dies
				if (bird.touches(ground)) {
					gameOver = true;
					music.stop();
				}

				repaint();
				
				try
				{
				   t.sleep(16);
				}
				catch(Exception x) {};
			}
		}
	}
	

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)     up_pressed = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)     dw_pressed = true;
	}
	
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)     up_pressed = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)     dw_pressed = false;
	}

	public void keyTyped(KeyEvent e)
	{
		
	}
	
	
	//Stop flickering
		public void update(Graphics g)
		{
			offscreen_g.clearRect(0, 0, WIDTH, HEIGHT);
			paint(offscreen_g);	
			g.drawImage(offscreen_img, 0, 0, WIDTH+200, HEIGHT, null);
		}
	
		
	public void paint(Graphics g)
	{
		
		//Drawing the background
		imgSky.draw(g);
		imgOne.draw(g);
		imgTwo.draw(g);
		
		imgFour.draw(g);
		
		imgThree.draw(g);
		
		//Drawing bird to the screen
		birdImg.draw(g);
		//bird.draw(g);
		//ground.draw(g);

		
		if (gameOver) {
			g.drawImage(imgGameOver, 50, 50, null);
		}
		
		//drawing the top and bottom pipes
		for(int i = 0; i < pipeArrTop.length; i++) {
			//pipeArrTop[i].draw(g);
			//pipeArrBottom[i].draw(g);
			g.drawImage(imgPipeTop, pipeArrTop[i].x, pipeArrTop[i].y, null);
			g.drawImage(imgPipeBottom, pipeArrBottom[i].x, pipeArrBottom[i].y, null);
		}
		


	
	}
	
	
	
	
	
	
	
	
	


}