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
import beans.DegreePlan;
import beans.Degrees;

public class EditDegreePlanPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewDegree,back,exitButton;
	public EditDegreePlanPanel(final JFrame currentGUIFrame,final String degreeCodeValue) {
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
		
//		DatabaseHelper helper=new DatabaseHelper();
//		//ArrayList degreeList=helper.getDegreePlanAndDegreeData();
//		ArrayList degreeList=helper.getDegreePlanFromDegreeData(degreeCodeValue);
//		DegreePlan degree=(DegreePlan)degreeList.get(Integer.parseInt(seletedIndex));
		
		lblNewLabel = new JLabel("Degree Code");
		lblNewLabel.setBounds(100, 120, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField degreeCode = new JTextField();
		degreeCode.setText(degreeCodeValue);
		degreeCode.setEditable(false);
		degreeCode.setBounds(250, 118, 175, 25);
		currentGUIFrame.add(degreeCode);	
		
		lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(100, 150, 100, 20);
		//currentGUIFrame.add(lblNewLabel);
		
		final JTextField  description= new JTextField();
		//description.setText(degree.getDescription());
		description.setBounds(250, 148, 175, 25);
		//currentGUIFrame.add(description);
		
		lblNewLabel = new JLabel("Hours");
		lblNewLabel.setBounds(100, 150, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  hours= new JTextField();
		//hours.setText(degree.getHours());
		hours.setBounds(250, 148, 175, 25);
		currentGUIFrame.add(hours);
		
		lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(100, 180, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  type= new JTextField();
		//type.setText(degree.getType());
		type.setBounds(250, 178, 175, 25);
		currentGUIFrame.add(type);
		
		lblNewLabel = new JLabel("Courses");
		lblNewLabel.setBounds(100, 210, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  courses= new JTextField();
		//courses.setText(degree.getCourses());
		courses.setBounds(250, 208, 175, 25);
		currentGUIFrame.add(courses);
		
//		final JCheckBox fall = new JCheckBox("Fall");
//		final JCheckBox spring = new JCheckBox("Spring");
//	    final JCheckBox summer = new JCheckBox("Summer");
//	    fall.setBounds(150, 240, 50, 30);
//		currentGUIFrame.add(fall);
//		spring.setBounds(210, 240, 70, 30);
//		currentGUIFrame.add(spring);
//		summer.setBounds(280, 240, 100, 30);
//		currentGUIFrame.add(summer);
		
		addNewDegree = new JButton("Add Course");
		addNewDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(degreeCode!=null && degreeCode.getText().trim().length()!=0 && hours!=null && hours.getText().trim().length()!=0 
						&& type!=null && type.getText().trim().length()!=0 && courses!=null && courses.getText().trim().length()!=0){
					try{
						DatabaseHelper helper=new DatabaseHelper();
						ArrayList degreeList=helper.getDegreePlanAndDegreeData();
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv"));
						Iterator iterator=degreeList.iterator();
						while(iterator.hasNext()){
							DegreePlan degree=(DegreePlan)iterator.next();
							if(degree.getDegreeCode().equalsIgnoreCase(degreeCode.getText())){
								if(degree.getHours().equalsIgnoreCase(hours.getText()) && degree.getType().equalsIgnoreCase(type.getText())){
									degree.setCourses(degree.getCourses()+","+courses.getText());
									String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
									writer.writeNext(record);
								}else{
									String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
									writer.writeNext(record);
								}
							}else{								
								String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
								writer.writeNext(record);								
							}
						}						
						writer.close();
						
//						DatabaseHelper helper=new DatabaseHelper();
//						ArrayList degreeList=helper.getDegreePlanAndDegreeData();
//						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv"));
//						Iterator iterator=degreeList.iterator();
//						int i=0;
//						while(iterator.hasNext()){
//							DegreePlan degree=(DegreePlan)iterator.next();
//							if(degree.getDegreeCode().equalsIgnoreCase(degreeCodeValue)){
//								if(i==(Integer.parseInt(seletedIndex))){
//									String [] record = {degreeCode.getText(),description.getText(),hours.getText(),type.getText(),courses.getText()};
//									writer.writeNext(record);
//								}else{								
//									String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
//									writer.writeNext(record);								
//								}
//								i++;
//							}else{								
//								String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
//								writer.writeNext(record);								
//							}
//						}						
//						writer.close();
						
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new ViewDegressPanel(currentGUIFrame,degreeCodeValue));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewDegree, "Please enter all correct details");
				}
			}
		});
		addNewDegree.setBounds(275, 375, 125, 30);
		currentGUIFrame.add(addNewDegree);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ViewDegressPanel(currentGUIFrame,degreeCodeValue));
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
