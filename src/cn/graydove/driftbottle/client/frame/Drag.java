package cn.graydove.driftbottle.client.frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import cn.graydove.driftbottle.core.bean.User;
/**
 * 
 * @author Sunshine
 *
 */
class Drag extends JFrame{
	private static final long serialVersionUID = 1L;
	MyBottle b;
	User user;
	public Drag(User user) {
		b = new MyBottle(user);
		this.setUndecorated(true); 
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setSize(1200,700);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		Container container=getContentPane();
		JButton close=new JButton("CLOSE");
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		JPanel jp=new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(253,240,215));
		close.setBounds(1100, -20, 100, 100);
		Font f=new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20);
		close.setFont(f);
		jp.add(close);
		container.add(jp);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				setVisible(false);
			}
		});
	}
}