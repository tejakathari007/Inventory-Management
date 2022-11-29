package com.quinnox.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Autowired
	private Configuration config;

//	public String sendEmail(String mails) {
//
//		SimpleMailMessage msg = new SimpleMailMessage();
//
//		msg.setFrom(fromEmail);
//		msg.setTo(mails.split(","));
//
//		msg.setSubject("Testing from Spring Boot APP");
//		msg.setText("Hello World \n Spring Boot Email");
//
//		javaMailSender.send(msg);
//
//		return "Email Sent Successfully";
//
//	}

//	public String sendEmailWithAttachment(String mails) throws MessagingException, IOException {
//		
//		 Map<String, Object> model = new HashMap<>();  
//	     model.put("name", "TEJA");
//
//		MimeMessage msg = javaMailSender.createMimeMessage();
//
//		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//		helper.setFrom(fromEmail);
//		helper.setTo(mails.split(","));
//		helper.setSubject("Testing from Spring Boot");
//		helper.setMailContent(geContentFromTemplate(mail.getModel()));
//
//		javaMailSender.send(msg);
//
//		return "Email Sent Successfully";
//
//	}
//	
//	
//	    public String geContentFromTemplate(Map<String, Object> model)     { 
//	        StringBuffer content = new StringBuffer();
//	 
//	        try {
//	            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate("email-template.flth"), model));
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return content.toString();
//	    }
//	 }

	public String sendEmail(Map<String, Object> model, String mails)
			throws MessagingException, IOException, TemplateException {
		MimeMessage message = javaMailSender.createMimeMessage();
		// set mediaType
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		// add attachment
		Template t = config.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		helper.setTo(mails.split(","));
		helper.setText(html, true);
		helper.setSubject("Testing from Spring Boot");
		helper.setFrom(fromEmail);
		javaMailSender.send(message);
		return "Email Sent Successfully";
	}

}
