package finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.scene.media.AudioClip;

public class gamesover implements ppp {
	static JFrame frm = new JFrame("植物大戰僵屍");
	static JButton jb;
	static JLabel l;
	static Container cp = frm.getContentPane();

	public gamesover() {
		frm.setSize(852, 480);
		frm.setVisible(true);
		AudioClip but=new AudioClip(getClass().getResource("悲傷音效.mp3").toString());
		but.play();
		l = new JLabel(gameover);
		l.setLocation(0, 0);
		l.setSize(852, 480);
		cp.add(l);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


