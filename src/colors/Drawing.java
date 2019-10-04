package colors;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Drawing extends Canvas {
	public static Random rand = new Random();
	public static Colors col = new Colors();
	public static BufferedImage image;
	public static BufferedImage imageCrop;
	public static Color imgColor;
	public static Canvas canvas;
	
	
	public Drawing() {
		//canvas = new Canvas();
		//canvas.setSize(1600, 1200);
		this.setSize(1600, 1200);
		imgColor = col.randColor();
		image = new BufferedImage(this.getWidth(), this.getHeight(),  BufferedImage.TYPE_INT_RGB);
		
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("My Drawing");
		canvas = new Drawing();
		canvas.setSize(1600, 1200);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		
		imgColor = col.randColor();
		
		image = new BufferedImage(canvas.getWidth(), canvas.getHeight(),  BufferedImage.TYPE_INT_RGB);		
		

	}
	
	
	public void paint(Graphics g) {
		imgColor = col.randColor();
		triangles(g, 100, imgColor);	
	}
	
	
	
	public void saveImage(String path) {
			
			Graphics2D g2 = (Graphics2D) image.getGraphics();
			this.paint(g2);
			
			File imageFile = new File(path);
			
			imageCrop = image.getSubimage(200, 200, 1200, 800);
			
			
			try {
				ImageIO.write(imageCrop, "png", imageFile);
			} catch (IOException e) {
				
			}
	}

	
	public void triangletest(Graphics g) {
		g.setColor(Color.yellow);

		g.fillPolygon(new int[] {100, 150, 200},
						new int[] {0, 50, 0}, 3);
		
		g.setColor(Color.blue);
		g.fillPolygon(new int[] {200, 250, 300},
				new int[] {0, 100, 20}, 3);
	}
	
	
	public void makesquare(Graphics g, int x1, int y1) {
		g.fillRect(x1, y1, 20, 20);	
	}
	
	
	public void grid(Graphics g, int width, int height) {
		
		
		for(int y = 0; y < height; y += 20) {
			
			for(int x = 0; x < width; x += 20) {
				g.setColor(col.randColor());
				makesquare(g, x, y);
			}
			
		}
		
		g.setColor(Color.blue);
		
	}

	
	public ArrayList<Point> createPoints3(int width, int height, int scale) {
		ArrayList<Point> pointArray = new ArrayList<>();
		
		Point p1;
		Point p2;
		
		int h = t_height(scale);
		
		for (int y = 0; y < height; y += (2*h)) {
			
			for(int x = 0; x < width; x += scale) {
				
				int x1rand = rand.nextInt(scale + 1);
				int y1rand = rand.nextInt(h + 1);		
						
				int x2rand = rand.nextInt(scale + 1);
				int y2rand = rand.nextInt(h + 1);
				
				p1 = new Point(x + x1rand, y + y1rand);
				p2 = new Point(x + (scale/2) + x2rand, y + h + y2rand);
				

				
				pointArray.add(p1);
				pointArray.add(p2);

				
			}
		
		}
		
		return pointArray;
			
	}
	
	
	
	public int t_height(int side) {
		double height = (side * Math.sqrt(3.0)) / 2;
		
		
		return (int) Math.round(height);
	}
	
	
	public void triangles(Graphics g, int scale, Color c) {
		
		int width = 1600;
		int height = 1200;
		
		int shift = (width / scale) * 2;
		
		
		Color col1 = col.randColor();
		Color col2 = col.randColor();
		
		ArrayList<Point> points = createPoints3(width, height, scale);
				
		
		Point[] tempPoints = new Point[3];
		
		int h = t_height(scale);
		
		
		for (int i = 0; i < points.size() - 2; i++) {
			
			for(int j = 0; j < 3; j ++) {
				tempPoints[j] = points.get(i + j);
			}
			
			
			if(tempPoints[0].getX() < tempPoints[2].getX()) {
				CoordArray ca = pointConvert(tempPoints);
				g.setColor(col.gradient(col1, col2, tempPoints[0].getX(), tempPoints[0].getY()));
				//g.setColor(col.randTint2(c));
				g.fillPolygon(ca.getXarray(), ca.getYarray(), 3);
			}
			
			if(i % 2 == 1 && (points.get(i).getY() < height - h) && 
					points.get(i).getX() < points.get(i + 1).getX() + scale) { //maybe just do if it's odd and if it isn't the last row
				tempPoints[0] = points.get(i + shift - 1);
				tempPoints[1] = points.get(i);
				tempPoints[2] = points.get(i + shift + 1);
				
				CoordArray ca2 = pointConvert(tempPoints);
				g.setColor(col.gradient(col1, col2, tempPoints[0].getX(), tempPoints[0].getY()));

				//g.setColor(col.randTint2(c));
				g.fillPolygon(ca2.getXarray(), ca2.getYarray(), 3);
				
				
				
				tempPoints[0] = points.get(i + 2); //just gotta change one of the points
				
				ca2 = pointConvert(tempPoints);
				g.setColor(col.gradient(col1, col2, tempPoints[0].getX(), tempPoints[0].getY()));
				
				//g.setColor(col.randTint2(c));
				g.fillPolygon(ca2.getXarray(), ca2.getYarray(), 3);
				
				
			}
				
		}
		
	}
	
	public CoordArray pointConvert(Point[] parray) {
		int[] parrx = new int[parray.length];
		int[] parry = new int[parray.length];
		
		
		
		for (int i = 0; i < parray.length; i ++) {
			parrx[i] = (int) parray[i].getX();
			parry[i] = (int) parray[i].getY();
		}
		
		
		return new CoordArray(parrx, parry);

	}
	


}
