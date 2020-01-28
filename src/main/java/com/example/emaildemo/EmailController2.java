package com.example.emaildemo;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController2 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping(value="/sendemail2")
	public String sendemail(){
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
		//The following controller embeds the cat picture to the email as an inline resource. 
		//The recipient will see the resource embedded in the email (not as an attachment)
		MimeMessageHelper helper=new MimeMessageHelper(message, true);
		
		helper.setTo("v.k.marialavi.2@gmail.com");
		helper.setSubject("Inline Resource");
		helper.setText("<html><body>Here is a cat picture! <img src='cid:id101'/><body></html>", true);
		
		ClassPathResource file=new ClassPathResource("cat.jpg");
		helper.addInline("id101", file);
		
		sender.send(message);
	}
}
