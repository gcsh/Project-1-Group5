package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Screens.HomePanel;
import Screens.ImportsPanel;

public class GUIFrames extends JFrame {
	
	private JFrame currentGUIFrame;
	private JPanel contentPane;
	public GUIFrames() {
		currentGUIFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 575);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu homeMenu = new JMenu("Home");
		//menuBar.add(homeMenu);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		
		JMenu exit = new JMenu("Exit");
		menuBar.add(exit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);		
		getContentPane().removeAll();
		getContentPane().add(new LoginHome(currentGUIFrame));
		
		JMenuItem mntmStore = new JMenuItem("open");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		fileMenu.add(mntmStore);
		mntmStore = new JMenuItem("new");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		fileMenu.add(mntmStore);
		mntmStore = new JMenuItem("save");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		fileMenu.add(mntmStore);
		mntmStore = new JMenuItem("save as");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
			}
		});
		fileMenu.add(mntmStore);
		mntmStore = new JMenuItem("import file");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ImportsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		fileMenu.add(mntmStore);
		
		mntmStore = new JMenuItem("home");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				currentGUIFrame.getContentPane().removeAll();
//				currentGUIFrame.getContentPane().add(new HomePanel(currentGUIFrame));
//				currentGUIFrame.getContentPane().repaint();
			}
		});
		homeMenu.add(mntmStore);
		
		mntmStore = new JMenuItem("exit");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.exit(0);
			}
		});
		exit.add(mntmStore);
	}
	
	public static void run(){
		try{
			JFrame frame = new GUIFrames();
			frame.setVisible(true);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
