package com.via.utils;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvReader {

	private CSVReader cs;

	public CsvReader (String fileNameWithLocation) {
		
		  try {
			  // Read file using csvreader
			cs = new CSVReader(new FileReader(fileNameWithLocation));
		}
		  catch (Exception e) {
			  
			  System.out.println("Error with file Reading"+e.getMessage());
		} 
	}
	
	public void getAllData() throws Exception {
	
	   List<String[]> allData = cs.readAll(); 
		 for(int i=0;i< allData.size() ; i++) {
			 String rowData[] = allData.get(i);
			 for(int j=0; j< rowData.length ;j++) {
				 System.out.println(rowData[j]+"\t");
			 }
		 }
	}
	
}
