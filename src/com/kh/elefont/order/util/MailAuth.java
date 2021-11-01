package com.kh.elefont.order.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
	
	PasswordAuthentication pa;
	public static final String MAILID = "watt.kwon";
	public static final String MAILPW = "flxrndymebctkrpk";
	
	public MailAuth() {
		
		pa = new PasswordAuthentication(MAILID, MAILPW);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

}
