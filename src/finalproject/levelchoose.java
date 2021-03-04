package finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import finalproject.levelmodel.back1;

public class levelchoose implements ppp {
	static JFrame frm = new JFrame("植物大戰僵屍");
	static Container cp = frm.getContentPane();
	static JButton[] b = new JButton[5];
	static JPanel jp = new JPanel();
	static JLabel jbackground;
	static int level = 0;

	public levelchoose() {
		frm.setSize(1440, 1080);
		frm.setVisible(true);
		jp.setLayout(null);
		jp.setOpaque(false);
		jbackground = new JLabel(levelchoosebackground);
		jbackground.setBounds(0, 0, 1100, 570);
		b[0] = new JButton(levelwalleasy);
		b[1] = new JButton(levelwallnormal);
		b[2] = new JButton(levelwallhard);
		b[3] = new JButton(backtohome);
		b[4]=new JButton(levelboss);
		b[4].setLocation(360,286);
		b[4].setSize(b[4].getPreferredSize());
		b[4].setOpaque(false);
		b[4].setContentAreaFilled(false);
		b[4].setFocusPainted(false);
		b[4].setBorder(null);
		b[4].setRolloverEnabled(true);
		b[4].addActionListener(new back());
		b[3].setBounds(0, 0, 70, 70);
		b[3].setOpaque(false);
		b[3].setContentAreaFilled(false);
		b[3].setFocusPainted(false);
		b[3].setBorder(null);
		b[3].addActionListener(new back2());
		for (int i = 0; i < 3; i++) {
			b[i].setLocation(110 + i * 250, 80);
			b[i].setSize(b[i].getPreferredSize());
			b[i].setOpaque(false);
			b[i].setContentAreaFilled(false);
			b[i].setFocusPainted(false);
			b[i].setBorder(null);
			b[i].setRolloverEnabled(true);
			jp.add(b[i]);
			b[i].addActionListener(new back());
		}
		b[0].setRolloverIcon(levelwalleasy2);
		b[0].setPressedIcon(levelwalleasy3);
		b[1].setRolloverIcon(levelwallnormal2);
		b[1].setPressedIcon(levelwallnormal3);
		b[2].setRolloverIcon(levelwallhard2);
		b[2].setPressedIcon(levelwallhard3);
		b[4].setRolloverIcon(levelboss2);
		b[4].setPressedIcon(levelboss3);
		jp.add(b[3]);
		jp.add(b[4]);
		jp.add(jbackground);
		cp.add(jp);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static class back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			if (bt.getIcon() == levelwalleasy) {
				level = 1;
				new levelmodel();
				
			} else if (bt.getIcon() == levelwallnormal) {
				level = 2;
				new level2test();
			} else if (bt.getIcon()==levelwallhard) {
				level = 3;
				new level3test();
			}else if(bt.getIcon()==levelboss) {
				new levelboss();
			}
		 frm.dispose();
		}
	}
	static class back2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new gameframe();
			frm.dispose();
		}
	}
}


