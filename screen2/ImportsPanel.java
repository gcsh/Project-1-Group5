package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

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
		lblNewLabel.setBounds(175, 125, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField textField = new JTextField();
		textField.setBounds(250, 125, 175, 25);
		currentGUIFrame.add(textField);	
		
		lblNewLabel = new JLabel("Import Type");
		lblNewLabel.setBounds(175, 150, 100, 20);
		//currentGUIFrame.add(lblNewLabel);
		
		String	listData[] ={"Students","Professors","Courses","Degree Plans"};
		JList listbox = new JList( listData );
		ListSelectionModel cellSelectionModel = listbox.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		listbox.setBounds(250, 150, 100, 75);
//		currentGUIFrame.add(listbox);
//		
//		MouseListener mouseListener = new MouseAdapter(){
//			public void mouseClicked(MouseEvent mouseEvent){
//				JList theList = (JList) mouseEvent.getSource();
//		        if (mouseEvent.getClickCount() == 1){
//		        	int index = theList.locationToIndex(mouseEvent.getPoint());
//		        	if(index >= 0){
//		        		Object o = theList.getModel().getElementAt(index);
//		        		typeOfFile=o.toString();
//		        	}
//		        }
//			}
//		};
//		listbox.addMouseListener(mouseListener);
		
		download = new JButton("Import Data");
		download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filePath!=null && filePath.trim().length()!=0){
					DatabaseHelper helper=new DatabaseHelper();
					System.out.println(filePath+" This is the File Path ...................");
					String[] fileNames=filePath.split(",");
					for(int i=0;i<fileNames.length;i++){
						if(fileNames[i].indexOf("STU.DUMP.CSV")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allStudentsData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\STU.DUMP.CSV",true));
								for(String[] studentData : allStudentsData){
									//if(index!=0){
										writer.writeNext(studentData);
									//}
									//index++;
								}								
								writer.close();								
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						if(fileNames[i].indexOf("STC.DUMP.CSV")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allStudentCoursesData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\STC.DUMP.CSV",true));
								for(String[] studentCourseData : allStudentCoursesData){
									//if(index!=0){
										writer.writeNext(studentCourseData);
									//}
									//index++;
								}								
								writer.close();								
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						if(fileNames[i].indexOf("TestDataCourses.csv")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allCoursesData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataCourses.csv",true));
								for(String[] courseData : allCoursesData){
									//if(index!=0){
										writer.writeNext(courseData);
									//}
									//index++;
								}								
								writer.close();								
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						if(fileNames[i].indexOf("TestDataDegreePlanReq.csv")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allDegreePlanData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv",true));
								for(String[] degreePlanData : allDegreePlanData){
									//if(index!=0){
										writer.writeNext(degreePlanData);
									//}
									index++;
								}								
								writer.close();
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						if(fileNames[i].indexOf("TestDataDegrees.csv")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allDegreesData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegrees.csv",true));
								for(String[] degreeData : allDegreesData){
									//if(index!=0){
										writer.writeNext(degreeData);
									//}
									//index++;
								}								
								writer.close();
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						if(fileNames[i].indexOf("TestDataFaculty.csv")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allProfessorsData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataFaculty.csv",true));
								for(String[] professorData : allProfessorsData){
									//if(index!=0){
										writer.writeNext(professorData);
									//}
									//index++;
								}								
								writer.close();
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						if(fileNames[i].indexOf("TestDataSemesters.csv")!=-1){
							try{
								CSVReader inputReader = new CSVReader(new FileReader(fileNames[i]));
								List<String[]> allSemesterData=inputReader.readAll();
								int index=0;
								CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataSemesters.csv",true));
								for(String[] semesterData : allSemesterData){
									//if(index!=0){
										writer.writeNext(semesterData);
									//}
									//index++;
								}								
								writer.close();
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
					}				
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();
				}else{
					JOptionPane.showMessageDialog(download, "Please select type of file you want to import and browse the file.");
				}
			}
		});
		download.setBounds(225, 250, 100, 30);
		currentGUIFrame.add(download);
		
		browse = new JButton("Browse");
		browse.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent ae){
				 
				 JFileChooser fileChooser = new JFileChooser();
				 fileChooser.setMultiSelectionEnabled(true);
				 int returnValue = fileChooser.showOpenDialog(null);
				 if(returnValue == JFileChooser.APPROVE_OPTION){					 
					 try {
						 ArrayList<String> FileData = new ArrayList<String>();
						 File[] file = fileChooser.getSelectedFiles();
						 for(int i=0;i<file.length;i++){  
				              textField.setText(textField.getText()+file[i].toString()+",");				              				              
						 }
						 filePath=textField.getText();
						 textField.setEditable(false);
					 }catch (Exception e){
						 JOptionPane.showMessageDialog(browse, "Please select correct files");
				     }
				 }
			 }
		 });
		browse.setBounds(425, 123, 100, 30);
		currentGUIFrame.add(browse);
	}
}
