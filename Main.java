import java.io.IOException;

import javax.swing.JFrame;

import org.python.util.PythonInterpreter;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Main extends JFrame
{
	private static boolean run = true;
	


	public static void main(String[] args) throws IOException
	{
		UI kUI = new UI();
		kUI.init();
	}
	
}
