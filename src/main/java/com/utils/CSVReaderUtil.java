package com.utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReaderUtil {
	public static List<String[]> readCSV(String filePath) throws FileNotFoundException, IOException{
		List<String[]> data = new ArrayList<>();
		try(BufferedReader br= new BufferedReader(new FileReader(filePath))) {
			String line;
			
			while((line=br.readLine())!=null){
				String[] values = line.split(",");
				data.add(values);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
		
	}
}
