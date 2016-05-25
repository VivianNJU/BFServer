package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	String userId;
	
	void setUserId(String id){
		userId = id;
	}

	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	@Override
	public String readFile(String userId, String fileName) {
		File f = new File(userId + "_" + fileName);
		String result = "";
		String temp;
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while((temp = br.readLine())!=null){
				result = result+temp;
			}
			br.close();
			fr.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public String readFileList(String userId) {
		// TODO Auto-generated method stub
		return "OK";
	}
	
	

	
	
}
