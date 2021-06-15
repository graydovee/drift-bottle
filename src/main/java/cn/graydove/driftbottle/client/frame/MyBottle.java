package cn.graydove.driftbottle.client.frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
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
public class MyBottle extends JFrame{
	private static final long serialVersionUID = 1L;

	JButton over1;
	JButton left,num,right;
	JLabel[] jl=new JLabel[1000];
	JButton[] o=new JButton[1000];
	JLabel[] jll=new JLabel[1000];
	JButton[] oo=new JButton[1000];
	String[] countt= {"��ͨƿ","����ƿ","����ƿ","����ƿ","��ͨƿ"};
	
	Font f=new Font("΢���ź�",Font.BOLD,20);
	JPanel jp=new JPanel();
	MyBottle t=this;
	JTextArea jta,ja;
	putong q=new putong();
	xinqing w=new xinqing();
	tiwen e=new tiwen();
	putong2 qq=new putong2();
	xinqing2 ww=new xinqing2();
	tiwen2 ee=new tiwen2();
	JLabel pict;
	JPanel k,kk;
	
	int getPage = 1;
	int myPage = 1;
	int getSize = 0;
	int mySize = 0;
	
	int flag;
	
	ActionListener[] oa = new ActionListener[5];
	ActionListener[] oaa = new ActionListener[5];
	
	DriftBottleService service = new ClientSetvice();
	User user;
	
	public void openPuTong(Bottle bottle) {
		q.open(bottle);
	}
	public void openxinqing(Bottle bottle) {
		w.open(bottle);
	}
	public void opentiwen(Bottle bottle) {
		e.open(bottle);
	}
	private String count(int x) {
		if(x==0) {
			return "��ͨƿ";
		}else if(x==1) {
			return "����ƿ";
		}else {
			return "����ƿ";
		}
	}
	
