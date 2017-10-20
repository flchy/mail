package com.flchy.blog.plugin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flchy.blog.plugin.blockingQueue.MailQueue;
import com.flchy.blog.plugin.entity.Mail;
import com.flchy.blog.plugin.response.ResponseCommand;

@RestController
@RequestMapping
public class MailController {
	@PostMapping(value = "/send")
	public ResponseCommand send(Mail mail){
		if(MailQueue.offer(mail)){
			return new ResponseCommand();
		}else{
			return new ResponseCommand("发送失败");
		}
		
	}
}
