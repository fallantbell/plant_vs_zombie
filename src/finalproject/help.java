package finalproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class help implements ppp{
	static JFrame frm = new JFrame("植物大戰僵屍");
	static Container cp = frm.getContentPane();
	static JButton b,wall,zombie1,back,trizombie1;
	static JLabel l,Jbackground,introduce;
	static JPanel jp = new JPanel();
	public help() {
		b = new JButton(smallpea);
		b.setBounds(70, 400, 70, 70);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setBorder(null);
		b.addActionListener(new peaintroduce());
		wall = new JButton(smallwall);
		wall.setBounds(140, 350, 70, 70);
		wall.setOpaque(false);
		wall.setContentAreaFilled(false);
		wall.setFocusPainted(false);
		wall.setBorder(null);
		wall.addActionListener(new wallintroduce());
		zombie1 = new JButton(smallzombie1);
		zombie1.setBounds(930, 350, 70, 95);
		zombie1.setOpaque(false);
		zombie1.setContentAreaFilled(false);
		zombie1.setFocusPainted(false);
		zombie1.setBorder(null);
		zombie1.addActionListener(new zombie1introduce());
		trizombie1 = new JButton(trizombie2);
		trizombie1.setBounds(840, 300, 70, 95);
		trizombie1.setOpaque(false);
		trizombie1.setContentAreaFilled(false);
		trizombie1.setFocusPainted(false);
		trizombie1.setBorder(null);
		trizombie1.addActionListener(new trizombieintroduce());
		trizombie1.setVisible(true);
		back = new JButton(helpback);
		back.setBounds(30, 0, 127,127);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorder(null);
		back.addActionListener(new back());
		back.setVisible(true);
		l=new JLabel();
		l.setBounds(500,370,70,95);
		introduce=new JLabel();
		introduce.setBounds(400,-50,438,384);
		Jbackground=new JLabel(inbackground);
		Jbackground.setBounds(0, 0, 1100, 570);
		jp.setLayout(null);
		jp.setOpaque(false);
		jp.add(back);
		jp.add(zombie1);
		jp.add(trizombie1);
		jp.add(wall);
		jp.add(b);
		jp.add(l);
		jp.add(introduce);
		jp.add(Jbackground);
		cp.add(jp);
		frm.setLocation(0, 0);
		frm.setVisible(true);
		frm.setSize(1440, 1080);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class peaintroduce implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			l.setIcon(smallpea);
			introduce.setIcon(peaintroduce);
		}
	}
	static class wallintroduce implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			l.setIcon(smallwall);
			introduce.setIcon(wallintroduce);
		}
	}
	static class zombie1introduce implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			l.setIcon(smallzombie1);
			introduce.setIcon(zombie1introduce);
		}
	}
	static class trizombieintroduce implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			l.setIcon(trizombie2);
			introduce.setIcon(trizombie1introduce);
		}
	}
	static class back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new helpchoose();
			b.setVisible(false);
			wall.setVisible(false);
			zombie1.setVisible(false);
			back.setVisible(false);
			Jbackground.setVisible(false);
			l.setVisible(false);
			introduce.setVisible(false);
			frm.dispose();
		}
	}
}


