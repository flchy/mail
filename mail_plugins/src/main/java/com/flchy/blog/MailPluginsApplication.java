package com.flchy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flchy.blog.plugin.blockingQueue.MailQueue;

@SpringBootApplication
public class MailPluginsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailPluginsApplication.class, args);
	}
}
