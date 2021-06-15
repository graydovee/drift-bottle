package cn.graydove.driftbottle.client.frame;
import javax.swing.*;

import cn.graydove.driftbottle.client.service.ClientSetvice;
import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.utils.ResourceUtil;

import java.awt.*;
import java.awt.event.*;
/**
 * 
 * @author Sunshine
 *
 */
public class Page extends JFrame{
	private static final long serialVersionUID = 1L;
	JLayeredPane layeredPane;
	JPanel jp;
	JLabel jl;
	ImageIcon img1=new ImageIcon(ResourceUtil.getResource("img/page.jpg"));
	ImageIcon img2=new ImageIcon(ResourceUtil.getResource("img/pt.png"));
	ImageIcon img3=new ImageIcon(ResourceUtil.getResource("img/find.png"));
	ImageIcon img4=new ImageIcon(ResourceUtil.getResource("img/all.png"));
	public static JButton[] button=new JButton[3];
	
	Throw th;
	Drag da;
	MyBottle mb;
	Loading ld = new Loading();
	UserInfo userInfo;
	
	private User user;
	private DriftBottleService service = new ClientSetvice();
	
	public Page(User arg) {
		this.user = arg;
		userInfo = new UserInfo(user);
		th=new Throw(this.user);
		da=new Drag(this.user);
		mb=new MyBottle(this.user);
		this.setUndecorated(true); 
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setTitle("漂流瓶");
		this.setResizable(false);
		layeredPane=new JLayeredPane();
		jp=new JPanel();
		jp.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
		button[0]=new JButton(img2);
		button[1]=new JButton(img3);
		button[2]=new JButton(img4);
		for(int i=0;i<3;++i) {
			button[i].setBounds(400+i*350,700,200,100);
			layeredPane.add(button[i], JLayeredPane.MODAL_LAYER);
			button[i].setContentAreaFilled(false);
			button[i].setBorderPainted(false);
		}
		button[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				th.setResizable(false);
				th.setVisible(true);
			}
		});
		button[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				ld.setMessage("正在打捞中");
				ld.setVisible(true);
				Bottle bottle = service.pickUp(user);
				if(bottle==null || bottle.getId()==0) {

					ld.setMessage("换个时间再来捞吧");;
				}else {
					if(bottle.getKinds()==0) {
						mb.openPuTong(bottle);
					}else if(bottle.getKinds()==1) {
						mb.openxinqing(bottle);
					}else {
						mb.opentiwen(bottle);
					}
					mb.getFlush();
					mb.myFlush();
				}
				
			}
		});
		button[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){

				mb.getFlush();
				mb.myFlush();
				mb.setResizable(false);
				mb.setVisible(true);
			}
		});
		jl=new JLabel(img1);
		jp.add(jl);
		
	
        JButton back=new JButton("E X I T");
        back.setBounds(img1.getIconWidth()-170, img1.getIconHeight()-80, 200, 80);
        back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setFont(new Font("微软雅黑",Font.BOLD,30));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		});
        jl.add(back);
        
        JButton co=new JButton(new ImageIcon(ResourceUtil.getResource("img/name.png")));
        co.setBounds(img1.getIconWidth()-100, 10, 80, 80);
        co.setContentAreaFilled(false);
		co.setBorderPainted(false);
        co.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				userInfo.setVisible(true);
			}
		});
        
        jl.add(co);
        
        this.setLayeredPane(layeredPane);  
        this.setSize(img1.getIconWidth(),img1.getIconHeight());  
        this.setLocation(110,0);  
        this.setVisible(true);
		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
        
	}
}