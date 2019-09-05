import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UI extends JFrame implements KeyListener
{
	Vector<Integer> info;
	ScreenShot kScreenShot;
	public UI()
	{
		kScreenShot = new ScreenShot();
		info = new Vector<Integer>();
		
		//create a full screen
		JFrame frame = new JFrame();
		frame.setSize(800, 400);
		frame.setUndecorated(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
		try
		{						//ImageIO.read(new File("temp"))
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("temp")))));
			frame.pack();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getRectInfo()
	{
		info.add(getLocationOfMouse()[0]);
		info.add(getLocationOfMouse()[1]);
		
	
	}
	
	
	
	
	public int[] getLocationOfMouse()
	{
		Point m = MouseInfo.getPointerInfo().getLocation();
		int[] mousePos = {(int)m.getX(), (int)m.getY()};
		
		return mousePos;
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if (keyCode == e.VK_ADD)
		{
			
			getLocationOfMouse();
			getRectInfo();
			if (info.size() == 4)
			{
				
				
				kScreenShot.createRegion(info.get(0), info.get(1), info.get(2), info.get(3));
				//System.out.println("X start: " + info.get(0) + "Y start: " + info.get(1) + "X end: " + info.get(2) + "Y end: " + info.get(3));
				
				kScreenShot.takeScreenShot();
				try
				{
					kScreenShot.saveSS();
					info = new Vector<Integer>();
					
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
	

	
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
	
	
	
	
	
}
