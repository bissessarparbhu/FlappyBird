package adventureGame;


public class Bird extends Sprite
{
	static String[] poses = {"lt", "rt"};
	
	
	public Bird(int x, int y)
	{
		super(x, y, "b", poses, 6, 10);
	}
	


}