package com.flchy.blog.plugin.blockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flchy.blog.plugin.entity.Mail;
import com.flchy.blog.plugin.mail.MailUtil;

/**
 * 消费者
 * 
 * @author 1st
 *
 */
@Component
public class Consumer implements Runnable {
	private  BlockingQueue<Mail> queue;
	private static final int DEFAULT_RANGE_FOR_SLEEP = 3000;
	@Autowired
	private MailUtil mailUtil;

/*	public Consumer(BlockingQueue<Mail> queue) {
		this.queue = queue;
	}*/
	public  void setQueue(BlockingQueue<Mail> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Random r = new Random();
		boolean isRunning = true;
		try {
			while (isRunning) {
				System.out.println("正从队列获取数据...");
				Mail data = queue.poll(2, TimeUnit.SECONDS);
				if (null != data) {
					System.out.println("拿到数据：" + data);
					System.out.println("开始消费：");
					try {
						mailUtil.send(data);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("退出消费者线程！");
		}
	}

}
