package edu.gatech.seclass.project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Grades {

	String DB = null;
	int[][] teamProjects;
	int numAssignments;
	int numProjects;
	
	public Grades(String db, HashSet<Student> studentRoster, int numTeam) {
		DB = db;
		
		try {
			
		    FileInputStream file = new FileInputStream(new File(DB));
		     
		    //Get the workbook instance for XLS file 
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		 
		    //sheet iterator
		    for(int sheetNum = 3;sheetNum < 6;sheetNum++) {
		    	//Get the sheet from the workbook
		    	XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		     
		    	//Iterate through each rows from first sheet
		    	Iterator<Row> rowIterator = sheet.iterator();
		    
		    	Row row = rowIterator.next();//first row
		    	Iterator<Cell> cellIterator = row.cellIterator();
		    	int count = -1; //num of column
		    	while(cellIterator.hasNext()) {
		    		count++;
	        		cellIterator.next();
		    	}
		    	switch(sheetNum){
		    		case 3:
		    			numAssignments = count;
		    		case 4:
		    			numProjects = count;
		    		case 5:
		    		teamProjects = new int[numTeam][count];
		    	}
		    	
		    	if(sheetNum == 5)//array of teamworks
		    	{
		    		teamProjects = new int[numTeam][count];		    		
		    	}
		    	
		    	Student student = new Student();
		    	int team = 0;// used for the loop of the 6th sheet
		    	
		    	while(rowIterator.hasNext()) {
		    		row = rowIterator.next();
		    		//For each row, iterate through each columns
	    			cellIterator = row.cellIterator();
	    			
	    			switch(sheetNum)
			    	{
			    		case 3:
			    			int i = 0;
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		student = getByID((int)cell.getNumericCellValue(),studentRoster);
				                		student.assignments = new int[count];
				                		i++;
				                		break;
				                	default:
				                		student.assignments[i-1] = (int)cell.getNumericCellValue();
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			break;
			    			
			    		case 4:
			    			i = 0;
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		student = getByID((int)cell.getNumericCellValue(),studentRoster);
				                		student.projects = new int[count];
				                		i++;
				                		break;
				                	default:
				                		student.projects[i-1] = (int)cell.getNumericCellValue();
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			break;
			    		case 5:
			    			i = 0;			    			
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		i++;
				                		break;
				                	default:
				                		teamProjects[team][i-1] = (int)cell.getNumericCellValue();
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			team++;
			    			break;
			    	}
		    	}
		    }
		    file.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public Grades(String db2) {
		DB = db2;
		Students students = new Students(db2);
		int numTeam = students.numTeam;
		HashSet<Student> studentRoster = students.StudentRoster;
		try {
		    FileInputStream file = new FileInputStream(new File(DB));
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		    for(int sheetNum = 3;sheetNum < 6;sheetNum++) {
		    	XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		    	Iterator<Row> rowIterator = sheet.iterator();
		    
		    	Row row = rowIterator.next();//first row
		    	Iterator<Cell> cellIterator = row.cellIterator();
		    	int count = -1; //num of column
		    	while(cellIterator.hasNext()) {
		    		count++;
	        		cellIterator.next();
		    	}
		    	switch(sheetNum){
		    		case 3:
		    			numAssignments = count;
		    		case 4:
		    			numProjects = count;
		    		case 5:
		    		teamProjects = new int[numTeam][count];
		    	}
		    	
		    	if(sheetNum == 5)//array of teamworks
		    	{
		    		teamProjects = new int[numTeam][count];		    		
		    	}
		    	
		    	Student student = null;
		    	int team = 0;// used for the loop of the 6th sheet
		    	
		    	while(rowIterator.hasNext()) {
		    		row = rowIterator.next();
		    		//For each row, iterate through each columns
	    			cellIterator = row.cellIterator();
	    			
	    			switch(sheetNum)
			    	{
			    		case 3:
			    			int i = 0;
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		student = getByID((int)cell.getNumericCellValue(),studentRoster);
				                		student.assignments = new int[count];
				                		i++;
				                		break;
				                	default:
				                		student.assignments[i-1] = (int)cell.getNumericCellValue();
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			break;
			    			
			    		case 4:
			    			i = 0;
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		student = getByID((int)cell.getNumericCellValue(),studentRoster);
				                		student.projects = new int[count];
				                		i++;
				                		break;
				                	default:
				                		student.projects[i-1] = (int)cell.getNumericCellValue();
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			break;
			    		case 5:
			    			i = 0;			    			
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		i++;
				                		break;
				                	default:
				                		teamProjects[team][i-1] = (int)cell.getNumericCellValue();
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			team++;
			    			break;
			    	}
		    	}
		    }
		    file.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public Student getByID(int GT_ID, HashSet<Student> studentRoster) {		
		Student student = new Student();
		for(Student s:studentRoster)
		{
			if(s.getGtid() == GT_ID)
			{
				student = s;
				break;
			}
		}
		return student;
	}

	public void addAssignment(String string){
		try {
			FileInputStream file = new FileInputStream(new File(DB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(3);
			CellStyle style = workbook.createCellStyle();
			Iterator<Row> rowIterator = sheet.iterator();
			Row row1 = rowIterator.next();
			Iterator<Cell> cellIterator = row1.cellIterator();
			int i = 0;
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				style = cell.getCellStyle();
				i++;
			}
			Cell newCell = row1.createCell(i);
			newCell.setCellValue(string);
			newCell.setCellStyle(style);
			
			while(rowIterator.hasNext()) {
	    		row1 = rowIterator.next();
    			row1.createCell(i);			  
			}
		    			
			FileOutputStream out = 
		            new FileOutputStream(new File(DB));
		    workbook.write(out);
		    file.close();
		    out.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public void setAssignments(Student s, String assignmentName, int grade) {
		try {
			FileInputStream file = new FileInputStream(new File(DB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(3);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();//first row
	    	Iterator<Cell> cellIterator = row.cellIterator();
	    	int count = -1; //num of column
	    	int orderOfAssignment = 0;//order of the assignment
	    	while(cellIterator.hasNext()) {
	    		count++;
	    		if(assignmentName.equals(cellIterator.next().getStringCellValue())){
	    			orderOfAssignment = count;
	    		}
	    	}
	    	s.assignments = new int[count];
	    	boolean modified = false;
	    	while(rowIterator.hasNext()) {
	    		row = rowIterator.next();
	    		//For each row, iterate through each columns
    			cellIterator = row.cellIterator();
    			int i = 0;
    			while(cellIterator.hasNext()) {
	        		Cell cell = cellIterator.next();
	        		switch(i) {
	                	case 0:
	                		if(s.getGtid()==(int)cell.getNumericCellValue()){
	                			modified = true;
	                		}
	                		i++;
	                		break;
	                	default:
	                		if(i == orderOfAssignment&&modified == true){
		                		cell.setCellValue(grade);
		                		s.assignments[i-1] = grade;
		                		modified = false;
	                		}
	                		else if(modified == true)
	                		s.assignments[i-1] = (int)cell.getNumericCellValue();
	                		i++;
	                		break;
	        		}				             				                         
	            }
			}
			FileOutputStream out = 
		            new FileOutputStream(new File(DB));
		    workbook.write(out);
		    file.close();
		    out.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
	}

	public void setContribution(Student s, String projectName1, int contribution) {
		try {
			FileInputStream file = new FileInputStream(new File(DB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(4);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();//first row
	    	Iterator<Cell> cellIterator = row.cellIterator();
	    	int count = -1; //num of column
	    	int orderOfAssignment = 0;//order of the assignment
	    	while(cellIterator.hasNext()) {
	    		count++;
	    		if(projectName1.equals(cellIterator.next().getStringCellValue())){
	    			orderOfAssignment = count;
	    		}
	    	}
	    	s.projects = new int[count];
	    	boolean modified = false;
	    	while(rowIterator.hasNext()) {
	    		row = rowIterator.next();
	    		//For each row, iterate through each columns
    			cellIterator = row.cellIterator();
    			int i = 0;
    			while(cellIterator.hasNext()) {
	        		Cell cell = cellIterator.next();
	        		switch(i) {
	                	case 0:
	                		if(s.getGtid()==(int)cell.getNumericCellValue()){
	                			modified = true;
	                		}
	                		i++;
	                		break;
	                	default:
	                		if(i == orderOfAssignment&&modified == true){
		                		cell.setCellValue(contribution);
		                		s.projects[i-1] = contribution;
		                		modified = false;
	                		}
	                		else if(modified == true)
	                		s.projects[i-1] = (int)cell.getNumericCellValue();
	                		i++;
	                		break;
	        		}				             				                         
	            }
			}
			FileOutputStream out = 
		            new FileOutputStream(new File(DB));
		    workbook.write(out);
		    file.close();
		    out.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
	}

	public void addStudent(Student s) {
		try {
			FileInputStream file = new FileInputStream(new File(DB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(3);
			Iterator<Row> rowIterator = sheet.iterator(); 			
			Row row = rowIterator.next();
			int i = 1;
			while(rowIterator.hasNext()) {
			    row = rowIterator.next();
			    i++;
			}
			row = sheet.createRow(i);
		    for(i = 0;i < 4;i++){
			    row.createCell(i);
		    }
		    row.getCell(0).setCellValue(s.getGtid());
		    
		    
		    sheet = workbook.getSheetAt(4);
			rowIterator = sheet.iterator(); 			
			row = rowIterator.next();
			i = 1;
			while(rowIterator.hasNext()) {
			    row = rowIterator.next();
			    i++;
			}
			row = sheet.createRow(i);
		    for(i = 0;i < 4;i++){
			    row.createCell(i);
		    }
		    row.getCell(0).setCellValue(s.getGtid());
		    
			FileOutputStream out = 
		            new FileOutputStream(new File(DB));
		    workbook.write(out);
		    file.close();
		    out.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
	}

	public void addProject(String projectName) {
		try {
			FileInputStream file = new FileInputStream(new File(DB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			//Individual Contributions
			XSSFSheet sheet = workbook.getSheetAt(4);
			CellStyle style = workbook.createCellStyle();
			Iterator<Row> rowIterator = sheet.iterator();
			Row row1 = rowIterator.next();
			Iterator<Cell> cellIterator = row1.cellIterator();
			int i = 0;
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				style = cell.getCellStyle();
				i++;
			}
			Cell newCell = row1.createCell(i);
			newCell.setCellValue(projectName);
			newCell.setCellStyle(style);
			
			while(rowIterator.hasNext()) {
	    		row1 = rowIterator.next();
    			row1.createCell(i,Cell.CELL_TYPE_NUMERIC).setCellValue(0);;
			}
		    
			//Teamgrades
			sheet = workbook.getSheetAt(5);
			style = workbook.createCellStyle();
			rowIterator = sheet.iterator();
			row1 = rowIterator.next();
			cellIterator = row1.cellIterator();
			i = 0;
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				style = cell.getCellStyle();
				i++;
			}
			newCell = row1.createCell(i);
			newCell.setCellValue(projectName);
			newCell.setCellStyle(style);
			
			while(rowIterator.hasNext()) {
	    		row1 = rowIterator.next();
	    		row1.createCell(i,Cell.CELL_TYPE_NUMERIC).setCellValue(0);;
			}
			
			
			FileOutputStream out = 
		            new FileOutputStream(new File(DB));
		    workbook.write(out);
		    file.close();
		    out.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
	}

	public void setProjects(Student s, String projectName, int grade) {
		try {
			FileInputStream file = new FileInputStream(new File(DB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(5);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();//first row
	    	Iterator<Cell> cellIterator = row.cellIterator();
	    	int count = -1; //num of column
	    	int orderOfProject = 0;//order of the project
	    	while(cellIterator.hasNext()) {
	    		count++;
	    		if(projectName.equals(cellIterator.next().getStringCellValue())){
	    			orderOfProject = count;
	    		}
	    	}

	    	boolean modified = false;
	    	while(rowIterator.hasNext()) {
	    		row = rowIterator.next();
	    		//For each row, iterate through each columns
    			cellIterator = row.cellIterator();
    			int i = 0;
    			while(cellIterator.hasNext()) {
	        		Cell cell = cellIterator.next();
	        		switch(i) {
	                	case 0:
	                		if(s.getTeam().equals(cell.getStringCellValue())){
	                			modified = true;
	                		}
	                		i++;
	                		break;
	                	default:
	                		if(i == orderOfProject&&modified == true){
		                		cell.setCellValue(grade);
		                		modified = false;
	                		}
	                		else if(modified == true)
	                		i++;
	                		break;
	        		}				             				                         
	            }
			}
			FileOutputStream out = 
		            new FileOutputStream(new File(DB));
		    workbook.write(out);
		    file.close();
		    out.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
}
