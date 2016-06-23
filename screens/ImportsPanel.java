package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.LoginHome;

public class ImportsPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,download,browse;
	private String filePath,typeOfFile;
	public ImportsPanel(final JFrame currentGUIFrame) {
		setLayout(null);		
		JLabel lblNewLabel = new JLabel("Welcome User");
		lblNewLabel.setBounds(425, 10, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new LoginHome(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();	
			}
		});
		logout.setBounds(525, 7, 75, 30);
		currentGUIFrame.add(logout);
		lblNewLabel = new JLabel("Import Files");
		lblNewLabel.setBounds(250, 30, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		
		professors = new JButton("Professors");
		professors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		professors.setBounds(10, 55, 100, 30);
		currentGUIFrame.add(professors);
		
		students = new JButton("Students");
		students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new StudentsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		students.setBounds(95, 55, 90, 30);
		currentGUIFrame.add(students);
		
		degrees = new JButton("Degrees");
		degrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		degrees.setBounds(180, 55, 90, 30);
		currentGUIFrame.add(degrees);
		
		subjects = new JButton("Courses");		
		subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new CousesPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		subjects.setBounds(265, 55, 90, 30);
		currentGUIFrame.add(subjects);
		
		rooms = new JButton("Semesters");
		rooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SemestersPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		rooms.setBounds(350, 55, 100, 30);
		currentGUIFrame.add(rooms);
		
		schedule = new JButton("Schedule");
		schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SchedulePanel(currentGUIFrame,null));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		schedule.setBounds(435, 55, 90, 30);
		currentGUIFrame.add(schedule);
		
		reports = new JButton("Reports");
		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ReportsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		reports.setBounds(520, 55, 85, 30);
		currentGUIFrame.add(reports);
		
		lblNewLabel = new JLabel("Upload File");
		lblNewLabel.setBounds(175, 100, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField textField = new JTextField();
		textField.setBounds(250, 100, 175, 25);
		currentGUIFrame.add(textField);	
		
		lblNewLabel = new JLabel("Import Type");
		lblNewLabel.setBounds(175, 150, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		String	listData[] ={"Students","Professors","Subjects","Degree Plans"};
		JList listbox = new JList( listData );
		listbox.setBounds(250, 150, 100, 75);
		currentGUIFrame.add(listbox);
		
		download = new JButton("Import Data");
		download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filePath!=null && filePath.trim().length()!=0){
					System.out.println(filePath+" This is the File Path ...................");
				}
			}
		});
		download.setBounds(225, 250, 100, 30);
		currentGUIFrame.add(download);
		
		browse = new JButton("Browse");
		browse.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent ae){
				 JFileChooser fileChooser = new JFileChooser();
				 int returnValue = fileChooser.showOpenDialog(null);
				 if(returnValue == JFileChooser.APPROVE_OPTION){
					 File selectedFile = fileChooser.getSelectedFile();
					 textField.setText(selectedFile.getAbsolutePath());
					 filePath=selectedFile.getAbsolutePath();
					 textField.setEditable(false);
				 }
			 }
		 });
		browse.setBounds(425, 97, 100, 30);
		currentGUIFrame.add(browse);
	}
}
