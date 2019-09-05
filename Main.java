import java.io.IOException;

import javax.swing.JFrame;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Main extends JFrame
{
	private static boolean run = true;
	
	public static void windowsAndMac()
	{
		// Might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails 
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // Use false here to switch to hook instead of raw input

		System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
		
		for (java.util.Map.Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet()) {
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		}
		
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
		
			@Override 
			public void keyPressed(GlobalKeyEvent event) {
				System.out.println(event);
				
				if(event.getVirtualKeyCode() == event.VK_PRINT)
				{
					UI kUI = new UI();
				}
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					run = false;
				}
			}
			
			@Override 
			public void keyReleased(GlobalKeyEvent event) {
				System.out.println(event); 
			}
		});
		
		try {
			while(run) { 
				Thread.sleep(128); 
			}
		} catch(InterruptedException e) { 
			//Do nothing
		} finally {
			keyboardHook.shutdownHook(); 
		}

	}

	public static void linuxSupport()
	{
		
	}
	public static void main(String[] args) throws IOException
	{
		try
		{
			windowsAndMac();
		}
		catch (Exception e) 
		{
			
			linuxSupport();
			
		}
		
	}
	
}
