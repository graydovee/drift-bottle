package cn.graydove.driftbottle.client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cn.graydove.driftbottle.client.service.ClientSetvice;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.utils.StringUtil;

/**
 * 
 * @author 施安娴
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	
	private static final int DIALOG_WIDTH=360;
    private static final int DIALOG_HEIGHT=242;
    private static final int DIALOG_HEIGHT_EXTEND=510;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JPasswordField passwordField_2;
    private JTextField textField_year;
    private JTextField textField_month;
    private JTextField textField_day;
	private JComboBox sex;
    
    private User user;
    private DriftBottleService service; 
    private UserInfo t = this;

    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * Create the frame.
	 */
	public UserInfo(User mainUser) {
		setUser(mainUser);
		setResizable(false);
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		setBounds(1280, 50, DIALOG_WIDTH, DIALOG_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		setLocation(WindowXY.getXY(UserInfo.this.getSize()));
		contentPane.setBackground(new Color(253,240,215));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 360, 230);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(253,240,215));
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(33, 45, 48, 18);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(120, 43, 169, 24);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("性别");
		lblNewLabel_1.setBounds(33, 94, 35, 18);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1);
		
		sex = new JComboBox();
		sex.setBounds(120, 92, 66, 24);
		sex.addItem("男");
		sex.addItem("女");
		panel_1.add(sex);
		sex.setBackground(new Color(236,196,145));
		
		
		JLabel label = new JLabel("出生日期");
		label.setBounds(33, 140, 75, 18);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_1.add(label);
		
		
		JButton revisepwd = new JButton("修改密码");
		revisepwd.setBounds(12, 185, 113, 27);
		revisepwd.setFont(new Font("宋体", Font.BOLD, 16));
		revisepwd.setForeground(new Color(51, 153, 255));
		panel_1.add(revisepwd);
		revisepwd.setContentAreaFilled(false);
		revisepwd.setBorderPainted(false);
		revisepwd.setRolloverEnabled(true); 
		revisepwd.setFocusPainted(false);
		revisepwd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(UserInfo.this.getHeight()==DIALOG_HEIGHT_EXTEND){
                    UserInfo.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
                }
                else{
                    UserInfo.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
                }
            }
        });
		
		JButton close = new JButton("关闭");
		close.setFont(new Font("宋体", Font.BOLD, 15));
		close.setBounds(247, 180, 73, 32);
		close.setForeground(new Color(253, 240, 215));
		close.setFocusPainted(false);
		close.setBackground(new Color(236, 196, 145));
		panel_1.add(close);
		
		textField_year = new JTextField();
		textField_year.setColumns(10);
		textField_year.setBounds(120, 138, 37, 25);
		panel_1.add(textField_year);
		
		JLabel label_4 = new JLabel("年");
		label_4.setBounds(160, 138, 20, 25);
		panel_1.add(label_4);
		
		textField_month = new JTextField();
		textField_month.setColumns(10);
		textField_month.setBounds(180, 138, 26, 25);
		panel_1.add(textField_month);
		
		JLabel label_5 = new JLabel("月");
		label_5.setBounds(208, 138, 20, 25);
		panel_1.add(label_5);
		
		textField_day = new JTextField();
		textField_day.setColumns(10);
		textField_day.setBounds(229, 138, 26, 25);
		panel_1.add(textField_day);
		
		JLabel label_6 = new JLabel("日");
		label_6.setBounds(258, 138, 20, 25);
		panel_1.add(label_6);
		
		JButton confirm = new JButton("确认");
		confirm.setFont(new Font("宋体", Font.BOLD, 15));
		confirm.setForeground(new Color(253, 240, 215));
		confirm.setFocusPainted(false);
		confirm.setBackground(new Color(236, 196, 145));
		confirm.setBounds(170, 180, 73, 32);
		panel_1.add(confirm);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(System.getProperty("user.dir")+"/img/shell.png"));
		lblNewLabel_2.setBounds(258, 62, 94, 63);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(System.getProperty("user.dir")+"/img/star.png"));
		lblNewLabel_3.setBounds(61, 0, 81, 69);
		panel_1.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,"修改密码",TitledBorder.LEADING, TitledBorder.TOP, null,null));
		panel.setBounds(12, 239, 336, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(253,240,215));
		
		
		JLabel label_1 = new JLabel("当前密码");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(14, 51, 75, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("新密码");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(14, 95, 75, 18);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("确认密码");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(14, 140, 75, 18);
		panel.add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(108, 49, 169, 24);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(108, 93, 169, 24);
		panel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(108, 138, 169, 24);
		panel.add(passwordField_2);
		
		JButton ConfirmRevision = new JButton("确认修改");
		ConfirmRevision.setFont(new Font("宋体", Font.BOLD, 15));
		ConfirmRevision.setBounds(219, 200, 100, 32);
		panel.add(ConfirmRevision);
		ConfirmRevision.setBackground(new Color(236,196,145));
		ConfirmRevision.setFocusPainted(false);
		ConfirmRevision.setForeground(new java.awt.Color(253,240,215));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(System.getProperty("user.dir")+"/img/fish.png"));
		lblNewLabel_4.setBounds(35, 157, 134, 100);
		panel.add(lblNewLabel_4);
	
		flush();
		//增加监听事件
		service = new ClientSetvice();
		this.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				t.setVisible(false);
				
			}
			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});
		//关闭按钮
		close.addActionListener((e)->{
			this.setVisible(false);
		});
		
		//修改密码按钮
		ConfirmRevision.addActionListener((e)->{
			String oldPwd = new String(passwordField.getPassword());
			String newPwd = new String(passwordField_1.getPassword());
			String cfmPwd = new String(passwordField_2.getPassword());
			if(StringUtil.empty(oldPwd)) {
				JOptionPane.showMessageDialog(null, "当前密码不能为空", "提示",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if(StringUtil.empty(newPwd)) {
				JOptionPane.showMessageDialog(null, "新密码不能为空", "提示",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if(StringUtil.empty(cfmPwd)) {
				JOptionPane.showMessageDialog(null, "确认密码不能为空", "提示",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			if(!cfmPwd.equals(newPwd)) {
				JOptionPane.showMessageDialog(null, "两次输入的密码不匹配", "提示",JOptionPane.PLAIN_MESSAGE);
			}
			
			User u = new User();
			u.setUname(user.getUname());
			u.setPwd(oldPwd);
			u = service.login(u);
			if(u==null || u.getUid()!=user.getUid()) {
				JOptionPane.showMessageDialog(null, "密码不正确", "提示",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			u.setPwd(newPwd);
			int count = service.updPwd(user);
			if(count > 0) {
				JOptionPane.showMessageDialog(null, "修改成功", "提示",JOptionPane.PLAIN_MESSAGE);
				user = u;
				flush();
			}else {
				JOptionPane.showMessageDialog(null, "修改失败", "提示",JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		//修改信息按钮
		confirm.addActionListener((e)->{
			User temp = new User();
			temp.setUid(user.getUid());
			temp.setPwd(user.getPwd());
			
			String uname = textField.getText();
			temp.setUname(uname);
			
			int se = sex.getSelectedIndex();
			temp.setSex(se);
			
			String year = textField_year.getText();
			String month = textField_month.getText();
			String day = textField_day.getText();
			if(!StringUtil.empty(year) && !StringUtil.empty(month) && !StringUtil.empty(day)) {
				String date = year+"-"+month+"-"+day;
				try {
					temp.setBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
					System.out.println(temp.getBirth());
				} catch (ParseException e1) {
					temp.setBirth(null);
				}
			}
			
			int count = service.updUser(temp);
			if(count > 0) {
				JOptionPane.showMessageDialog(null, "修改成功", "提示",JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "修改失败", "提示",JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
	
	private void flush() {
		
		textField.setText(user.getUname());
		
		if(user.getSex()==0) {
			sex.setSelectedIndex(0);
		}else if(user.getSex()==1) {
			sex.setSelectedIndex(1);
		}
		java.sql.Date date = user.getBirth();
		if(date!=null) {
			String dateStr = date.toString();
			String[] dateMsg = dateStr.split("-");
			if(dateMsg.length==3) {
				textField_year.setText(dateMsg[0]);
				textField_month.setText(dateMsg[1]);
				textField_day.setText(dateMsg[2]);
			}
		}
	}
	
}
