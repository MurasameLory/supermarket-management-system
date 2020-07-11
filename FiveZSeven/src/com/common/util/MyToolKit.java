package com.common.util;

import javax.swing.JOptionPane;

import com.Client;
import com.user.view.LoginFrame;

public class MyToolKit {
	public static final String regexp_telephone= "^(((\\+\\d{2}-)?0\\d{2,3}-\\d{7,8})|((\\+\\d{2}-)?(\\d{2,3}-)?([1][3,4,5,7,8][0-9]\\d{8})))$";
	public static final String regexp_email="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	public static void login() {
		JOptionPane.showMessageDialog(null, "会话超时，请重新登录！",
				"信息提示框", JOptionPane.INFORMATION_MESSAGE);
		if(Client.mainFrame!=null) {
			Client.mainFrame.setVisible(false);
		}
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
	
	public static boolean isTelephone(String tel){
		return tel.matches(regexp_telephone);
	}
	
	public static boolean isMail(String email){
		return email.matches(regexp_email);
	}
	
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showWarning(String message) {
		JOptionPane.showMessageDialog(null, message, "警告", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showError(Exception ex) {
		JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "错误", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int showConfirm(String message) {
		return JOptionPane.showConfirmDialog(null, message, "确认", JOptionPane.YES_NO_OPTION);
	}
	
}