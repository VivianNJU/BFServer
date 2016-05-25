package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.MessageDigest;

import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		
		File f = new File("user_database");
		String temp = "";
		String[] information;
		password = encrypt(password);
		boolean result = false;
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while((temp = br.readLine())!=null){
				information = temp.split(";");
				if(information[1].equals(username)&&information[2].equals(password)){
					result = true;
					break;
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		
		return true;
	}
	
	
	public void addNewUser(String username,String password){
		String temp = "";
		String result = "";
		int userId = 0;
		password = encrypt(password);
		File f = new File("user_database");
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while((temp = br.readLine())!=null){
				result = temp;
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(result!=""){
			String[] information = result.split(";");
			userId = Integer.valueOf(information[0])+1;
		}
		
		
		try {
			FileWriter fw = new FileWriter("user_database", true);
			fw.write(userId+";"+username+";"+password+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String encrypt(String password){
		String result = "";
		try{
			MessageDigest messageDigest=MessageDigest.getInstance("MD5"); 
			byte[] b = messageDigest.digest(password.getBytes());
			
			//为了防止输出乱码，将加密后的密码转换为十六进制
			for (int i = 0; i < b.length; i++) {
			    String tmp = Integer.toHexString(b[i] & 0xFF);
			    if (tmp.length() == 1) {
			    	result += "0" + tmp;
			    } else {
			    	result += tmp;
			    }
			}
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}
	

}
