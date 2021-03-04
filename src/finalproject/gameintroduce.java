package finalproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class gameintroduce implements ppp{
	static JFrame frm = new JFrame("植物大戰僵屍");
	static Container cp = frm.getContentPane();
	static JButton back;
	static JLabel Jbackground;
	static JPanel jp = new JPanel();
	public gameintroduce() {
		back = new JButton(helpback);
		back.setBounds(30, 0, 127,127);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorder(null);
		back.addActionListener(new back());
		back.setVisible(true);
		Jbackground=new JLabel(game);
		Jbackground.setBounds(10, -70, 1131, 705);
		jp.setLayout(null);
		jp.setOpaque(false);
		jp.add(back);
		jp.add(Jbackground);
		cp.add(jp);
		frm.setLocation(0, 0);
		frm.setVisible(true);
		frm.setSize(1440, 1080);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static class back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new helpchoose();
			back.setVisible(false);
			Jbackground.setVisible(false);
			frm.dispose();
		}
	}
}


