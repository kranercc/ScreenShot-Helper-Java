import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI extends JFrame implements KeyListener
{
	Vector<Integer> info;
	ScreenShot kScreenShot;
	JFrame frame;
	JPanel panel;
	public void init()
	{
		kScreenShot = new ScreenShot();
		info = new Vector<Integer>();
		
		//create a full screen
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(800, 400);
		frame.setUndecorated(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.addKeyListener(this);
		
		try
		{						//ImageIO.read(new File("temp"))
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("temp")))));
			frame.pack();
			frame.add(panel);
			frame.setVisible(true);

		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public UI()
	{
		
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
				
				int width = info.get(2) - info.get(0);
				int height = info.get(3) - info.get(1);
			
				
				
				kScreenShot.createRegion(info.get(0), info.get(1), width, height);
				//System.out.println("X start: " + info.get(0) + "Y start: " + info.get(1) + "X end: " + info.get(2) + "Y end: " + info.get(3));
				
				kScreenShot.takeScreenShot();
				kScreenShot.saveSS();
				info = new Vector<Integer>();
				new File("temp").delete();
				//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				System.exit(0);
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
