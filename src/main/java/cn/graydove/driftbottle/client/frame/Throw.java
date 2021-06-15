package cn.graydove.driftbottle.client.frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import cn.graydove.driftbottle.client.service.ClientSetvice;
import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.User;
import cn.graydove.driftbottle.core.service.DriftBottleService;
import cn.graydove.driftbottle.utils.ResourceUtil;
import cn.graydove.driftbottle.utils.StringUtil;

/**
 * 
 * @author Sunshine
 *
 */

public class Throw extends JFrame{
	private static final long serialVersionUID = 1L;
	ImageIcon img1=new ImageIcon(ResourceUtil.getResource("img/1.png"));
	ImageIcon img2=new ImageIcon(ResourceUtil.getResource("img/2.png"));
	ImageIcon img3=new ImageIcon(ResourceUtil.getResource("img/3.png"));
	JLabel jl1=new JLabel(new ImageIcon(ResourceUtil.getResource("img/11.png")));
	JLabel jl2=new JLabel(new ImageIcon(ResourceUtil.getResource("img/222.png")));
	JLabel jl3=new JLabel(new ImageIcon(ResourceUtil.getResource("img/333.png")));
	JButton[] bottle=new JButton[3];
	Font f;
	Throw t = this;
	PuTong p;
	XinQing xq=new XinQing();
	XinQing2 xq2=new XinQing2();
	TiWen tw=new TiWen();
	JTextArea jta;
	DriftBottleService service;
	
