import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	
	public void createRegion(int w, int h, int x, int y)
	{
		this.rect = new Rectangle(x, y, w, h);
		
	}
	
	public void takeScreenShot()
	{
		this.im = r.createScreenCapture(this.rect);
	}
	
	public void saveSS() throws IOException
	{
		ImageIO.write(this.im, "jpg", new File("screenshot"));
	}
	
	
}
