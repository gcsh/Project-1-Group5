package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import au.com.bytecode.opencsv.CSVReader;
import beans.Professor;
import main.LoginHome;
import Database.DatabaseHelper;

public class FacultyReport extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,download,studentReport,facultyReport,exitButton,degreePlanReport;
	public FacultyReport(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("Faculty Load Report");
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
		reports.setBackground(Color.gray);
		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ReportsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		reports.setBounds(520, 55, 85, 30);
		currentGUIFrame.add(reports);
		
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3"},
							   { "Row2-Column1", "Row2-Column2", "Row2-Column3"} };
		Object columnNames[] = { "Faculty Name", "Course Name", "Teaching Hours"};
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList professorsList=helper.getFacultiesDataFromTestFaculty();
		LinkedHashMap facultyData=new LinkedHashMap();
		LinkedHashMap facultyTeachHours=new LinkedHashMap();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\facultyreport.csv"));
			List<String[]> allProfessorsData=inputReader.readAll();
			if(allProfessorsData!=null && allProfessorsData.size()!=0){
				for(String[] professorData : allProfessorsData){
					if(!facultyData.containsKey(professorData[3])){
						facultyData.put(professorData[3],professorData[1]);
					}else if(facultyData.containsKey(professorData[3]) && !((String)facultyData.get(professorData[3])).equalsIgnoreCase(professorData[1])){
						String teach=((String)facultyData.get(professorData[3]));
						facultyData.put(professorData[3],teach+" (and) "+professorData[1]);
					}
				}
			}
			
			if(allProfessorsData!=null && allProfessorsData.size()!=0){
				for(String[] professorData : allProfessorsData){
					if(facultyTeachHours.containsKey(professorData[3])){
						int hours=Integer.parseInt(((String)facultyTeachHours.get(professorData[3])));
						hours=hours+3;
						facultyTeachHours.put(professorData[3],""+hours);
					}else{
						int hours=3;
						facultyTeachHours.put(professorData[3],""+hours);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(professorsList!=null && professorsList.size()!=0){
			rowData=new String[professorsList.size()][9];
			for(int count=0;count<professorsList.size();count++){
				if(count!=professorsList.size()-1){
					int increment=0;
					Professor professor=(Professor)professorsList.get(count+1);
					if(professor!=null){
						rowData[count][increment]=professor.getFirstName();
						increment=increment+1;
						rowData[count][increment]=facultyData.get(professor.getFirstName());
						increment=increment+1;
						rowData[count][increment]=facultyTeachHours.get(professor.getFirstName());
					}
				}
			}
		}
		final JTable table = new JTable(rowData, columnNames);
		table.setRowSelectionAllowed(true);		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 150, 600, 300);
		currentGUIFrame.add(scroll);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(250, 350, 75, 30);
		currentGUIFrame.add(exitButton);
	}
}
