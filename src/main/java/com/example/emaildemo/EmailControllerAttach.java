package com.example.emaildemo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailControllerAttach {
	
	@Autowired
	private JavaMailSender sender;

	@RequestMapping(value="/sendmailAtt")
	public String sendmailAttachment() throws MessagingException{
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message, true);
		try {
			helper.setTo("v.k.marialavi.2@gmail.com");
			helper.setText("Greetings :)\n Please find the attached docuemnt for your reference.");
			helper.setSubject("Mail From Spring Boot");
			ClassPathResource file= new ClassPathResource("Lavi.txt");
			helper.addAttachment("Lavi.txt", file);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error sending in mail";
		}
		sender.send(message);
		return "Mail sent Successfully";
	}
}
