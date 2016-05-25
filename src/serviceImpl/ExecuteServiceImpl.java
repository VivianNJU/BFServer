//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		String result = "";
		
		char[] data = param.toCharArray();
		int dataPtr = 0;
		
		char[] codeArray = code.toCharArray();
		int codePtr = 0;
		
		int[] register = new int[100];
		int registerPtr = 0;
		
		int circle = 0;
		for(codePtr = 0;codePtr<codeArray.length;codePtr++){
			switch (codeArray[codePtr]){
			case '<': 
				registerPtr--;
				break;
            case '>': 
            	registerPtr++;
            	break;
            case '+':
            	register[registerPtr]++;
            	break;
            case '-':
            	register[registerPtr]--;
            	break;
            case '.':
            	char temp = (char) register[registerPtr];
            	result = result+temp;
            	break;
            case ',':
            	register[registerPtr]=(int) data[dataPtr];
            	dataPtr++;
            	break;
            case '[':
            	if(register[registerPtr]==0){
            		circle++;         		
            		while(circle!=0){
            			codePtr++;
            			if(codeArray[codePtr]=='['){
            				circle++;
            			}else if(codeArray[codePtr]==']'){
            				circle--;
            			}
            			
            		}
            	}
            	break;
            case ']':
            	if(register[registerPtr]!=0){
            		circle++;         		
            		while(circle!=0){
            			codePtr--;           			
            			if(codeArray[codePtr]==']'){
            				circle++;
            			}else if(codeArray[codePtr]=='['){
            				circle--;
            				
            			}
            			
            		}
            	}
            	break;
			}
		}
		return result;
	}

	

}
