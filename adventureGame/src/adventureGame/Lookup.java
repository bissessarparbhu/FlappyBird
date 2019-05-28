package adventureGame;


public class Lookup
{
	public static final double[] cos = generateCos();
	public static final double[] sin = generateSin();
	
	
	private static double[] generateCos()
	{
		double[] cos = new double[360];
		
		for(int i = 0; i < cos.length; i++)
			
			cos[i] = Math.cos(i * Math.PI / 180);
		
		return cos;
	}
	

	
	private static double[] generateSin()
	{
		double[] sin = new double[360];
		
		for(int i = 0; i < sin.length; i++)
			
			sin[i] = Math.sin(i * Math.PI / 180);
		
		return sin;
	}
	
}