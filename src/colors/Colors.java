package colors;
import java.awt.Color;
import java.util.Random;


public class Colors {
	private static Random rand = new Random();
	
	public Colors() {
		
	}
	
	public Color randColor() {
		//float red = rand.nextFloat();
		//float green = rand.nextFloat();
		//float blue = rand.nextFloat();

		int red = rand.nextInt(256);
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		
		return new Color(red, green, blue);
	}
	
	public Color randTint(Color base) {
		
		int r = (255 - base.getRed());
		int g = (255 - base.getGreen());
		int b = (255 - base.getBlue());
		
		float factor = rand.nextFloat();
		
		
		return new Color(base.getRed() + Math.round(factor * r),
						 base.getGreen() + Math.round(factor * g),
						 base.getBlue() + Math.round(factor * b));
		
		
		
	}
	
	public Color randShade(Color base) {
		int r = base.getRed();
		int g = base.getGreen();
		int b = base.getBlue();
		
		float factor = rand.nextFloat();
		
		return new Color(Math.round(factor * r),
						Math.round(factor * g), 
						Math.round(factor * b));
		
	}
	
	public Color randShade2(Color base) {
		int r = base.getRed();
		int g = base.getGreen();
		int b = base.getBlue();
		
		float factor = rand.nextFloat() * 0.05f + 0.95f;
		
		return new Color(Math.round(factor * r),
						Math.round(factor * g), 
						Math.round(factor * b));
		
	}
	
	
	public Color randTint2(Color base) {
		
		int r = (255 - base.getRed());
		int g = (255 - base.getGreen());
		int b = (255 - base.getBlue());
		
		float factor = rand.nextFloat() * 0.8f;
		
		
		return new Color(base.getRed() + Math.round(factor * r),
						 base.getGreen() + Math.round(factor * g),
						 base.getBlue() + Math.round(factor * b));
		
	}
	
	
	
	public Color randColorize(Color base) {
		
		int coin = rand.nextInt(2);
		
		if (coin == 0) {
			return randTint(base);
		} else {
			return randShade(base);
		}
		
		
	}

	public Color generatePastelColor(Color mix) {
	    Random random = new Random();
	    int red = random.nextInt(256);
	    int green = random.nextInt(256);
	    int blue = random.nextInt(256);
	 
	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2;
	        green = (green + mix.getGreen()) / 2;
	        blue = (blue + mix.getBlue()) / 2;
	    }
	 
	    Color color = new Color(red, green, blue);
	    return color;
	}
	
	
}






