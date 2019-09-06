import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.FileHandler;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class Install extends JFrame
{

	private JPanel contentPane;
	private JTextField txtPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Install frame = new Install();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Install()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/krane/eclipse-workspace/ScreenShot-Install/pics/screenshot2.jpg"));
		setTitle("ScreenShot-Helper Install");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPath = new JTextField();
		txtPath.setEditable(false);
		txtPath.setToolTipText("Path");
		txtPath.setText("C:\\");
		txtPath.setBounds(44, 99, 350, 19);
		contentPane.add(txtPath);
		txtPath.setColumns(10);
		
		JButton btnInstall = new JButton("Install");
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = txtPath.getText();
				path = path + "/";
				
				//help for the py script, because it must be run as sudo, if you go from the desktop shortcut it will
				//recognize as the open folder is the desktop and if he is looking there he will not find the .jar and will cry about it
				
				createLauncherDesktop(path);

				
				
				//Copy files
				String fileToCopy = "ScreenShot-Helper.jar";
				File fileToMove = new File(fileToCopy);
				boolean success = fileToMove.renameTo(new File(path, fileToMove.getName()));
				
					
				
				

				//thank you message
				JOptionPane.showMessageDialog(null, "Thank you!");
				System.exit(0);

				
				
			}
		});
		btnInstall.setBounds(163, 200, 114, 25);
		contentPane.add(btnInstall);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				//	BROWSE FILES
				//
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Choose a directory to save your file: ");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isDirectory()) {
						
						//	SET THE TEXTBOX TO BE THE PATH
						//
						txtPath.setText(jfc.getSelectedFile().toString());
					}
				}
				
				
			}
		});
		btnBrowse.setBounds(145, 130, 150, 25);
		contentPane.add(btnBrowse);
	}
	
	
	public void createLauncherDesktop(String path)
	{
		try
		{
			String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory() + "/Desktop/ScreenShot.desktop";
			FileWriter f = new FileWriter(new File(desktopPath));

			f.write("[Desktop Entry]\n" + 
					"Name=ScreenShot\n" + 
					"Exec=java -jar " + path + "/ScreenShot-Helper.jar\n" + 
					"Comment=ScreenShot\n" + 
					"Terminal=false\n" + 
					"Icon=face-smile-big\n" + 
					"Type=Application\n"
					);
			
			
			f.close();
			
			Runtime.getRuntime().exec("chmod +x " + desktopPath);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
