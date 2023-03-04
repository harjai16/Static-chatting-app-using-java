package chattingApplication;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import java.net.*;

public class Server  implements ActionListener {
	
	JTextField text1;
	JPanel a1;
	static Box vertical = Box.createVerticalBox();
			
	static JFrame f = new JFrame();
	static DataOutputStream dout;
	
	Server(){
		// green panel at top 
	f.setLayout(null);
	JPanel p1 = new JPanel();
	p1.setBackground(new Color(7,94,84));
	p1.setBounds(0,0,450,70);
	p1.setLayout(null);
	f.add(p1);
	
	// importing back icon 
	ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
	Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
	ImageIcon i3 = new ImageIcon(i2);
	JLabel back = new JLabel(i3);
	back.setBounds(5,20,25,25);
	p1.add(back);
	
	// adding function to the back icon
	back.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent ae) {
			// two methods to close the screen 
			// 1st setVisible(false); 
			System.exit(0); // 2nd 
		}
		
	});
	
	// adding profile picture 
	
	ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
	Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	ImageIcon i6 = new ImageIcon(i5);
	JLabel profile = new JLabel(i6);
	profile.setBounds(40,10,50,50);
	p1.add(profile);
	
	// importing video icon
	ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
	Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	ImageIcon i9 = new ImageIcon(i8);
	JLabel video = new JLabel(i9);
	video.setBounds(300,20,30,30);
	p1.add(video);
	
	//
	ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
	Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
	ImageIcon i12 = new ImageIcon(i11);
	JLabel phone = new JLabel(i12);
	phone.setBounds(360,20,35,30);
	p1.add(phone);
	
	// importing more icon 
	
	ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
	Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
	ImageIcon i15 = new ImageIcon(i14);
	JLabel morevert = new JLabel(i15);
	morevert.setBounds(410,20,10,25);
	p1.add(morevert);
	
	// setting name 
	
	JLabel name= new JLabel("ASHWANI");
	name.setBounds(110,15,100,18);
	name.setForeground(Color.white);
	name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
	
	p1.add(name);
	
	//setting active status 
	
	JLabel status = new JLabel("Active Now");
	status.setBounds(110,35,100,18);
	status.setForeground(Color.white);
	status.setFont(new Font("SAN_SERIF", Font.BOLD, 10));
	p1.add(status);
	
	// creating one more panel for chatting display 
	
    a1 = new JPanel();
	a1.setBounds(5,75,435,570);
	f.add(a1);
	
	// text field 
	 text1 = new JTextField();
	text1.setBounds(5,655,310,40);
	text1.setFont(new Font("SAN_SERIF",Font.PLAIN,17));
	f.add(text1);
	
	
	
	// send button
	JButton send = new JButton("send");
	send.setBounds(320,655,123,40);
	send.setBackground(new Color(7,94,84));
	send.setForeground(Color.white);
	send.addActionListener(this);
	
	f.add(send);
	
	f.setUndecorated(true);
	f.setSize(450,700);
	f.setLocation(200,60);
	f.getContentPane().setBackground(Color.white);
	

	
	
	
	
	
	f.setVisible(true);	
	}
	public void actionPerformed(ActionEvent ae) {
		try {
		String out = text1.getText();
	
	JPanel p2 = formatLabel(out);
	
		
		
	
		a1.setLayout(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		right.add(p2, BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		a1.add(vertical, BorderLayout.PAGE_END);
		
		
		dout.writeUTF(out);
		
		text1.setText("");
		
		
		
		
		f.repaint();
		f.invalidate();
		f.validate();
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
		
		public static JPanel formatLabel(String out) {
			
			JPanel panel = new JPanel();
			
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel(out);
		output.setFont(new Font("tahoma", Font.PLAIN, 16));
		output.setBackground(new Color(0,128,129));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(("hh:mm"));
		JLabel time = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		panel.add(time);
		
		
		
		
			
			
			return panel;
		}
		
		
		
	
		
	

	public static void main(String[] args) {
		
		new Server();
		try {
			ServerSocket skt = new ServerSocket(6001);
			while(true) {
			Socket s =	skt.accept();
			DataInputStream din = new DataInputStream(s.getInputStream());
			 dout = new DataOutputStream(s.getOutputStream());
			
			while (true) {
				
				String msg = din.readUTF();
				JPanel panel = formatLabel(msg);
				JPanel left = new JPanel(new BorderLayout());
				left.add(panel, BorderLayout.LINE_START);
				vertical.add(left);
				f.validate();
				
				
			}
			
			}
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
