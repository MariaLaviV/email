package com.example.emaildemo;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController1 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping(value="/sendemail1")
	public String sendEmail(){
		try {
			sendmail();
			return "Email sent";
		} catch (Exception e) {
			return "Error sending in email:"+e;
		}
	}

	private void sendmail() throws Exception{
		MimeMessage message=sender.createMimeMessage();
		
		// Enable the multipart flag!
		//sending email with attachment
		MimeMessageHelper helper=new MimeMessageHelper(message, true);
		helper.setTo("v.k.marialavi.2@gmail.com");
		helper.setSubject("mail with attachment");
		helper.setText("Hi Maria,cat jpeg image is attached");
		
		ClassPathResource file= new ClassPathResource("cat.jpg");
		helper.addAttachment("cat.jpg", file);
		
		sender.send(message);
	}
}
