package cn.graydove.driftbottle.client.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cn.graydove.driftbottle.client.service.ClientSetvice;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.utils.StringUtil;

/**
 * 
 * @author ʩ���
 *
 */
public class LoginDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	
	private static final int DIALOG_WIDTH=360;
    private static final int DIALOG_HEIGHT=295;
    private static final int DIALOG_HEIGHT_EXTEND=540;
    private JTextField textField_1;
    private JPasswordField passwordField_1;
    private JPasswordField passwordField_2;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    private User user;
    private DriftBottleService service;
    private Page main;

	public User getUeser() {
		return user;
	}

	public void setUeser(User user) {
		this.user = user;
	}

	public static void load() {
		LoginDialog frame = new LoginDialog();
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public LoginDialog() {
		service = new ClientSetvice();
		
		setResizable(false);
		setTitle("�û���¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 360,295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		setLocation(WindowXY.getXY(LoginDialog.this.getSize()));
		
		JLabel lblNewLabel = new JLabel("�˺�");
		lblNewLabel.setBounds(54, 149, 54, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setBounds(54, 187, 54, 25);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(133, 147, 150, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 182, 150, 25);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 360, 136);
		ImageIcon icon=new ImageIcon(System.getProperty("user.dir")+"/img/loginbg.jpg");
        icon=ImageScale.getImage(icon, label.getWidth(), label.getHeight());
        label.setIcon((icon));
        contentPane.add(label);

		
		JButton btnNewButton = new JButton("ע��");
        btnNewButton.setBounds(53, 224, 93, 23);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	expend();
            }
        });


		JButton btnNewButton_1 = new JButton("��¼");
		btnNewButton_1.setBounds(190, 224, 93, 23);
		contentPane.add(btnNewButton_1);
		
		
		JPanel panel = new JPanel();
	    panel.setBorder(new TitledBorder(null, "ע���û�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel.setBounds(12, 259, 336, 239);
	    contentPane.add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblNewLabel_2 = new JLabel("�û���");
	    lblNewLabel_2.setBounds(41,30, 55, 18);
	    panel.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_4 = new JLabel("����");
	    lblNewLabel_4.setBounds(41, 65, 55, 18);
	    panel.add(lblNewLabel_4);
	    
	    JLabel lblNewLabel_5 = new JLabel("ȷ������");
	    lblNewLabel_5.setBounds(41, 99, 55, 18);
	    panel.add(lblNewLabel_5);
	    
	    JLabel lblNewLabel_3 = new JLabel("��������");
	    lblNewLabel_3.setBounds(41, 134, 55, 18);
	    panel.add(lblNewLabel_3);
	    
	    
	    textField_1 = new JTextField();
	    textField_1.setBounds(123, 25, 150, 25);
	    panel.add(textField_1);
	    textField_1.setColumns(10);
	    
	    JButton btnNewButton_3 = new JButton("ȡ��");
	    btnNewButton_3.addActionListener((e)->{
	    	expend();
	    });
	    btnNewButton_3.setBounds(51, 199, 83, 27);
	    panel.add(btnNewButton_3);
	    
	    JButton btnNewButton_4 = new JButton("ȷ��");
	    btnNewButton_4.setBounds(185, 199, 83, 27);
	    panel.add(btnNewButton_4);
	    
	    passwordField_1 = new JPasswordField();
	    passwordField_1.setBounds(123, 95, 150, 25);
	    panel.add(passwordField_1);
	    
	    passwordField_2 = new JPasswordField();
	    passwordField_2.setBounds(123, 60, 150, 25);
	    panel.add(passwordField_2);
	    
	    textField_2 = new JTextField();
	    textField_2.setBounds(123, 130, 37, 25);
	    panel.add(textField_2);
	    textField_2.setColumns(10);
	    
	    JLabel label_1 = new JLabel("��");
	    label_1.setBounds(163, 130, 20, 25);
	    panel.add(label_1);
	    
	    textField_3 = new JTextField();
	    textField_3.setColumns(10);
	    textField_3.setBounds(182, 130, 26, 25);
	    panel.add(textField_3);
	    
	    JLabel label_2 = new JLabel("��");
	    label_2.setBounds(210, 130, 20, 25);
	    panel.add(label_2);
	    
	    textField_4 = new JTextField();
	    textField_4.setColumns(10);
	    textField_4.setBounds(231, 130, 26, 25);
	    panel.add(textField_4);
	    
	    JLabel label_3 = new JLabel("��");
	    label_3.setBounds(260, 130, 20, 25);
	    panel.add(label_3);
	    
	    JLabel lblNewLabel_6 = new JLabel("�Ա�");
	    lblNewLabel_6.setBounds(41, 168, 55, 18);
	    panel.add(lblNewLabel_6);
	    
	    ButtonGroup group = new ButtonGroup();
	    JRadioButton jrb1 = new JRadioButton("��");
	    jrb1.setBounds(123, 164, 54, 27);
	    JRadioButton jrb2 = new JRadioButton("Ů");
	    jrb2.setBounds(219, 164, 54, 27);
	    panel.add(jrb1);
	    panel.add(jrb2);
	    group.add(jrb1);
        group.add(jrb2);

        //���Ӽ����¼�
        //ע�����
        btnNewButton_4.addActionListener((e)->{
        	
        	String uname = textField_1.getText();
        	if(StringUtil.empty(uname)) {
        		JOptionPane.showMessageDialog(null, "�û�������Ϊ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
        		return;
        	}
        	
        	String pwd = new String(passwordField_2.getPassword());
        	if(StringUtil.empty(pwd)) {
        		JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
        		return;
        	}
        	
        	String cfm = new String(passwordField_1.getPassword());
        	if(!cfm.equals(pwd)) {
        		JOptionPane.showMessageDialog(null, "�������벻һ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
            	return;
        	}

        	String year = textField_2.getText();
        	String month = textField_3.getText();
        	String day = textField_4.getText();
        	java.sql.Date date = null;
        	if(!StringUtil.empty(year) && !StringUtil.empty(month) && !StringUtil.empty(day)) {
        		try {
    				date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-"+day).getTime());
    			} catch (ParseException e1) {
    				JOptionPane.showMessageDialog(null, "���ڸ�ʽ����ȷ", "��ʾ",JOptionPane.PLAIN_MESSAGE);
    				return;
    			}
        	}
        	
        	int sex = -1;
        	if(jrb1.isSelected()) {
        		sex = 0;
        	}else if(jrb2.isSelected()) {
        		sex = 1;
        	}else {
        		JOptionPane.showMessageDialog(null, "��ѡ���Ա�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
        		return;
        	}
        	
        	
        	
        	
        	User u = new User();
        	u.setUname(uname);
        	u.setPwd(pwd);
        	u.setSex(sex);
			u.setBirth(date);
        	int count = service.register(u);
        	if(count>0) {
        		JOptionPane.showMessageDialog(null, "ע��ɹ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
    			expend();
        	}else {
        		JOptionPane.showMessageDialog(null, "ע��ʧ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
        	}
        });
        
        //��¼����
        btnNewButton_1.addActionListener((e)->{
        	String uname = textField.getText();
        	String pwd = new String(passwordField.getPassword());
        	if(!StringUtil.empty(uname) && !StringUtil.empty(pwd)) {
        		User u = new User();
        		u.setUname(uname);
        		u.setPwd(pwd);
        		user = service.login(u);
        		if(user!=null) {
        			main = new Page(user);
        			this.setVisible(false);
        			main.setVisible(true);
        		}else {
        			JOptionPane.showMessageDialog(null, "��¼ʧ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
        		}
        	}else {
        		JOptionPane.showMessageDialog(null, "�û������벻��Ϊ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
        	}
        });
        
        
	}
	
	private void expend() {
		 if(LoginDialog.this.getHeight()==DIALOG_HEIGHT_EXTEND){
             LoginDialog.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
         }
         else{
             LoginDialog.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
         }
	}
}
