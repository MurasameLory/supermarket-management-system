package com.main.view;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

public class AlphaScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private boolean borderPaint = false;
	private boolean headerOpaquae = true;
	private boolean viewportBorderPaint = false;	
	
	public AlphaScrollPane() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300,80);
		setBackground(new Color(151,188,229));
		setOpaque(false);
		addPropertyChangeListener(new PropertyChangeAdapter());		
	}
	
	public boolean isBorderPaint() {
		return borderPaint;
	}
	
	public void setBorderPaint(boolean borderPaint) {
		this.borderPaint = borderPaint;
	}
	
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		Component[] components = getComponents();
		for(Component component : components) {
			component.setEnabled(enabled);
		}
		Component view = getViewport().getView();
		if(view!=null) {
			view.setEnabled(enabled);
		}
		if(getColumnHeader()!=null) {
			getColumnHeader().setEnabled(enabled);
		}
	}
	
	public void setHeaderOpaquae(boolean headerOpaquae) {
		this.headerOpaquae = headerOpaquae;
	}
	
	public boolean isViewportBorderPaint() {
		return viewportBorderPaint;
	}
	
	public void setViewportBorderPaint(boolean viewportBorderPaint) {
		this.viewportBorderPaint = viewportBorderPaint;
	}
	
	private final class PropertyChangeAdapter implements PropertyChangeListener, Serializable{
		private static final long serialVersionUID = 2623183751240306776L;

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			String name = e.getPropertyName();
			if(name.equals("ancestor")) {
				JViewport header = getColumnHeader();
				if(header!=null) {
					JComponent view =(JComponent)header.getView();
					if(view instanceof JTable) {
						view.setOpaque(isOpaque());
						header.setOpaque(headerOpaquae);
					}
				}
				getViewport().setOpaque(isOpaque());
				if(!viewportBorderPaint) {
					setViewportBorder(null);
				}
				if(!isBorderPaint()) {
					setBorder(null);
				}
			}			
		}		
	}
	
}
