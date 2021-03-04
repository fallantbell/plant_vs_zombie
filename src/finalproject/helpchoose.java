package finalproject;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import finalproject.help.back;

public class helpchoose implements ppp{
	static JFrame frm = new JFrame("植物大戰僵屍");
	static Container cp = frm.getContentPane();
	static JButton b1,b2,b,back;
	static JLabel j,j1,j2;
	static JPanel jp = new JPanel();
	public helpchoose() {
		b = new JButton(gameintro);
		b.setBounds(350, 300, 317, 70);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setBorder(null);
		b.addActionListener(new gameintroduces());
		b1 = new JButton(chaintro);
		b1.setBounds(340, 200, 317, 70);
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setFocusPainted(false);
		b1.setBorder(null);
		b1.addActionListener(new characterintroduce());
		back = new JButton(helpback);
		back.setBounds(0, 0, 127,127);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorder(null);
		back.addActionListener(new back());
		j=new JLabel(helpb);
		j.setBounds(0, 0, 1100, 570);
		jp.setLayout(null);
		jp.setOpaque(false);
		jp.add(back);
		jp.add(b);
		jp.add(b1);
		jp.add(j);
		cp.add(jp);
		frm.setLocation(0, 0);
		frm.setVisible(true);
		frm.setSize(1440, 1080);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	static class gameintroduces implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new gameintroduce();
			frm.dispose();
		}
	}
	static class characterintroduce implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new help();
			frm.dispose();
		}
	}
	static class back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new gameframe();
			gameframe.frm.setVisible(true);
			frm.dispose();
		}
	}
}


