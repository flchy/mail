package com.flchy.blog.plugin.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.flchy.blog.plugin.entity.Mail;

@Configuration
public class MailQueue {
	// 声明一个容量为1000的缓存队列
	static BlockingQueue<Mail> queue = new LinkedBlockingQueue<Mail>(1000);
	@Autowired
	private Consumer consumer;
	@PostConstruct
	public  void start() {
//		Consumer consumer = new Consumer(queue);
		consumer.setQueue(queue);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(consumer);
	}

	public static boolean offer(Mail mail) {
		boolean status = false;
		try {
			if (!queue.offer(mail, 2, TimeUnit.SECONDS)) {
				System.out.println("放入数据失败：" + mail);
			} else {
				status = true;
			}
		} catch (InterruptedException e) {
			System.out.println("放入数据失败：" + mail);
			e.printStackTrace();
		}
		return status;
	}
}
