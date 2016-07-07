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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Course;
import beans.Professor;
import main.LoginHome;

public class CousesPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewSubjects,editButton,deleteButton,exitButton;
	private String selectedIndex;
	public CousesPanel(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("View Courses");
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
		subjects.setBackground(Color.gray);
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
		
		addNewSubjects = new JButton("Add New Course");
		addNewSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new AddCoursePanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		addNewSubjects.setBounds(10, 90, 175, 30);
		currentGUIFrame.add(addNewSubjects);
		
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5","Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5"},
		                       { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row1-Column4", "Row1-Column5","Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5"} };
		Object columnNames[] = { "Course Code", "Course Name", "Descrption", "Course Hours","Course Cap","Offered Fall", "Offered Spring", "Offered Summer", "Prereqs","Teachers"};
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList courseList=helper.getCoursesDataFromTestCourse();
		if(courseList!=null && courseList.size()!=0){
			rowData=new Object[courseList.size()][10];
			for(int count=0;count<courseList.size();count++){
				if(count!=courseList.size()-1){
					int increment=0;
					Course course=(Course)courseList.get(count+1);
					if(courseList!=null){
						rowData[count][increment]=course.getCourseCode();
						increment=increment+1;
						rowData[count][increment]=course.getCourseName();
						increment=increment+1;
						rowData[count][increment]=course.getCourseDescription();
						increment=increment+1;
						rowData[count][increment]=course.getCourseHours();
						increment=increment+1;						
						rowData[count][increment]=course.getCourseCap();
						increment=increment+1;
						rowData[count][increment]=course.getOfferedFall();
						increment=increment+1;
						rowData[count][increment]=course.getOfferedSpring();
						increment=increment+1;
						rowData[count][increment]=course.getOfferedSummer();
						increment=increment+1;
						rowData[count][increment]=course.getCoursePrereqs();
						increment=increment+1;
						rowData[count][increment]=course.getTeaches();
					}
				}				
			}
		}
		final JTable table = new JTable(rowData, columnNames);
		table.setRowSelectionAllowed(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent e) {
	    		String selectedData = null;
		        if(table.getSelectedRow() > -1){
		        	selectedIndex=""+table.getSelectedRow();		        	
		        }
	    	}
	    });
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 150, 600, 300);
		currentGUIFrame.add(scroll);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedIndex!=null && selectedIndex.trim().length()!=0){
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new EditCoursePanel(currentGUIFrame,selectedIndex));
					currentGUIFrame.getContentPane().repaint();
					selectedIndex="";
				}else{
					JOptionPane.showMessageDialog(editButton, "Please select one table row");
				}
			}
		});
		editButton.setBounds(10, 475, 100, 30);
		currentGUIFrame.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedIndex!=null && selectedIndex.trim().length()!=0){
					DatabaseHelper helper=new DatabaseHelper();
					ArrayList courseList=helper.getCoursesDataFromTestCourse();
					courseList.remove(Integer.parseInt(selectedIndex)+1);				
					try{
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataCourses.csv"));
						Iterator iterator=courseList.iterator();
						while(iterator.hasNext()){
							Course course=(Course)iterator.next();
							String [] record = {course.getCourseCode(),course.getCourseName(),course.getCourseDescription(),course.getCourseHours(),course.getCourseCap(),course.getOfferedFall(),course.getOfferedSpring(),course.getOfferedSummer(),course.getCoursePrereqs(),course.getTeaches()};
							writer.writeNext(record);
						}					
						writer.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new CousesPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(editButton, "Please select one table row");
				}
			}
		});
		deleteButton.setBounds(115, 475, 100, 30);
		currentGUIFrame.add(deleteButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(225, 475, 100, 30);
		currentGUIFrame.add(exitButton);
	}
}