	public void getFlush() {
		List<Bottle> list = service.showCollections(user);
		if(list!=null) {
			getSize = list.size();
		}else {
			getSize=0;
		}
		for(int i=0;i<5;++i) {
			int index = (getPage-1)*5 + i;
			if(list!=null && index<list.size()) {
				o[i].setText("����");
				o[i].setContentAreaFilled(false);
				o[i].setBorderPainted(false);
				o[i].setFont(new Font("΢���ź�",Font.BOLD,20));
				o[i].setForeground(new Color(120,81,14));
				o[i].setBounds(980, -25, 150, 150);
				jl[i].setFont(new Font("΢���ź�",Font.BOLD,20));
				jl[i].setText("        �յ���"+count(list.get(index).getKinds())+"   ("+list.get(index).getUpdateTime().toString()+")");
				jl[i].setBorder(BorderFactory.createLineBorder(new Color(120,81,14)));
				jl[i].setBounds(0,100*i,1100,100);
			}else {
				o[i].setText("");
				jl[i].setText("");
			}
		}

		for(int i=0;i<5;++i) {
			int index = (getPage-1)*5 +i;
			int ii = i;
			if(list!=null && index<list.size()) {
				if(oa[i]!=null) {
					o[i].removeActionListener(oa[i]);
				}
				o[i].addActionListener(oa[i]=new ActionListener() {
					public void actionPerformed(ActionEvent a){
						t.setVisible(false);
						int in = (getPage-1)*5 +ii;
						if(in<list.size()) {
							if(list.get(in).getKinds()==0) {
								q.open(list.get(in));
							}else if(list.get(in).getKinds()==1) {
								w.open(list.get(in));
							}else {
								e.open(list.get(in));
							}
						}
					}
				});
			}else {
				o[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a){
						
					}
				});
			}
		}
	}
	
	public void myFlush() {
		List<Bottle> list = service.showMyThrow(user);
		if(list!=null) {
			mySize = list.size();
		}
		for(int i=0;i<5;++i) {
			int index = (myPage-1)*5+i;
			if(list!=null && index<list.size()) {
				oo[i].setText("����");
				oo[i].setContentAreaFilled(false);
				oo[i].setBorderPainted(false);
				oo[i].setFont(new Font("΢���ź�",Font.BOLD,20));
				oo[i].setForeground(new Color(120,81,14));
				oo[i].setBounds(980, -25, 150, 150);
				jll[i].setFont(new Font("΢���ź�",Font.BOLD,20));
				jll[i].setText("        "+list.get(index).getCreateTime().toString()+"   ("+count(list.get(index).getKinds())+")");
				jll[i].setBorder(BorderFactory.createLineBorder(new Color(120,81,14)));
				jll[i].setBounds(0,100*i,1100,100);
			}else {
				oo[i].setText("");
				jll[i].setText("");
			}
		}
		
		
		for(int i=0;i<5;++i) {
			int index = (myPage-1)*5 + i;
			int ii = i;
			if(list!=null && index<list.size()) {
				if(oaa[i]!=null) {
					oo[i].removeActionListener(oaa[i]);
				}
				oo[i].addActionListener(oaa[i] = new ActionListener() {
					public void actionPerformed(ActionEvent a){
						t.setVisible(false);
						int in = (myPage-1)*5 + ii;
						if(list.get(in).getKinds()==0) {
							qq.open(list.get(in));
						}else if(list.get(in).getKinds()==1) {
							ww.open(list.get(in));
						}else {
							ee.open(list.get(in));
						}
					}
				});
			}else {
				if(oaa[i]!=null) {
					oo[i].removeActionListener(oaa[i]);
				}
			}
			
		}
	}
	
	public MyBottle(User arg) {
		this.user = arg;
		this.setUndecorated(true); 
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setSize(1200,700);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		Container container=getContentPane();
		over1=new JButton("��");
		over1.setContentAreaFilled(false);
		over1.setBorderPainted(false);
		over1.setFont(new Font("΢���ź�",Font.BOLD,30));
		over1.setForeground(new Color(120,81,14));
		over1.setBounds(1125,-25,100,100);
		
		
		left=new JButton("< ");
		left.setContentAreaFilled(false);
		left.setBorderPainted(false);
		left.setFont(new Font("΢���ź�",Font.BOLD,30));
		left.setForeground(new Color(120,81,14));
		left.setBounds(1025,625,100,100);
		jp.add(left);
		
		right=new JButton(" >");
		right.setContentAreaFilled(false);
		right.setBorderPainted(false);
		right.setFont(new Font("΢���ź�",Font.BOLD,30));
		right.setForeground(new Color(120,81,14));
		right.setBounds(1075,625,100,100);
		jp.add(right);
		
		num=new JButton("1");
		num.setContentAreaFilled(false);
		num.setBorderPainted(false);
		num.setFont(new Font("΢���ź�",Font.BOLD,25));
		num.setForeground(new Color(120,81,14));
		num.setBounds(1050,625,100,100);
		jp.add(num);
		
		
		jp.setLayout(null);
		jp.setBackground(new Color(253,240,215));
		jp.add(over1);
		container.add(jp);
		over1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				setVisible(false);
			}
		});
		JButton GetBottle=new JButton("�̵���ƿ��");
		JButton SetBottle=new JButton("�ӳ���ƿ��");
		GetBottle.setVisible(false);
		ButtonGet(GetBottle,400);
		ButtonGet(SetBottle,600);
		JLabel GetBottle2=new JLabel("�̵���ƿ��",JLabel.CENTER);
		JLabel SetBottle2=new JLabel("�ӳ���ƿ��",JLabel.CENTER);
		ButtonGets(GetBottle2,400);
		ButtonGets(SetBottle2,600);
		k=new JPanel();
		kk=new JPanel();
		k.setBounds(50, 150, 1100, 500);
		k.setBackground(Color.white);
		k.setLayout(null);
		
		
		kk.setBounds(50, 150, 1100, 500);
		kk.setBackground(Color.white);
		kk.setLayout(null);
		
		for(int i=0;i<5;++i) {
			o[i]=new JButton();
			jl[i]=new JLabel();
			oo[i]=new JButton();
			jll[i]=new JLabel();
			
			jll[i].add(oo[i]);
			kk.add(jll[i]);

			jl[i].add(o[i]);
			k.add(jl[i]);
		}
		
		jp.add(k);
		jp.add(kk);
		GetBottle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				GetBottle.setVisible(false);
				SetBottle.setVisible(true);
				k.setVisible(true);
				kk.setVisible(false);
				flag = 0;
				num.setText(""+getPage);
			}
		});
		SetBottle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a){
				SetBottle.setVisible(false);
				GetBottle.setVisible(true);
				kk.setVisible(true);
				k.setVisible(false);
				flag = 1;
				num.setText(""+myPage);
			}
		});
		
		right.addActionListener((e)->{
			if(flag==0) {
				int all=getSize%5==0?getSize/5:getSize/5+1;
				if(getPage<all) {
					getPage+=1;
					num.setText(""+getPage);
					getFlush();
				}
			}else {
				int all=mySize%5==0?mySize/5:mySize/5+1;
				if(myPage<all) {
					myPage+=1;
					num.setText(""+myPage);
					myFlush();
				}
			}
		});
		
		left.addActionListener((e)->{
			if(flag==0) {
				if(getPage>1) {
					getPage-=1;
					num.setText(""+getPage);
					getFlush();
				}
			}else {
				if(myPage>1) {
					myPage-=1;
					num.setText(""+myPage);
					myFlush();
				}
			}
		});
	}
	private void ButtonGet(JButton button,int x) {
		button.setBounds(x, 50, 200, 70);
		button.setFont(new Font("΢���ź�",Font.BOLD,25));
		button.setBackground(new Color(253,229,178));
		button.setForeground(new Color(120,81,14));
		jp.add(button);
	}
	private void ButtonGets(JLabel jl,int x) {
		jl.setBounds(x, 50, 200, 70);
		jl.setBackground(new Color(120,81,14));
		jl.setOpaque(true);
		jl.setFont(new Font("΢���ź�",Font.BOLD,25));
		jl.setForeground(new Color(253,229,178));
		jl.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jp.add(jl);
	}
	class putong extends JDialog{
		private static final long serialVersionUID = 1L;
		JTextArea jta,ja;
		private Bottle bottle;
		
		public void open(Bottle bottle) {
			this.bottle = bottle;
			String[] messages = bottle.getMessage().split("\\|");
			if(messages!=null) {
				jta.setText(messages[0]);
			}else {
				jta.setText("");
			}
			if(messages.length>1) {
				ja.setText(messages[1]);
			}else {
				ja.setText("");
			}
			jta.setEditable(false);
			this.setVisible(true);
		}

		public putong(){
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel jp2=new JPanel();
			
			f=new Font("΢���ź�",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jp2.setBackground(new Color(238,221,175));
			jp2.setLayout(null);
			jp2.setBounds(0, 500, 1200, 350);
			jp.setBounds(0, 0, 1200, 350);
			String na="����ƿ��";
			JButton them=new JButton(na);
			them.setFont(new Font("΢���ź�",Font.BOLD,30));
			them.setBounds(50,50,180,80);
			them.setBackground(new Color(254,247,231));
			them.setForeground(new Color(120,81,14));
			them.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			jp.add(them);
			
			JButton back,over1,go1,breaks;

			over1=new JButton("��");
			over1.setContentAreaFilled(false);
			over1.setBorderPainted(false);
			over1.setFont(new Font("΢���ź�",Font.BOLD,30));
			over1.setForeground(new Color(120,81,14));
			over1.setBounds(1125,-25,100,100);
			
			
			back=new JButton("�ӻغ���");
			breaks=new JButton("����ƿ��");
			go1=new JButton("����");
			back.setBounds(725, 60, 150, 50);
			breaks.setBounds(900,60,150,50);
			go1.setBounds(1075,60,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			back.setFont(f);
			go1.setFont(f);
			breaks.setFont(f);
			over1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			breaks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					int count = service.breakBottle(bottle);
					if(count > 0) {
						JOptionPane.showMessageDialog(null, "�������һ��Ư��ƿ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "����Ư��ƿʧ����Ŷ������", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			go1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					String[] messages = bottle.getMessage().split("\\|");
					String msg;
					if(messages!=null) {
						msg = messages[0];
					}else {
						msg = "";
					}
					if(!StringUtil.empty(ja.getText())) {
						msg=msg+"|"+ja.getText();
					}else {
						JOptionPane.showMessageDialog(null, "û����д���͵���Ϣ�ϣ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
					bottle.setMessage(msg);
					int count = service.throwBottle(bottle);
					if(count>0) {
						JOptionPane.showMessageDialog(null, "Ư��ƿ���������Ϣ�����ˣ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ư��ƿ��Ȼû���뿪��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			go1.setContentAreaFilled(false);
			over1.setContentAreaFilled(false);
			back.setContentAreaFilled(false);
			breaks.setContentAreaFilled(false);
			jp.add(back);
			jp.add(breaks);
			jp.add(over1);
			jp.add(go1);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a){
					setVisible(false);
					int count = service.throwBottle(bottle);
					if(count>0) {
						JOptionPane.showMessageDialog(null, "Ư��ƿ���ӻ��˺��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ư��ƿ��Ȼû�б��ӵ���", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			
			jta=new JTextArea();
			jta.setSize(new Dimension(1140,150));
			jta.setLocation(30,150);
			jta.setTabSize(4);
			jta.setFont(new Font("΢���ź�", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			
			JLabel hy=new JLabel("��Ӧ��");
			hy.setBounds(30, 360, 100, 50);
			hy.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy.setForeground(new Color(130,100,9));
			jp2.add(hy);
			
			JLabel j=new JLabel("<html><body>"+"Ư��ƿ���Ư������ɳ̲��������������ˡ�" +"<br>"+"<br>"+"<&nbsp>"+"<&nbsp>"+"<&nbsp>"+"��ƿ�Ӱ����������ݣ��ɾٱ���"+"<body></html>",Label.LEFT);
			j.setFont(new Font("΢���ź�", Font.BOLD, 16));
			j.setForeground(new Color(181,179,157));
			j.setBorder(new TitledBorder(null,"",TitledBorder.LEADING, TitledBorder.TOP, null,new Color(139,113,76)));
			j.setBounds(30, 580, 1140, 105);
			jp2.add(j);
			
			
			ja=new JTextArea();
			ja.setSize(new Dimension(1140,150));
			ja.setLocation(30,410);
			ja.setTabSize(4);
			ja.setFont(new Font("΢���ź�", Font.BOLD, 30));
			ja.setLineWrap(true);
			ja.setWrapStyleWord(true);
			ja.setBackground(Color.WHITE);
			ja.setVisible(true);
			jp2.add(ja);
			
			JButton over,go;
			over=new JButton("�ر�");
			go=new JButton("�ӳ�ȥ");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
			container.add(jp2);
		}
	}
	class xinqing extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta,ja;
		private JLabel u;
		private Bottle bottle;
		
		public void open(Bottle bottle) {
			this.bottle = bottle;
			String[] messages = bottle.getMessage().split("\\|");
			int xKinds = Integer.parseInt(messages[0]);
			if(messages.length>1) {
				jta.setText(messages[1]);
			}else {
				jta.setText("");
			}
			
			if(messages.length>2) {
				ja.setText(messages[2]);
			}else {
				ja.setText("");
			}
			if(xKinds==0) {
				u.setIcon(new ImageIcon(ResourceUtil.getResource("img/11.png")));
			}else if(xKinds==1) {
				u.setIcon(new ImageIcon(ResourceUtil.getResource("img/222.png")));
			}else {
				u.setIcon(new ImageIcon(ResourceUtil.getResource("img/333.png")));
			}
			jta.setEditable(false);
			this.setVisible(true);
		}

		public xinqing(){
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel jp2=new JPanel();
			
			f=new Font("΢���ź�",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jp2.setBackground(new Color(238,221,175));
			jp2.setLayout(null);
			jp2.setBounds(0, 500, 1200, 350);
			jp.setBounds(0, 0, 1200, 350);
			String na="����ƿ��";
			JButton them=new JButton(na);
			them.setFont(new Font("΢���ź�",Font.BOLD,30));
			them.setBounds(125,50,180,80);
			them.setBackground(new Color(254,247,231));
			them.setForeground(new Color(120,81,14));
			them.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			jp.add(them);
			
			JButton back,over1,go1,breaks;

			over1=new JButton("��");
			over1.setContentAreaFilled(false);
			over1.setBorderPainted(false);
			over1.setFont(new Font("΢���ź�",Font.BOLD,30));
			over1.setForeground(new Color(120,81,14));
			over1.setBounds(1125,-25,100,100);
			
			
			back=new JButton("�ӻغ���");
			breaks=new JButton("����ƿ��");
			go1=new JButton("����");
			back.setBounds(725, 60, 150, 50);
			breaks.setBounds(900,60,150,50);
			go1.setBounds(1075,60,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			back.setFont(f);
			go1.setFont(f);
			breaks.setFont(f);
			over1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			breaks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					int count = service.breakBottle(bottle);
					if(count > 0) {
						JOptionPane.showMessageDialog(null, "�������һ��Ư��ƿ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "����Ư��ƿʧ����Ŷ������", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			go1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					String[] messages = bottle.getMessage().split("\\|");
					String msg;
					if(messages.length>1) {
						msg = messages[0]+"|"+messages[1];
					}else {
						msg = "";
					}
					if(!StringUtil.empty(ja.getText())) {
						msg=msg+"|"+ja.getText();
					}else {
						JOptionPane.showMessageDialog(null, "û����д���͵���Ϣ�ϣ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
					bottle.setMessage(msg);
					int count = service.throwBottle(bottle);
					if(count>0) {
						JOptionPane.showMessageDialog(null, "Ư��ƿ���������Ϣ�����ˣ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ư��ƿ��Ȼû���뿪��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			
			go1.setContentAreaFilled(false);
			over1.setContentAreaFilled(false);
			back.setContentAreaFilled(false);
			breaks.setContentAreaFilled(false);
			jp.add(back);
			jp.add(breaks);
			jp.add(over1);
			jp.add(go1);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a){
					setVisible(false);
					int count = service.throwBottle(bottle);
					if(count>0) {
						JOptionPane.showMessageDialog(null, "Ư��ƿ���ӻ��˺��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ư��ƿ��Ȼû�б��ӵ���", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			
			jta=new JTextArea();
			jta.setSize(new Dimension(1140,150));
			jta.setLocation(30,150);
			jta.setTabSize(4);
			jta.setFont(new Font("΢���ź�", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			
			JLabel hy=new JLabel("��Ӧ��");
			hy.setBounds(30, 360, 100, 50);
			hy.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy.setForeground(new Color(130,100,9));
			jp2.add(hy);
			
			JLabel j=new JLabel("<html><body>"+"Ư��ƿ���Ư������ɳ̲��������������ˡ�" +"<br>"+"<br>"+"<&nbsp>"+"<&nbsp>"+"<&nbsp>"+"��ƿ�Ӱ����������ݣ��ɾٱ���"+"<body></html>",Label.LEFT);
			j.setFont(new Font("΢���ź�", Font.BOLD, 16));
			j.setForeground(new Color(181,179,157));
			j.setBorder(new TitledBorder(null,"",TitledBorder.LEADING, TitledBorder.TOP, null,new Color(139,113,76)));
			j.setBounds(30, 580, 1140, 105);
			jp2.add(j);
			
			
			ja=new JTextArea();
			ja.setSize(new Dimension(1140,150));
			ja.setLocation(30,410);
			ja.setTabSize(4);
			ja.setFont(new Font("΢���ź�", Font.BOLD, 30));
			ja.setLineWrap(true);
			ja.setWrapStyleWord(true);
			ja.setBackground(Color.WHITE);
			ja.setVisible(true);
			jp2.add(ja);
			
			JButton over,go;
			over=new JButton("�ر�");
			go=new JButton("�ӳ�ȥ");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			u=new JLabel(new ImageIcon(ResourceUtil.getResource("img/11.png")));
			jp.add(u);
			u.setBounds(25,50,80,80);
			container.add(jp);
			container.add(jp2);
		}
	}
	class tiwen extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta,ja;
		private Bottle bottle;
		
		public void open(Bottle bottle) {
			this.bottle = bottle;
			String[] messages = bottle.getMessage().split("\\|");
			System.out.println(messages.length);
			jta.setText(messages[0]);
			if(messages.length>1) {
				ja.setText(messages[1]);
			}else {
				ja.setText("");
			}
			jta.setEditable(false);
			this.setVisible(true);
		}


		public tiwen(){
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel jp2=new JPanel();
			
			f=new Font("΢���ź�",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jp2.setBackground(new Color(238,221,175));
			jp2.setLayout(null);
			jp2.setBounds(0, 500, 1200, 350);
			jp.setBounds(0, 0, 1200, 350);
			String na="����ƿ��";
			JButton them=new JButton(na);
			them.setFont(new Font("΢���ź�",Font.BOLD,30));
			them.setBounds(50,50,180,80);
			them.setBackground(new Color(254,247,231));
			them.setForeground(new Color(120,81,14));
			them.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			jp.add(them);
			
			JButton back,over1,go1,breaks;

			over1=new JButton("��");
			over1.setContentAreaFilled(false);
			over1.setBorderPainted(false);
			over1.setFont(new Font("΢���ź�",Font.BOLD,30));
			over1.setForeground(new Color(120,81,14));
			over1.setBounds(1125,-25,100,100);
			
			
			back=new JButton("�ӻغ���");
			breaks=new JButton("����ƿ��");
			go1=new JButton("����");
			back.setBounds(725, 60, 150, 50);
			breaks.setBounds(900,60,150,50);
			go1.setBounds(1075,60,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			back.setFont(f);
			go1.setFont(f);
			breaks.setFont(f);
			over1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			breaks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					int count = service.breakBottle(bottle);
					if(count > 0) {
						JOptionPane.showMessageDialog(null, "�������һ��Ư��ƿ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "����Ư��ƿʧ����Ŷ������", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			go1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					String[] messages = bottle.getMessage().split("\\|");
					String msg;
					if(messages.length>0) {
						msg = messages[0];
					}else {
						msg = "";
					}
					if(!StringUtil.empty(ja.getText())) {
						msg=msg+"|"+ja.getText();
					}else {
						JOptionPane.showMessageDialog(null, "û����д���͵���Ϣ�ϣ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
					bottle.setMessage(msg);
					int count = service.throwBottle(bottle);
					if(count>0) {
						JOptionPane.showMessageDialog(null, "Ư��ƿ���������Ϣ�����ˣ�", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ư��ƿ��Ȼû���뿪��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			go1.setContentAreaFilled(false);
			over1.setContentAreaFilled(false);
			back.setContentAreaFilled(false);
			breaks.setContentAreaFilled(false);
			jp.add(back);
			jp.add(breaks);
			jp.add(over1);
			jp.add(go1);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a){
					setVisible(false);
					int count = service.throwBottle(bottle);
					if(count>0) {
						JOptionPane.showMessageDialog(null, "Ư��ƿ���ӻ��˺��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ư��ƿ��Ȼû�б��ӵ���", "��ʾ",JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			
			jta=new JTextArea();
			jta.setSize(new Dimension(1140,150));
			jta.setLocation(30,150);
			jta.setTabSize(4);
			jta.setFont(new Font("΢���ź�", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			
			JLabel hy=new JLabel("��Ӧ��");
			hy.setBounds(30, 360, 100, 50);
			hy.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy.setForeground(new Color(130,100,9));
			jp2.add(hy);
			
			JLabel j=new JLabel("<html><body>"+"Ư��ƿ���Ư������ɳ̲��������������ˡ�" +"<br>"+"<br>"+"<&nbsp>"+"<&nbsp>"+"<&nbsp>"+"��ƿ�Ӱ����������ݣ��ɾٱ���"+"<body></html>",Label.LEFT);
			j.setFont(new Font("΢���ź�", Font.BOLD, 16));
			j.setForeground(new Color(181,179,157));
			j.setBorder(new TitledBorder(null,"",TitledBorder.LEADING, TitledBorder.TOP, null,new Color(139,113,76)));
			j.setBounds(30, 580, 1140, 105);
			jp2.add(j);
			
			
			ja=new JTextArea();
			ja.setSize(new Dimension(1140,150));
			ja.setLocation(30,410);
			ja.setTabSize(4);
			ja.setFont(new Font("΢���ź�", Font.BOLD, 30));
			ja.setLineWrap(true);
			ja.setWrapStyleWord(true);
			ja.setBackground(Color.WHITE);
			ja.setVisible(true);
			jp2.add(ja);
			
			JButton over,go;
			over=new JButton("�ر�");
			go=new JButton("�ӳ�ȥ");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
			container.add(jp2);
		}
	}
	class putong2 extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta;
		private JTextArea ja;
		private void open(Bottle bottle) {
			String[] msg = bottle.getMessage().split("\\|");
			if(msg!=null) {
				jta.setText(msg[0]);
			}else {
				jta.setText("");
			}
			if(msg.length>1) {
				ja.setText(msg[1]);
			}else {
				ja.setText("");
			}
			
			jta.setEditable(false);
			ja.setEditable(false);
			this.setVisible(true);
		}

		public putong2(){
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel jp2=new JPanel();
			
			f=new Font("΢���ź�",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jp2.setBackground(new Color(238,221,175));
			jp2.setLayout(null);
			jp2.setBounds(0, 500, 1200, 350);
			jp.setBounds(0, 0, 1200, 350);

			JButton over1;

			over1=new JButton("��");
			over1.setContentAreaFilled(false);
			over1.setBorderPainted(false);
			over1.setFont(new Font("΢���ź�",Font.BOLD,30));
			over1.setForeground(new Color(120,81,14));
			over1.setBounds(1125,-25,100,100);
			jp.add(over1);
			
			jta=new JTextArea();
			jta.setSize(new Dimension(1140,250));
			jta.setLocation(30,50);
			jta.setTabSize(4);
			jta.setFont(new Font("΢���ź�", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			

			JLabel hy1=new JLabel("�����ͨƿ��");
			hy1.setBounds(30,0, 130, 50);
			hy1.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy1.setForeground(new Color(130,100,9));
			jp.add(hy1);
			
			
			JLabel hy=new JLabel("TA�Ļ�Ӧ��");
			hy.setBounds(30, 360, 120, 50);
			hy.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy.setForeground(new Color(130,100,9));
			jp2.add(hy);
			
			
			ja=new JTextArea();
			ja.setSize(new Dimension(1140,250));
			ja.setLocation(30,410);
			ja.setTabSize(4);
			ja.setFont(new Font("΢���ź�", Font.BOLD, 30));
			ja.setLineWrap(true);
			ja.setWrapStyleWord(true);
			ja.setBackground(Color.WHITE);
			ja.setVisible(true);
			jp2.add(ja);
			
			JButton over,go;
			over=new JButton("�ر�");
			go=new JButton("�ӳ�ȥ");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
			container.add(jp2);
		}
	}
	class xinqing2 extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta,ja;
		private JLabel hy1;
		
		public void open(Bottle bottle) {
			String[] msgs = bottle.getMessage().split("\\|");
			jta.setText(msgs[0]);
			System.out.println(msgs.length);
			int xKinds = Integer.parseInt(msgs[0]);
			if(msgs.length>1) {
				jta.setText(msgs[1]);
			}else {
				jta.setText("");
			}
			
			if(msgs.length>2) {
				ja.setText(msgs[2]);
			}else {
				ja.setText("");
			}
			if(xKinds==0) {
				hy1.setText("�������ƿ��(������)");
			}else if(xKinds==1) {
				hy1.setText("�������ƿ��(�е���)");
			}else if(xKinds==2) {
				hy1.setText("�������ƿ��(������)");
			}
			jta.setEditable(false);
			ja.setEditable(false);
			this.setVisible(true);
		}

		public xinqing2(){
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel jp2=new JPanel();
			
			f=new Font("΢���ź�",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jp2.setBackground(new Color(238,221,175));
			jp2.setLayout(null);
			jp2.setBounds(0, 500, 1200, 350);
			jp.setBounds(0, 0, 1200, 350);

			JButton over1;

			over1=new JButton("��");
			over1.setContentAreaFilled(false);
			over1.setBorderPainted(false);
			over1.setFont(new Font("΢���ź�",Font.BOLD,30));
			over1.setForeground(new Color(120,81,14));
			over1.setBounds(1125,-25,100,100);
			jp.add(over1);
			
			jta=new JTextArea();
			jta.setSize(new Dimension(1140,250));
			jta.setLocation(30,50);
			jta.setTabSize(4);
			jta.setFont(new Font("΢���ź�", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			

			hy1=new JLabel("�������ƿ��");
			hy1.setBounds(30,0, 250, 50);
			hy1.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy1.setForeground(new Color(130,100,9));
			jp.add(hy1);
			
			
			JLabel hy=new JLabel("TA�Ļ�Ӧ��");
			hy.setBounds(30, 360, 120, 50);
			hy.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy.setForeground(new Color(130,100,9));
			jp2.add(hy);
			
			
			ja=new JTextArea();
			ja.setSize(new Dimension(1140,250));
			ja.setLocation(30,410);
			ja.setTabSize(4);
			ja.setFont(new Font("΢���ź�", Font.BOLD, 30));
			ja.setLineWrap(true);
			ja.setWrapStyleWord(true);
			ja.setBackground(Color.WHITE);
			ja.setVisible(true);
			jp2.add(ja);
			
			JButton over,go;
			over=new JButton("�ر�");
			go=new JButton("�ӳ�ȥ");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
			container.add(jp2);
		}
	}
	class tiwen2 extends JDialog{
		private static final long serialVersionUID = 1L;
		private JTextArea jta,ja;
		public void open(Bottle bottle) {
			String[] msgs = bottle.getMessage().split("\\|");
			jta.setText(msgs[0]);
			if(msgs.length>1) {
				ja.setText(msgs[1]);
			}else {
				ja.setText("");
			}
			
			jta.setEditable(false);
			ja.setEditable(false);
			this.setVisible(true);
		}
		public tiwen2(){
			this.setUndecorated(true); 
			this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			this.setSize(1200,700);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			setResizable(false);
			Container container=getContentPane();
			JPanel jp=new JPanel();
			JPanel jp2=new JPanel();
			
			f=new Font("΢���ź�",Font.BOLD,20);
			jp.setLayout(null);
			jp.setBackground(new Color(253,240,215));
			jp2.setBackground(new Color(238,221,175));
			jp2.setLayout(null);
			jp2.setBounds(0, 500, 1200, 350);
			jp.setBounds(0, 0, 1200, 350);

			JButton over1;

			over1=new JButton("��");
			over1.setContentAreaFilled(false);
			over1.setBorderPainted(false);
			over1.setFont(new Font("΢���ź�",Font.BOLD,30));
			over1.setForeground(new Color(120,81,14));
			over1.setBounds(1125,-25,100,100);
			jp.add(over1);
			
			jta=new JTextArea();
			jta.setSize(new Dimension(1140,250));
			jta.setLocation(30,50);
			jta.setTabSize(4);
			jta.setFont(new Font("΢���ź�", Font.BOLD, 30));
			jta.setLineWrap(true);
			jta.setWrapStyleWord(true);
			jta.setBackground(new Color(248,243,231));
			jta.setVisible(true);
			jp.add(jta);
			

			JLabel hy1=new JLabel("�������ƿ��");
			hy1.setBounds(30,0, 130, 50);
			hy1.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy1.setForeground(new Color(130,100,9));
			jp.add(hy1);
			
			
			JLabel hy=new JLabel("TA�Ļ�Ӧ��");
			hy.setBounds(30, 360, 120, 50);
			hy.setFont(new Font("΢���ź�", Font.BOLD, 20));
			hy.setForeground(new Color(130,100,9));
			jp2.add(hy);
			
			
			ja=new JTextArea();
			ja.setSize(new Dimension(1140,250));
			ja.setLocation(30,410);
			ja.setTabSize(4);
			ja.setFont(new Font("΢���ź�", Font.BOLD, 30));
			ja.setLineWrap(true);
			ja.setWrapStyleWord(true);
			ja.setBackground(Color.WHITE);
			ja.setVisible(true);
			jp2.add(ja);
			
			JButton over,go;
			over=new JButton("�ر�");
			go=new JButton("�ӳ�ȥ");
			over.setBounds(1000, 600, 100, 50);
			go.setBounds(880,600,100,50);
			f=new Font("΢���ź�",Font.BOLD,20);
			over.setFont(f);
			go.setFont(f);
			over1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					t.setVisible(true);
				}
			});
			go.setContentAreaFilled(false);
			over.setContentAreaFilled(false);
			jp.add(over);
			jp.add(go);
			container.add(jp);
			container.add(jp2);
		}
	}
}
