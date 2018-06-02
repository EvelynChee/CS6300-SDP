package edu.gatech.seclass.project3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Students {
	
	String DB = null;
	HashSet<Student> StudentRoster = new HashSet<Student>();
	int numTeam = 0;
	public Students(String db) {
		DB = db;
		
		try {
			
		    FileInputStream file = new FileInputStream(new File(DB));
		     
		    //Get the workbook instance for XLS file 
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		 
		    //sheet iterator
		    for(int sheetNum=0;sheetNum<3;sheetNum++) {
		    	
		    	//Get the sheet from the workbook
		    	XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		     
		    	//Iterate through each rows from first sheet
		    	Iterator<Row> rowIterator = sheet.iterator();
		    
		    	Row row = rowIterator.next();
		    	while(rowIterator.hasNext()) {
		    		row = rowIterator.next();
		    		//For each row, iterate through each columns
	    			Iterator<Cell> cellIterator = row.cellIterator();
		    
		    		switch(sheetNum){
			    		case 0:
			    			Student student = new Student(); 
			    			StudentRoster.add(student);
			    			int i = 0;
			        	
			    			while(cellIterator.hasNext()) {
				        		Cell cell = cellIterator.next();
				        		switch(i) {
				                	case 0:
				                		student.setName(cell.getStringCellValue());
				                		i++;
				                		break;
				                	case 1:
				                		student.setGtid(cell.getNumericCellValue());
				                		i++;
				                		break;
				                	case 2:
				                		student.setEmail(cell.getStringCellValue());
				                		i++;
				                		break;
				                	default:
				                		i++;
				                		break;
				        		}				             				                         
				            }
			    			break;
			    			
			    		case 1:
			    			String team = "";
			    			i = 0;
			    			while(cellIterator.hasNext()) {	
			    				Cell cell = cellIterator.next();
			    				
				        		switch(i) {
				                	case 0:
				                		team=cell.getStringCellValue();
				                		numTeam++;
				                		i++;
				                		break;
				                	default:
				                		student = getByID((int)cell.getNumericCellValue());
				                		student.setTeam(team);
				                		i++;
				                		break;
				        		}		    
			    			}	                		
			    			
			    			break;
			    			
			    		case 2:
			    			int GT_ID = 0;
			    			i = 0;
			    			while(cellIterator.hasNext()) {	
			    				Cell cell = cellIterator.next();
			    				
				        		switch(i) {
				                	case 0:
				                		GT_ID=(int)cell.getNumericCellValue();
				                		i++;
				                		break;
				                	case 1:
				                		student = getByID(GT_ID);
				                		student.setAttendance((int)cell.getNumericCellValue());
				                		i++;
				                		break;
				        		}		    
			    			}
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

	public Student getByName(String string) {
		Student student = new Student();
		for(Student s:StudentRoster)
		{
			if(s.getName().equals(string))
			{
				student = s;
				break;
			}
		}

		return student;
	}

	public Student getByID(int GT_ID) {		
		Student student = new Student();
		for(Student s:StudentRoster)
		{
			if(s.getGtid() == GT_ID)
			{
				student = s;
				break;
			}
		}
		return student;
	}


	public void addStudent(Student s) {
		StudentRoster.add(s);
		try{
		 FileInputStream file = new FileInputStream(new File(DB));
	     
		    //Get the workbook instance for XLS file 
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		    	
		    //Get the Students sheet from the workbook
		    XSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    
		    Row row = rowIterator.next();
		    int i = 1;
		    while(rowIterator.hasNext()) {
		    	row = rowIterator.next();
		    	i++;
		    }
		    row = sheet.createRow(i);
		    
		    row.createCell(0, Cell.CELL_TYPE_STRING);
		    row.createCell(1, Cell.CELL_TYPE_NUMERIC);
		    
		    row.getCell(0).setCellValue(s.getName());
		    row.getCell(1).setCellValue(s.getGtid());

		    
		    
		    //Get the Attendance sheet from the workbook
		    sheet = workbook.getSheetAt(2);
		     
		    //Iterate through each rows from first sheet
		    rowIterator = sheet.iterator();
		    
		    row = rowIterator.next();
		    i = 1;
		    while(rowIterator.hasNext()) {
		    	row = rowIterator.next();
		    	i++;
		    }
		    row = sheet.createRow(i);
		    
		    row.createCell(0);
		    row.createCell(1);
		    row.getCell(0).setCellValue(s.getGtid());
		    row.getCell(1).setCellValue(s.getAttendance());
		    
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
