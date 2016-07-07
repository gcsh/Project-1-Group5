package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Degrees;

public class EditDegreePanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewDegree,back,exitButton;
	public EditDegreePanel(final JFrame currentGUIFrame,final String seletedIndex) {
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
		lblNewLabel = new JLabel("Edit Degree Plan");
		lblNewLabel.setBounds(250, 90, 200, 20);
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
		degrees.setBackground(Color.gray);
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
		
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList degreeList=helper.getDegreesDataFromTestFaculty();
		Degrees degree=(Degrees)degreeList.get(Integer.parseInt(seletedIndex)+1);
		
		lblNewLabel = new JLabel("Degree Code");
		lblNewLabel.setBounds(100, 120, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField degreeCode = new JTextField();
		degreeCode.setText(degree.getDegreeCode());
		degreeCode.setEditable(false);
		degreeCode.setBounds(250, 118, 175, 25);
		currentGUIFrame.add(degreeCode);	
		
		lblNewLabel = new JLabel("Forecast");
		lblNewLabel.setBounds(100, 180, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  forecast= new JTextField();
		forecast.setText(degree.getForecast());
		forecast.setBounds(250, 178, 175, 25);
		currentGUIFrame.add(forecast);
		
		addNewDegree = new JButton("Update Forecast");
		addNewDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(degreeCode!=null && degreeCode.getText().trim().length()!=0 && forecast!=null && forecast.getText().trim().length()!=0 ){
					try{
						DatabaseHelper helper=new DatabaseHelper();
						ArrayList degreeList=helper.getDegreesDataFromTestFaculty();
						CSVWriter writers = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegrees.csv"));
						Iterator iterators=degreeList.iterator();
						while(iterators.hasNext()){
							Degrees degree=(Degrees)iterators.next();
							if(degree.getDegreeCode().equalsIgnoreCase(degreeCode.getText())){
								String [] record = {degreeCode.getText(),degree.getGradSchool(),degree.getDegreeName(),forecast.getText()};
								writers.writeNext(record);
							}else{								
								String [] record = {degree.getDegreeCode(),degree.getGradSchool(),degree.getDegreeName(),degree.getForecast()};
								writers.writeNext(record);								
							}
						}						
						writers.close();						
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewDegree, "Please enter all correct details");
				}
			}
		});
		addNewDegree.setBounds(275, 375, 150, 30);
		currentGUIFrame.add(addNewDegree);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 375, 125, 30);
		currentGUIFrame.add(back);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(450, 375, 100, 30);
		currentGUIFrame.add(exitButton);
	}

}
