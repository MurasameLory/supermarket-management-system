package com.common.util;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;

import com.Server;
import com.user.bean.User;

public class TokenManager {
	public static TokenManager instance = new TokenManager();
	private static Hashtable<User,String> user2token = new Hashtable<User,String>();
	private static Hashtable<String,User> token2user = new Hashtable<String,User>();
	private static Hashtable<String, Long> token2time = new Hashtable<String, Long>();
	private static int availablePeriod = 60;
	
	private TokenManager() {
		start();
	}
	
	public static void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Properties prop = new Properties();
				try {
					InputStream is = Class.class.getResourceAsStream("/com/server.properties");
					prop.load(is);
					String tokenTimeOut =  prop.getProperty("tokenTimeOut");
					availablePeriod = Integer.parseInt(tokenTimeOut);							
				}catch(Exception e) {
					e.printStackTrace();
				}
				Iterator<User> keys = null;
				while(true) {
					long sleepTime = availablePeriod/2;
					if(sleepTime<1) {
						sleepTime = 6 * 1000;
					}
					try {
						Thread.sleep(sleepTime);
						instance.checkToken();
						keys = user2token.keySet().iterator();
						long now = System.currentTimeMillis();
						int i=1;
						long last=0;
						while(Server.model.getRowCount()!=0) {
							Server.model.removeRow(0);
						}
						while(keys.hasNext()) {
							User key = keys.next();
							String value = user2token.get(key);
							last = (now -token2time.get(value))/1000;
							Server.model.addRow(new Object[] {i+"",key.getUserName(),value,last+"",(availablePeriod-last)+""});
							i++;
						}
						Server.lblNotice.setText("当前在线人数："+(i-1)+";当前服务器内存占用："
								+Runtime.getRuntime().totalMemory()/(8*1024*1024)+"MB");
					} catch(Throwable e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public synchronized String getToken(User user) {
		String token = user2token.get(user);
		if(token==null) {
			token = generateToken(user);
			putToken(user,token);
		}
		return token;
	}
	
	public synchronized boolean verify(String token) {
		return token2user.get(token) != null;
	}
	
	public User getUser(String token) {
		return token2user.get(token);
	}
	
	public static void setAvailablePeriod(int period) {
		availablePeriod = period;
	}
	
	private synchronized void putToken(User user, String token) {
		user2token.put(user, token);
		token2time.put(token, System.currentTimeMillis());
		token2user.put(token, user);
	}
	
	private synchronized void checkToken() {
		long crt = System.currentTimeMillis();
		Iterator<String> iterator = token2time.keySet().iterator();
		String token="";
		Long time = new Long(0);
		while(iterator.hasNext()) {
			token = iterator.next();
			time = token2time.get(token);
			if((crt-time)>availablePeriod*1000) {
				token2time.remove(token);
				user2token.remove(token2user.get(token));
				token2user.remove(token);
			}
		}
	}
	
	private String generateToken(User user) {
		return UUID.randomUUID().toString().substring(0,16);
	}

}
