package adventureGame;


public class Bird extends Sprite
{
	static String[] poses = {"lt", "rt"};
	
	private int dy; 
	private int dx; 
	
	public Bird(int x, int y)
	{
		super(x, y, "b", poses, 6, 10);
	}
	


}