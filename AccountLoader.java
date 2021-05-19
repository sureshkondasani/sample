package com.cynosure.drms.datalod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import com.cynosure.drms.vo.Account;

public class AccountLoader {
	Method []mthds=Account.class.getDeclaredMethods();
	Map<String, ArrayList<Account>> hashMap=new HashMap<String, ArrayList<Account>>();
	List<Account> accList=new ArrayList<Account>();
	StringTokenizer stknzer=null;

	String[] columnHeaders;
	//This method can load accounts from flat file to test
	public void loadAccounts(){
		BufferedReader br=null;

		try {
			InputStream in =  getClass().getResourceAsStream("StudentAcctData.txt");
			Reader fr = new InputStreamReader(in, "utf-8");
			br = new BufferedReader(fr);
			String str=br.readLine();
			int startIndex=0;
			System.out.println(str);
			String strng="F|ooo";

			StringTokenizer stknzer = new StringTokenizer(str, "|");
			columnHeaders= new String[stknzer.countTokens()];
			System.out.println("No of tokens: "+stknzer.countTokens());
			while (stknzer.hasMoreTokens()) {
				columnHeaders[startIndex++]=(String)stknzer.nextElement();
			}
			System.out.println("Hello :"+columnHeaders[0]);

			while (str!=null) {
				str=br.readLine();
				if(str!=null){
					stknzer=new StringTokenizer(str.trim(), ",");
					populateAccounts(stknzer);
				}
			}
			System.out.println("No of Accounts: "+accList.size());
			System.out.println(accList.get(28).getEducationalInstitution());
			loadToMap(accList);
			
			System.out.println(hashMap.get("I").size());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("one");
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("one1");
		}finally{
			try {
				br.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void populateAccounts(StringTokenizer stknzr){

		Account acc= new Account();
		Method[] arrangedMethods=syncColumnWithMthdNames(columnHeaders,mthds);
		System.out.println(arrangedMethods[9].getName());
		for (Method method : arrangedMethods) {
			try {
				System.out.println(method.getName());
				if(method.getName().startsWith("set")){
					method.invoke(acc, stknzr.nextToken());
					//System.out.println(method.getName());

				}

			} catch (IllegalAccessException IAE
IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		accList.add(acc);
	}

	private   Method[] syncColumnWithMthdNames(String []columnNames,Method[] arr){
		Method[] arrangeMethods=new Method[columnNames.length];
		//int noOfColumns=arrangeMethods.length;
		int startIndex=0;

		while(startIndex<columnNames.length){
			for (Method method : arr) {

				if(method.getName().startsWith("set")){
					//System.out.println("methodName "+method.getName()+"  -  "+columnNames[startIndex]);
					if(method.getName().indexOf(columnNames[startIndex])>=0){
						arrangeMethods[startIndex]=method;
						break;
					}
				}
			}
			startIndex++;
		}
		return arrangeMethods;
	}

	//This method can map the account information to Map by name wise
	private void loadToMap(List<Account> acctLst){
		String []alphabets={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};	

		for (String string : alphabets) {
			hashMap.put(string, new ArrayList<Account>());
		}
		for (Account account: acctLst) {
			addAccount(account);
		}
	}

	public void addAccount(Account acct){
		String charStr=acct.getUserName().substring(0, 1);
		switch (charStr) {
		case "a":
		case "A":hashMap.get("A").add(acct);break;
		case "b":
		case "B":hashMap.get("B").add(acct);break;
		case "c":
		case "C":hashMap.get("C").add(acct);break;
		case "d":
		case "D":hashMap.get("D").add(acct);break;
		case "e":
		case "E":hashMap.get("E").add(acct);break;
		case "f":
		case "F":hashMap.get("F").add(acct);break;
		case "g":
		case "G":hashMap.get("G").add(acct);break;
		case "h":
		case "H":hashMap.get("H").add(acct);break;
		case "i":
		case "I":hashMap.get("I").add(acct);break;
		case "j":
		case "J":hashMap.get("J").add(acct);break;
		case "k":
		case "K":hashMap.get("K").add(acct);break;
		case "l":
		case "L":hashMap.get("L").add(acct);break;
		case "m":
		case "M":hashMap.get("M").add(acct);break;
		case "n":
		case "N":hashMap.get("N").add(acct);break;
		case "o":
		case "O":hashMap.get("O").add(acct);break;
		case "p":
		case "P":hashMap.get("P").add(acct);break;
		case "q":
		case "Q":hashMap.get("Q").add(acct);break;
		case "r":
		case "R":hashMap.get("R").add(acct);break;
		case "s":
		case "S":hashMap.get("S").add(acct);break;
		case "t":
		case "T":hashMap.get("T").add(acct);break;
		case "u":
		case "U":hashMap.get("U").add(acct);break;
		case "v":
		case "V":hashMap.get("V").add(acct);break;
		case "w":
		case "W":hashMap.get("W").add(acct);break;
		case "x":
		case "X":hashMap.get("X").add(acct);break;
		case "y":
		case "Y":hashMap.get("Y").add(acct);break;
		case "z":
		case "Z":hashMap.get("Z").add(acct);break;			
		//default:hashMap.get("H").add(string);break;
		//break;
		}
	}
	public Map<String, ArrayList<Account>> getHashMap() {
		return hashMap;
	}

	public void setHashMap(Map<String, ArrayList<Account>> hashMap) {
		this.hashMap = hashMap;
	}

	
}
