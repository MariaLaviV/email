package com.example.emaildemo;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
	 @Autowired
	    private JavaMailSender sender;

	@RequestMapping(value="/sendemail")
	public String sendmail() {
		try {
			sendEmail();
			return "Email sent";
		} catch (Exception e) {
			return "Error in sending email:"+e;
		}
	}

	private void sendEmail() throws Exception{
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		helper.setTo("v.k.marialavi.2@gmail.com");
		helper.setText("How are you?");
		helper.setSubject("Hi");
		
		sender.send(message);
	}

}

/*Note:for this example dont enable 2 step verification instead use this url 
 * https://myaccount.google.com/lesssecureapps => Allow less secure apps: ON
enable the toggle button

 https://accounts.google.com/b/0/DisplayUnlockCaptcha click on continue then this will come
 Account access enabled
Please try signing in to your Google Account again from your new device or application.

postman i/p: http://localhost:8080/sendemail
 		o/p: Email sent*/
