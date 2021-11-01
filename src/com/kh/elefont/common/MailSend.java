package com.kh.elefont.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kh.elefont.order.model.vo.Order;
import com.kh.elefont.order.model.vo.OrderExt;
import com.kh.elefont.order.util.MailAuth;

public class MailSend {

	public void purchaseMailSend(List<Order> orderList, List<String> attachList) {
		Properties prop = System.getProperties();
		OrderExt order = (OrderExt) orderList.get(0);
		String memberId = order.getMemberId();
		String memberEmail = order.getMemberEmail();
		List<String> fontNames = new ArrayList<>();
		
		for(Order _oe : orderList) {
			OrderExt oe = (OrderExt) _oe;
			fontNames.add(oe.getFontName());
		};
		
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");
		
		Authenticator auth = new MailAuth();
		Session session = Session.getDefaultInstance(prop, auth);
		
		MimeMessage msg = new MimeMessage(session);
		
		//메일 출력 텍스트
		StringBuffer sb = new StringBuffer();
		sb.append("<h3>" + memberId + "님 안녕하세요</h3>");
		sb.append("<span>Elefont를 이용해 주셔서 감사합니다.</span></br>");
		sb.append("<span>"+ memberId+"님께서 구매하신 폰트는 다음과 같습니다.</span></br>");
		sb.append("<h4>"+ fontNames.toString() +"</h4>");
		sb.append("<span>앞으로도 많은 이용 부탁 드립니다.</span></br>");
		sb.append("<h5>감사합니다.</h5>");
		
		String html = sb.toString();
		
		try {
			msg.setSentDate(new Date());
			new MailAuth();
			msg.setFrom(new InternetAddress(MailAuth.MAILID, "Elefont"));
			InternetAddress to = new InternetAddress(order.getMemberEmail());
			msg.setRecipient(Message.RecipientType.TO, to);
			
			Multipart multipart = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();
			MimeBodyPart mFilePart = new MimeBodyPart();
			
			msg.setSubject( memberId + "님, 구매해 주셔서 감사합니다.");
//			msg.setText(html, "UTF-8", "html");
			mTextPart.setText(html, "UTF-8", "html");
			multipart.addBodyPart(mTextPart);
			
			//보낼 첨부파일이 여러개일 경우
			if(attachList.size() > 1) {
				for(int i = 0; i < attachList.size(); i++) {
					FileDataSource fds = new FileDataSource(attachList.get(i));
					mFilePart.setDataHandler(new DataHandler(fds));
					mFilePart.setFileName(fds.getName());
					multipart.addBodyPart(mFilePart);
					
				}
			}else {
				FileDataSource fds = new FileDataSource(attachList.get(0));
				mFilePart.setDataHandler(new DataHandler(fds));
				mFilePart.setFileName(fds.getName());
				multipart.addBodyPart(mFilePart);
			}
			
			
			msg.setContent(multipart);
			
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
