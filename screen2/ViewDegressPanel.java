package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

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

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Course;
import beans.DegreePlan;
import beans.Degrees;

public class ViewDegressPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewDegreePlan,editButton,deleteButton,exitButton;
	private String selectedIndex;
	public ViewDegressPanel(final JFrame currentGUIFrame,final String degreeCode) {
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
		lblNewLabel = new JLabel("View Degree Plans");
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
		
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"},
		                       { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"} };
		Object columnNames[] = { "Degree Code", "Name", "Hours","Type","Course Name","Course Code"};
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList degreeList=helper.getDegreePlanFromDegreeData(degreeCode);
		LinkedHashMap courseFacultyMap=helper.getCoursesFacultyDump();
		int rowLength=0;
		if(degreeList!=null && degreeList.size()!=0){
			for(int count=0;count<degreeList.size();count++){
				DegreePlan degree=(DegreePlan)degreeList.get(count);
				if(degree!=null && degree.getCourses()!=null && degree.getCourses().indexOf(",")!=-1){
					rowLength+=degree.getCourses().split(",").length;
				}else{
					rowLength++;
				}
			}
		}
		if(rowLength!=0 && courseFacultyMap!=null && degreeList!=null && degreeList.size()!=0){
			rowData=new Object[rowLength][6];
			int rowincrement=0;
			for(int count=0;count<degreeList.size();count++){
				DegreePlan degree=(DegreePlan)degreeList.get(count);
				if(degree!=null && degree.getCourses()!=null && degree.getCourses().indexOf(",")!=-1){
					String[] coursesData=degree.getCourses().split(",");
					for(int i=0;i<coursesData.length;i++){
						int increment=0;
						rowData[rowincrement][increment]=degree.getDegreeCode();
						increment=increment+1;
						rowData[rowincrement][increment]=degree.getDescription();
						increment=increment+1;
						rowData[rowincrement][increment]=degree.getHours();
						increment=increment+1;
						rowData[rowincrement][increment]=degree.getType();	
						increment=increment+1;
						Course course=(Course)courseFacultyMap.get(coursesData[i].trim());						
						if(course!=null){
							rowData[rowincrement][increment]=course.getCourseName();
						}else{
							rowData[rowincrement][increment]="";
						}
						increment=increment+1;
						rowData[rowincrement][increment]=coursesData[i];
						rowincrement++;
					}
				}else{					
					int increment=0;
					rowData[rowincrement][increment]=degree.getDegreeCode();
					increment=increment+1;
					rowData[rowincrement][increment]=degree.getDescription();
					increment=increment+1;
					rowData[rowincrement][increment]=degree.getHours();
					increment=increment+1;
					rowData[rowincrement][increment]=degree.getType();	
					increment=increment+1;
					Course course=(Course)courseFacultyMap.get(degree.getCourses().trim());
					if(course!=null){
						rowData[rowincrement][increment]=course.getCourseName();
					}else{
						rowData[rowincrement][increment]="";
					}
					increment=increment+1;
					rowData[rowincrement][increment]=degree.getCourses();
					rowincrement++;
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
		
		editButton = new JButton("Add");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new EditDegreePlanPanel(currentGUIFrame,degreeCode));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		editButton.setBounds(10, 475, 100, 30);
		currentGUIFrame.add(editButton);
		
//		editButton = new JButton("Edit");
//		editButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(selectedIndex!=null && selectedIndex.trim().length()!=0){
//					currentGUIFrame.getContentPane().removeAll();
//					currentGUIFrame.getContentPane().add(new EditDegreePlanPanel(currentGUIFrame,selectedIndex,degreeCode));
//					currentGUIFrame.getContentPane().repaint();
//					selectedIndex="";
//				}else{
//					JOptionPane.showMessageDialog(editButton, "Please select one table row");
//				}
//			}
//		});
//		editButton.setBounds(10, 475, 100, 30);
//		currentGUIFrame.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedIndex!=null && selectedIndex.trim().length()!=0){
					try{						
						DatabaseHelper helper=new DatabaseHelper();
						ArrayList degreeList=helper.getDegreePlanFromDegreeData(degreeCode);
						ArrayList existingDegreeList=helper.getDegreePlanAndDegreeData();
						LinkedHashMap courseFacultyMap=helper.getCoursesFacultyDump();
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv"));
						int rowLength=0;
						if(degreeList!=null && degreeList.size()!=0){
							int rowincrement=0;
							for(int count=0;count<degreeList.size();count++){
								DegreePlan degree=(DegreePlan)degreeList.get(count);
								if(degree!=null && degree.getCourses()!=null && degree.getCourses().indexOf(",")!=-1){
									String[] coursesData=degree.getCourses().split(",");
									degree.setCourses("");
									for(int i=0;i<coursesData.length;i++){
										if(!(Integer.parseInt(selectedIndex)==rowincrement)){
											degree.setCourses(degree.getCourses()+coursesData[i]+",");
										}
										rowincrement++;
									}
								}else{					
									if(!(Integer.parseInt(selectedIndex)==rowincrement)){
										degree.setCourses("");
									}
									rowincrement++;
								}
								if(degree.getCourses()!=null && degree.getCourses().trim().length()!=0){
									if(degree.getCourses().charAt(degree.getCourses().length()-1)==','){
										degree.setCourses(degree.getCourses().substring(0,degree.getCourses().length()-1));
									}
									String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
									writer.writeNext(record);
								}
							}
						}
						
						Iterator iterator=existingDegreeList.iterator();
						while(iterator.hasNext()){
							DegreePlan degree=(DegreePlan)iterator.next();
							if(!(degree.getDegreeCode().equalsIgnoreCase(degreeCode))){
								String [] record = {degree.getDegreeCode(),degree.getDescription(),degree.getHours(),degree.getType(),degree.getCourses()};
								writer.writeNext(record);
							}
						}
						writer.close();
						
						//ArrayList degreeList=helper.getDegreePlanAndDegreeData();
//						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv"));
//						Iterator iterator=degreeList.iterator();
//						int i=0;
//						while(iterator.hasNext()){
//							DegreePlan degree=(DegreePlan)iterator.next();
//							if(degree.getDegreeCode().equalsIgnoreCase(degreeCode)){
//								if(i==(Integer.parseInt(selectedIndex))){
////									String [] record = {degreeCode.getText(),description.getText(),hours.getText(),type.getText(),courses.getText()};
////									writer.writeNext(record);
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
						currentGUIFrame.getContentPane().add(new ViewDegressPanel(currentGUIFrame,degreeCode));
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
