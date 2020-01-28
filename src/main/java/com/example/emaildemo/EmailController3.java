package com.example.emaildemo;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.Template;

@RestController
public class EmailController3 {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@RequestMapping(value="/sendemail3")
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
		
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", "Lavi");
        
     // set loading location to src/main/resources
        // You may want to use a subfolder such as /templates here
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
         
        Template t = freemarkerConfig.getTemplate("welcome.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
 
        helper.setTo("v.k.marialavi.2@gmail.com");
        helper.setText(text, true); // set to html
        helper.setSubject("Hi");
 
        sender.send(message);
	}
}
