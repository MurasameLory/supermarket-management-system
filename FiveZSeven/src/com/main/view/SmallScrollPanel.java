package com.main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class SmallScrollPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private AlphaScrollPane alphaScrollPane;
	private JButton leftScrollButton = null;
	private JButton rightScrollButton = null;
	private ScrollMouseAdapter scrollMouseAdapter;
	
	public SmallScrollPanel() {
		scrollMouseAdapter = new ScrollMouseAdapter();
		initialize();
	}

	private void initialize() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(0);
		this.setLayout(borderLayout);		
		this.setSize(new Dimension(300,80));
		this.setOpaque(false);
		
		this.add(getAlphaScrollPanel(), BorderLayout.CENTER);
		this.add(getLeftScrollButton(),BorderLayout.WEST);
		this.add(getRightScrollButton(),BorderLayout.EAST);
		
	}

	private JButton getLeftScrollButton() {
		if(leftScrollButton==null) {
			leftScrollButton = new JButton();
			ImageIcon icon1= new ImageIcon(getClass().getResource("/com/icon/left_off.png"));
			ImageIcon icon2= new ImageIcon(getClass().getResource("/com/icon/left_on.png"));
			leftScrollButton.setOpaque(false);
			leftScrollButton.setBorder(createEmptyBorder(0,10,0,0));
			leftScrollButton.setIcon(icon1);
			leftScrollButton.setPressedIcon(icon2);
			leftScrollButton.setRolloverIcon(icon2);
			leftScrollButton.setContentAreaFilled(false);
			leftScrollButton.setPreferredSize(new Dimension(28,80));
			leftScrollButton.setFocusable(false);
			leftScrollButton.addMouseListener(scrollMouseAdapter);
		}
		return leftScrollButton;
	}
	

	private Border createEmptyBorder(int i, int j, int k, int l) {
		return new EmptyBorder(i, j, k, l);
	}

	private JButton getRightScrollButton() {
		if(rightScrollButton==null) {
			rightScrollButton = new JButton();
			ImageIcon icon1= new ImageIcon(getClass().getResource("/com/icon/right_off.png"));
			ImageIcon icon2= new ImageIcon(getClass().getResource("/com/icon/right_on.png"));
			rightScrollButton.setOpaque(false);
			rightScrollButton.setBorder(createEmptyBorder(0,0,0,10));
			rightScrollButton.setIcon(icon1);
			rightScrollButton.setPressedIcon(icon2);
			rightScrollButton.setRolloverIcon(icon2);
			rightScrollButton.setContentAreaFilled(false);
			rightScrollButton.setPreferredSize(new Dimension(28,80));
			rightScrollButton.setFocusable(false);
			rightScrollButton.addMouseListener(scrollMouseAdapter);
		}
		return rightScrollButton;
	}

	public AlphaScrollPane getAlphaScrollPanel() {
		if(alphaScrollPane==null) {
			alphaScrollPane = new AlphaScrollPane();
			alphaScrollPane.setPreferredSize(new Dimension(100,80));
			alphaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			alphaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			alphaScrollPane.setBorderPaint(false);
			alphaScrollPane.addComponentListener(new ScrollButtonShowListener());
		}
		return alphaScrollPane;
	}
	
	private class ScrollButtonShowListener extends ComponentAdapter implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public void componentResized(ComponentEvent e) {
			JScrollBar scrollBar = alphaScrollPane.getHorizontalScrollBar();
			int scrollWidth = scrollBar.getMaximum();
			int paneWidth = alphaScrollPane.getWidth();
			if(paneWidth >= scrollWidth) {
				getLeftScrollButton().setVisible(false);
				getRightScrollButton().setVisible(false);
			} else {
				getLeftScrollButton().setVisible(true);
				getRightScrollButton().setVisible(true);
			}			
		}

	}
	
	private final class ScrollMouseAdapter extends MouseAdapter implements Serializable {
		private static final long serialVersionUID = -783858617505330639L;
		JScrollBar scrollBar = getAlphaScrollPanel().getHorizontalScrollBar();
		private boolean isPressed = true;
		public void mousePressed(MouseEvent e) {
			Object source = e.getSource();
			isPressed = true;			
			if(source==getLeftScrollButton()) {
				scrollMoved(-1);
			}else {
				scrollMoved(1);
			}
		}
		
		private void scrollMoved(final int orientation) {
			new Thread() {
				private int oldValue = scrollBar.getValue();
				public void run() {
					while(isPressed) {
						try {
							Thread.sleep(10);
						}catch(InterruptedException e1) {
							e1.printStackTrace();
						}
						oldValue = scrollBar.getValue();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								scrollBar.setValue(oldValue+3*orientation);
							}
						});
					}
				}
			}.start();
		}
		
		public void mouseExited(MouseEvent e) {
			isPressed = false;
		}
		
		public void mouseReleased(MouseEvent e) {
			isPressed = false;
		}		
	}
	
}
