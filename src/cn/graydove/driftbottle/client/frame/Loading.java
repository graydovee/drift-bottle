package cn.graydove.driftbottle.client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class Loading extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblNewLabel;
	
	public void setMessage(String message) {
		this.lblNewLabel.setText(message);
	}

	/**
	 * Create the frame.
	 */
	public Loading() {
		
		this.setUndecorated(true); // È¥µô´°¿ÚµÄ×°ÊÎ
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(100, 100, 403, 76);
		setLocation(WindowXY.getXY(Loading.this.getSize()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(167,238,255));
		
		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 22));
		lblNewLabel.setBounds(119, 17, 273, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(System.getProperty("user.dir")+"/img/dolphin.png"));
		lblNewLabel_1.setBounds(24, 0, 91, 70);
		contentPane.add(lblNewLabel_1);
		
		this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				setVisible(false);
				
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});
	}
}
