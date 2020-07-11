package com.common;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	protected MyObservable observable=new MyObservable();

	public MyObservable getObservable() {
		return observable;
	}
}
