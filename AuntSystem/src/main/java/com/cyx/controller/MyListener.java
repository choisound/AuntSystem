package com.cyx.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cyx.util.MyTimerTask;

/**
 * Listener的方式在后台执行一线程
 * 
 * @author Champion.Wong
 * 
 */
public class MyListener implements ServletContextListener {
	TimerTask tt=new MyTimerTask();
	private Timer timer=new Timer();
	public void contextDestroyed(ServletContextEvent e) {
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent e) {
		System.out.println("线程正在派单。。。");
		timer.schedule(tt, 1, 5000);
	}
}
