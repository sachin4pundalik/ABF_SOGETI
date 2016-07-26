/**
 * 
 */
package com.sogeti.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author vkalyana
 *
 */
public class DigestUtil {

	public static String getEncripted(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		/*MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashPassword = md.digest(value.getBytes());*/
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(value.getBytes("UTF-8"));
		String hashedPassword = convertByteArrayToHexString(hashedBytes);
		
		return hashedPassword;
	}
	
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
	    StringBuffer stringBuffer = new StringBuffer();
	    for (int i = 0; i < arrayBytes.length; i++) {
	        stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
	                .substring(1));
	    }
	    return stringBuffer.toString();
	}
	
}