	private User user;
	public Throw(User arg) {
		this.service = new ClientSetvice();
		this.user = arg;
		p = new PuTong(this.user);
		this.setUndecorated(true); 
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setResizable(false);
		setBounds(450,570,400,150);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JPanel jp=new JPanel();
		Container container=getContentPane();
		jp.setLayout(new GridLayout(1,3,10,10));
		bottle[0]=new JButton(img1);
		bottle[1]=new JButton(img2);
		bottle[2]=new JButton(img3);
		for(int i=0;i<3;++i) {
			bottle[i].setContentAreaFilled(false);
			bottle[i].setBorderPainted(false);
			jp.add(bottle[i]);
		}
		container.add(jp);
		bottle[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				setVisible(false);
				p.setVisible(true);
			}
		});
		bottle[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				setVisible(false);
				xq.setVisible(true);
			}
		});
		bottle[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				setVisible(false);
				tw.setVisible(true);
			}
		});
		
		this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				t.setVisible(false);
				
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});
		
	}
	
	private void Get(JPanel jp, JButton close) {
		jp.setLayout(null);
		jp.setBackground(new Color(253,240,215));
		close.setBounds(1125,-25,100,100);
		close.setForeground(new Color(120,81,14));
		close.setFont(new Font("微软雅黑",Font.BOLD,30));
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.setFont(f);
		jp.add(close);
	}
	
	class PuTong extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta;
		private User user;
		private DriftBottleService service;

		public PuTong(User arg) {
			user = arg;
			service = new ClientSetvice();
			this.setUndecorated(true);
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			f=new Font("微软雅黑",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jta=new JTextArea();
			jta.setSize(new Dimension(1000, 500));
			jta.setLocation(100,80);
			jta.setTabSize(4);
			jta.setFont(new Font("宋体", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			jp.setBorder(new TitledBorder(null,"普通瓶：瓶子在海上漂浮，或许会有人捡起并回应您",TitledBorder.CENTER, TitledBorder.TOP, f,new Color(139,113,76)));
			JButton over,go;
			over=new JButton("关闭");
			go=new JButton("扔出去");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("微软雅黑",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			
			go.addActionListener((e)->{
				String msg = jta.getText();
				if(StringUtil.empty(msg)) {
					setVisible(false);
					t.setVisible(true);
					JOptionPane.showMessageDialog(null, "请输入信息", "提示",JOptionPane.PLAIN_MESSAGE);
					return;
				}
				Bottle b = new Bottle();
				b.setKinds(0);
				b.setState(0);
				b.setMessage(msg);
				b.setUid(user.getUid());
				int count = service.throwBottle(b);
				if(count > 0) {
					setVisible(false);
					t.setVisible(true);
					jta.setText("");
					JOptionPane.showMessageDialog(null, "成功扔出", "提示",JOptionPane.PLAIN_MESSAGE);
					this.setVisible(false);
					return;
				}
				setVisible(false);
				t.setVisible(true);
				JOptionPane.showMessageDialog(null, "现在不适合扔漂流瓶呦！", "提示",JOptionPane.PLAIN_MESSAGE);
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
		}
	}
	
	class XinQing extends JDialog{
		private static final long serialVersionUID = 1L;

		public XinQing() {
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel b=new JPanel();
			b.setLayout(null);
			b.setBackground(Color.white);
			JButton[] mood=new JButton[3];
			ImageIcon[] m=new ImageIcon[3];
			m[0]=new ImageIcon(ResourceUtil.getResource("img/happy.jpg"));
			m[1]=new ImageIcon(ResourceUtil.getResource("img/sad.jpg"));
			m[2]=new ImageIcon(ResourceUtil.getResource("img/other.jpg"));
			for(int i=0;i<3;++i) {
				mood[i]=new JButton(m[i]);
				mood[i].setBounds(85+(305)*i,50, 220, 265);
				b.add(mood[i]);
			}
			mood[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					xq2.setKinds(0);
					xq2.setVisible(true);
					jl1.setVisible(true);
					jl2.setVisible(false);
					jl3.setVisible(false);
				}
			});
			
			mood[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					xq2.setKinds(1);
					xq2.setVisible(true);
					jl1.setVisible(false);
					jl2.setVisible(true);
					jl3.setVisible(false);
				}
			});
			
			mood[2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						xq2.setKinds(2);
						xq2.setVisible(true);
						jl1.setVisible(false);
						jl2.setVisible(false);
						jl3.setVisible(true);
					}
				});
			
			jp.add(b);
			b.setSize(1000,365);
			b.setLocation(100, 100);
			JButton close=new JButton("×");
			Font h=new Font("微软雅黑",Font.BOLD,15);
			JPanel j=new JPanel();
			JLabel jl=new JLabel("<html><body>"+"<br>"+"选择一种图案代表您此刻的心情" +"<br>"+"<br>"+"<&nbsp>"+"<&nbsp>"+"写下只言片语分享您的心情"+"<body></html>",Label.LEFT);
			jl.setFont(h);
			jl.setForeground(new Color(139,113,76));
			j.add(jl);
			j.setSize(1000,150);
			j.setLocation(100, 490);
			j.setBackground(new Color(253,240,215));
			j.setBorder(new TitledBorder(null,"说明",TitledBorder.LEADING, TitledBorder.TOP,h,new Color(139,113,76)));
			jp.add(j);
			jp.setBorder(new TitledBorder(null,"心情瓶：用只言片语道出您现在的心情",TitledBorder.CENTER, TitledBorder.TOP, f,new Color(139,113,76)));
			Get(jp,close);
			container.add(jp);
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a){
					setVisible(false);
					t.setVisible(true);
				}
			});
		}
	}
	
	class XinQing2 extends JDialog{
		private static final long serialVersionUID = 1L;
		private int kinds;
		private JTextArea jta;
		public int getKinds() {
			return kinds;
		}
		public void setKinds(int kinds) {
			this.kinds = kinds;
		}

		public XinQing2() {
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			f=new Font("微软雅黑",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jta=new JTextArea();
			jta.setSize(new Dimension(1000, 500));
			jta.setLocation(100,80);
			jta.setTabSize(4);
			jta.setFont(new Font("宋体", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			jp.setBorder(new TitledBorder(null,"心情瓶：用只言片语道出您现在的心情",TitledBorder.CENTER, TitledBorder.TOP, f,new Color(139,113,76)));
			JButton over,go;
			over=new JButton("关闭");
			go=new JButton("扔出去");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("微软雅黑",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.addActionListener((e)->{
				String msg = jta.getText();
				if(StringUtil.empty(msg)) {
					setVisible(false);
					t.setVisible(true);
					JOptionPane.showMessageDialog(null, "请输入信息", "提示",JOptionPane.PLAIN_MESSAGE);
					return;
				}
				Bottle b = new Bottle();
				b.setKinds(1);
				b.setState(0);
				b.setMessage(getKinds()+"|"+msg);
				b.setUid(user.getUid());
				int count = service.throwBottle(b);
				if(count > 0) {
					setVisible(false);
					t.setVisible(true);
					jta.setText("");
					JOptionPane.showMessageDialog(null, "成功扔出", "提示",JOptionPane.PLAIN_MESSAGE);
					this.setVisible(false);
					return;
				}
				setVisible(false);
				t.setVisible(true);
				JOptionPane.showMessageDialog(null, "现在不适合扔漂流瓶呦！", "提示",JOptionPane.PLAIN_MESSAGE);
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			
			jp.add(jl1);
			jl1.setBounds(0, 0, 100, 100);
			jp.add(jl2);
			jl2.setBounds(0, 0, 100, 100);
			jp.add(jl3);
			jl3.setBounds(0, 0, 100, 100);
			
			container.add(jp);
		}
	}
	
	
	class TiWen extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta;
		
		public TiWen() {
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			f=new Font("微软雅黑",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jta=new JTextArea();
			jta.setSize(new Dimension(1000, 500));
			jta.setLocation(100,80);
			jta.setTabSize(4);
			jta.setFont(new Font("宋体", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			jp.setBorder(new TitledBorder(null,"提问瓶：心里有不解的难题？交给万能的漂流瓶吧",TitledBorder.CENTER, TitledBorder.TOP, f,new Color(139,113,76)));
			JButton over,go;
			over=new JButton("关闭");
			go=new JButton("扔出去");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("微软雅黑",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.addActionListener((e)->{
				String msg = jta.getText();
				if(StringUtil.empty(msg)) {
					setVisible(false);
					t.setVisible(true);
					JOptionPane.showMessageDialog(null, "请输入信息", "提示",JOptionPane.PLAIN_MESSAGE);
					return;
				}
				Bottle b = new Bottle();
				b.setKinds(2);
				b.setState(0);
				b.setMessage(msg+"|");
				b.setUid(user.getUid());
				int count = service.throwBottle(b);
				if(count > 0) {
					setVisible(false);
					t.setVisible(true);
					jta.setText("");
					JOptionPane.showMessageDialog(null, "成功扔出", "提示",JOptionPane.PLAIN_MESSAGE);
					this.setVisible(false);
					return;
				}
				setVisible(false);
				t.setVisible(true);
				JOptionPane.showMessageDialog(null, "现在不适合扔漂流瓶呦！", "提示",JOptionPane.PLAIN_MESSAGE);
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
		}
	}
}
