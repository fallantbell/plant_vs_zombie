package finalproject;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
public class gameframe extends JFrame implements ppp{
	static JFrame frm=new JFrame("植物大戰僵屍");
	static JButton b1,b2,b3;
	static Container cp=frm.getContentPane(); 
	static JPanel jp=new JPanel();
	static JLabel j1,j2;
	AudioClip butt=new AudioClip(getClass().getResource("Motivating and Upbeat Background Music.mp3").toString());
	public gameframe(){
		// TODO Auto-generated method stub
		b1=new JButton(start);
		b2=new JButton(exit);
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setFocusPainted(false);
		b1.setBorder(null);
		b1.setRolloverEnabled(true);
		b1.setRolloverIcon(start2);
		b1.setPressedIcon(start2);
		b2.setOpaque(false);
		b2.setContentAreaFilled(false);
		b2.setFocusPainted(false);
		b2.setBorder(null);
		b2.setRolloverEnabled(true);
		b2.setRolloverIcon(exitw);
		b2.setPressedIcon(exitw);
		b3=new JButton(help);
		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		b3.setFocusPainted(false);
		b3.setBorder(null);
		b3.setRolloverEnabled(true);
		b3.setRolloverIcon(helpw);
		b3.setPressedIcon(helpw);
		b3.addActionListener(new B3());
		b1.addActionListener(new B1());
		b2.addActionListener(new B2());
		b3.setBounds(930, 420, 130, 50);
		b2.setBounds(930, 470, 130, 50);
		b1.setBounds(930, 370, 130, 50);
		jp.setLayout(null);
		jp.setOpaque(false);
		j1=new JLabel(background2);
		j1.setSize(1100,570);
		j1.setLocation(0,0);
		jp.add(j1);
		cp.add(b1);
		cp.add(b2);
		cp.add(b3);
		cp.add(jp);
		frm.setSize(1440,1080);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	class B2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AudioClip but=new AudioClip(getClass().getResource("coin07.mp3").toString());
			but.play();
			butt.stop();
			System.exit(0);
			
		}
	}
	class B1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AudioClip but=new AudioClip(getClass().getResource("coin07.mp3").toString());
			but.play();
			butt.stop();
			new levelchoose();
			frm.dispose();
		}
	}
	static class B3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AudioClip but=new AudioClip(getClass().getResource("coin07.mp3").toString());
			but.play();
			new helpchoose();
			frm.dispose();
		}
	}
}


