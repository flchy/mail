package com.flchy.blog.plugin.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.flchy.blog.plugin.entity.Mail;

import freemarker.template.Template;

/**
 * 邮件操作类
 * 
 * @author flchy
 *
 */
@Component("mailUtil")
public class MailUtil {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	 // 自动注入的Bean
	@Autowired
	private JavaMailSender mailSender;
	
	 // 读取配置文件中的参数
	@Value("${spring.mail.username}")
	private String sender;

	public  void send(Mail mail) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getTitle());

			Map<String, Object> model = new HashMap<>();
			model.put("title", mail.getTitle());
			model.put("content", mail.getContent());
			// 读取 html 模板
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			helper.setText(html, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}
