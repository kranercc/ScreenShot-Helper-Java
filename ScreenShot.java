import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.w3c.dom.css.Rect;

public class ScreenShot extends JFrame
{
	Robot r;
	Rectangle rect;
	BufferedImage im;
	
	public ScreenShot()
	{
		try
		{
			r = new Robot();
			
			Rectangle capture =  new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
            BufferedImage Image = r.createScreenCapture(capture); 
            ImageIO.write(Image, "jpg", new File("temp")); 

			
		} 
		catch (AWTException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public void createRegion(int x, int y, int w, int h)
	{
		this.rect = new Rectangle(x, y, w, h);
		/*System.out.println(
				
				"X: " + this.rect.getX() +
				"\nY: " + this.rect.getY() +
				"\nWidth: " + this.rect.getWidth() +
				"\nHeight: " + this.rect.getHeight()
				
				);
		*/
		
	}
	
	public void takeScreenShot()
	{
		this.im = r.createScreenCapture(this.rect);
	}
	
	public void saveSS()
	{
		//Linux
		try
		{
			Process p = Runtime.getRuntime().exec("ls /home"); //the problem is only works on a user,
			String output = read(p.getInputStream()); //i can modify here to get the first user only tho
			
			ImageIO.write(this.im, "jpg", new File("/home/" + output + "/Desktop/screenshot.jpg"));
		}
		//others
		catch (Exception e) 
		{
			try 
			{
				ImageIO.write(this.im, "jpg", new File("screenshot.jpg"));
					
			}
			catch (Exception e2)
			{
				
				
				
			}
		}
	}
	public static String read(InputStream input) throws IOException {
	    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
	        return buffer.lines().collect(Collectors.joining("\n"));
	    }
	}
	
}
