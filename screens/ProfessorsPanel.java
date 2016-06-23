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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Professor;

public class ProfessorsPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewProfessor,editButton,deleteButton,exitButton;
	private String selectedIndex;
	public ProfessorsPanel(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("View Professors");
		lblNewLabel.setBounds(250, 30, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		
		professors = new JButton("Professors");
		professors.setBackground(Color.gray);
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
		
		addNewProfessor = new JButton("Add New Professor");
		addNewProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new AddProfessorPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		addNewProfessor.setBounds(10, 90, 175, 30);
		currentGUIFrame.add(addNewProfessor);
		
		String rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6", "Row1-Column7", "Row1-Column8", "Row1-Column9"},
		                       { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6", "Row1-Column7", "Row1-Column8", "Row1-Column9"} };
		String columnNames[] = { "Last Name", "First Name", "Grad School", "Degree", "Title", "Days","MaxLoadFall","MaxLoadSpring","MaxLoadSummer"};
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList professorsList=helper.getFacultiesDataFromTestFaculty();
		if(professorsList!=null && professorsList.size()!=0){
			rowData=new String[professorsList.size()][9];
			for(int count=0;count<professorsList.size();count++){
				if(count!=professorsList.size()-1){
					int increment=0;
					Professor professor=(Professor)professorsList.get(count+1);
					if(professor!=null){
						rowData[count][increment]=professor.getLastName();
						increment=increment+1;
						rowData[count][increment]=professor.getFirstName();
						increment=increment+1;
						rowData[count][increment]=professor.getGradSchool();
						increment=increment+1;
						rowData[count][increment]=professor.getDegree();
						increment=increment+1;
						rowData[count][increment]=professor.getTitle();
						increment=increment+1;
						rowData[count][increment]=professor.getDaysToTeach();
						increment=increment+1;
						rowData[count][increment]=professor.getMaxLoadFall();
						increment=increment+1;
						rowData[count][increment]=professor.getMaxLoadSpring();
						increment=increment+1;
						rowData[count][increment]=professor.getMaxLoadSummer();
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
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new EditProfessorPanel(currentGUIFrame,selectedIndex));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		editButton.setBounds(10, 475, 100, 30);
		currentGUIFrame.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseHelper helper=new DatabaseHelper();
				ArrayList professorsList=helper.getFacultiesDataFromTestFaculty();
				professorsList.remove(Integer.parseInt(selectedIndex)+1);				
				try{
					CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataFaculty.csv"));
					Iterator iterator=professorsList.iterator();
					while(iterator.hasNext()){
						Professor professor=(Professor)iterator.next();
						String [] record = {professor.getLastName(),professor.getFirstName(),professor.getGradSchool(),professor.getDegree(),professor.getTitle(),professor.getDaysToTeach(),professor.getMaxLoadFall(),professor.getMaxLoadSpring(),professor.getMaxLoadSummer()};
						writer.writeNext(record);
					}					
					writer.close();
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();
				}catch(Exception ex){
					ex.printStackTrace();
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