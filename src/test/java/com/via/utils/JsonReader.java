package com.via.utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonReader {
	public FileReader reader;
	private JSONObject jsonObject;
	
	public JsonReader() throws Exception, ParseException {	
		try {
			// to read the file to buffer
			reader = new FileReader(".\\src\\test\\resources\\files\\input\\input.json");
			// to create object for input.json file
			JSONParser jsonparser = new JSONParser();
			//to convert to object
			Object obj=jsonparser.parse(reader);  
			//casting
		    jsonObject = (JSONObject) obj;
		    
			} catch (FileNotFoundException e) {
			System.out.println("Error Reading file : "+e.getMessage());
		}
	}

	
	public String getData(String array,String data,int j) throws Exception, ParseException {
	    
	    JSONArray jsonArray = (JSONArray) jsonObject.get(array);
	    
	    String arr[] = new String[jsonArray.size()+1];
	    
	    for(int i=0;i<jsonArray.size();i++) {  
	    	
	    	JSONObject user = (JSONObject) jsonArray.get(i); 
	    	
	    	arr[i+1] = (String) user.get(data);	    	
	    }	
	    return arr[j];
	}
	
	
	
	public void getAllData(String array,String data) {
		
	    JSONArray jsonArray = (JSONArray) jsonObject.get(array);
	    String arr[] = new String[jsonArray.size()];
	    for(int i=0;i<jsonArray.size();i++) {   	
	    	JSONObject user = (JSONObject) jsonArray.get(i);    	
	    	arr[i] = (String) user.get(data);
	    	System.out.println(data+"["+(i+1)+"] : "+arr[i]);
	    	
	    }
    }
	
	public Object[] readData(String array) {
		
	    JSONArray jsonArray = (JSONArray) jsonObject.get(array);
	    return jsonArray.toArray();
	    
	    }
	
}
	