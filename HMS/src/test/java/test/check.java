 package test;

import org.apache.poi.EncryptedDocumentException;

import utilities.Utility;

public class check {
	
	public static void main(String[] args) throws EncryptedDocumentException, Exception {
		
		String a = Utility.getExlData("Patient_Login", 0, 0);
		System.out.println(a);
		String f = Utility.getExlData("admin", 0, 0);
		System.out.println(f);
//		Utility.getSheet(, a)
		
	}

}
