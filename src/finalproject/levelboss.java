
package finalproject;

import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.IconView;
import javax.swing.text.html.ImageView;

import javafx.scene.media.AudioClip;

public class levelboss extends JFrame implements ppp, ActionListener {
	static JFrame frm = new JFrame("植物大戰僵屍");
	static JButton back, bpea, bwall, vback;
	static JButton[][] grid = new JButton[5][9];
	static Container cp = frm.getContentPane();
	JPanel jp = new JPanel();
	static JPanel jp2;
	static Timer timerstart, timer0, timer2, timer3, timer4, timer5, timerre, timerph, timert1, timert2, timert3,
			timerzd, tim;
	static JLabel j1, Jbackground, power, powerfram, jvictory;
	static int poweradd = 0;
	static int stime = 0, ptime = 0;
	static String c = "0";
	static levelchoose l;
	static ArrayList<zombie> z0 = new ArrayList<zombie>();
	static ArrayList<zombie> z2 = new ArrayList<zombie>();
	static ArrayList<zombie> z3 = new ArrayList<zombie>();
	static ArrayList<zombie> z4 = new ArrayList<zombie>();
	static ArrayList<zombie> z5 = new ArrayList<zombie>();
	static boolean[][] zombiestop = new boolean[20][20];
	static ArrayList<pea> p = new ArrayList<pea>();
	static ArrayList<wall> w = new ArrayList<wall>();
	static ArrayList<zomdie> zd = new ArrayList<zomdie>();
	static volatile boolean threadrun = true;
	static int zombiedienum = 0;
	static int opennum = 0;

	public levelboss() {
		opennum++;
		threadrun = true;
		zombiedienum = 0;
		timert1 = new Timer();
		timert1.schedule(new t1(), 1, 10);
		timert2 = new Timer();
		timert2.schedule(new t2(), 1, 5000);
		timert3 = new Timer();
		timert3.schedule(new t3(), 1, 100);
		timerstart = new Timer();
		timerstart.schedule(new start(), 1, 40);
		timerre = new Timer();
		timerre.schedule(new re(), 1, 10);
		timerph = new Timer();
		timerph.schedule(new ph(), 1, 3000);
		timerzd = new Timer();
		timerzd.schedule(new zombiedie(), 1, 60);
		jp2 = new jp2();
		bpea = new JButton(pppea);
		bpea.setOpaque(false);
		bpea.setContentAreaFilled(false);
		bpea.setFocusPainted(false);
		bpea.setBorder(null);
		bpea.setBounds(75, 0, 70, 80);
		bpea.addMouseListener(new peamove());
		bpea.addMouseMotionListener(new peamove());
		bwall = new JButton(ppwall);
		bwall.setOpaque(false);
		bwall.setContentAreaFilled(false);
		bwall.setFocusPainted(false);
		bwall.setBorder(null);
		bwall.setBounds(140, 0, 70, 80);
		bwall.addMouseListener(new wallmove());
		bwall.addMouseMotionListener(new wallmove());
		back = new JButton(backtohome);
		back.setBounds(0, 0, 70, 70);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorder(null);
		back.addActionListener(new back1());
		powerfram = new JLabel(powerframe);
		powerfram.setBounds(150, -20, 700, 100);
		Jbackground = new JLabel(fightbackground);
		Jbackground.setBounds(0, 0, 1100, 570);
		jvictory = new JLabel(victory);
		jvictory.setBounds(225, 50, 500, 300);
		vback = new JButton(con);
		vback.setBounds(325, 300, 352, 100);
		vback.addActionListener(new con());
		vback.setOpaque(false);
		vback.setContentAreaFilled(false);
		vback.setFocusPainted(false);
		vback.setBorder(null);
		vback.setRolloverEnabled(true);
		vback.setRolloverIcon(conup);
		vback.setPressedIcon(conpress);
		jvictory.setVisible(false);
		vback.setVisible(false);
		jp2.add(vback);
		jp2.add(jvictory);
		jp2.add(back);
		jp2.add(bpea);
		jp2.add(bwall);
		jp2.add(powerfram);
		jp2.setLayout(null);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = new JButton(ground);
				grid[i][j].setBounds(170 + j * 69, 80 + i * 88, 69, 88);
				grid[i][j].addMouseListener(new peamove());
				grid[i][j].addMouseListener(new wallmove());
				jp2.add(grid[i][j]);
			}
		}
		jp2.add(Jbackground);
		cp.add(jp2);
		frm.setLocation(0, 0);
		frm.pack();
		frm.setVisible(true);
		frm.setSize(1440, 1080);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tim = new Timer();
		tim.schedule(new fakethread(jp2), 1, 10);
	}

	static int zombienum;
	static boolean victorystop = false;
	static int skilltime=0;
	class jp2 extends JPanel {
		public jp2() {
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for (int j = 0; j < 9; j++) {
				int judge = 0;
				for (int i = 0; i < 5; i++) {
					if (grid[i][j].getIcon() == peaground) {
						judge++;
					}
				}
				if (judge == 5) {
					for (int i = 0; i < p.size(); i++) {
						p.get(i).attack = 15;
						if (p.get(i).x > 170 + j * 69 && p.get(i).x < 170 + (j + 1) * 69) {
							p.get(i).attack += 3;
							g.drawImage(attack.getImage(), 170 + j * 69, p.get(i).y + 60, 20, 20, null);
						}
					}
				}
			}
			if (zombiedienum > 0 ) {
				reset();
				AudioClip vic = new AudioClip(getClass().getResource("勝利音效.mp3").toString());
				vic.play();
				jvictory.setVisible(true);
				victorystop = true;
				vback.setVisible(true);
			}
			if(boss1.skill&&boss1.stop) {
				skilltime++;
				if(skilltime%100<4) {
					g.drawImage(shine1.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine1.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<8) {
					g.drawImage(shine2.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine2.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<12) {
					g.drawImage(shine3.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine3.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<16) {
					g.drawImage(shine4.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine4.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<20) {
					g.drawImage(shine5.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine5.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<24) {
					g.drawImage(shine6.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine6.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<28) {
					g.drawImage(shine7.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine7.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<32) {
					g.drawImage(shine8.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine8.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<36) {
					g.drawImage(shine9.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine9.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<40) {
					g.drawImage(shine10.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine10.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<44) {
					g.drawImage(shine11.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine11.getImage(), boss1.x-30,80+4*88,72,73, null);
				}else if(skilltime%100<48) {
					g.drawImage(shine12.getImage(), boss1.x-60,80+0*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-60,80+1*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-60,80+2*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-60,80+3*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-60,80+4*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-30,80+0*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-30,80+1*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-30,80+2*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-30,80+3*88,72,73, null);
					g.drawImage(shine12.getImage(), boss1.x-30,80+4*88,72,73, null);
				}
			}
			for (int i = 0; i < p.size(); i++) {
				for (int j = 0; j < p.get(i).tbullet.size(); j++) {
					g.drawImage(peabullet.getImage(), p.get(i).x + p.get(i).tbullet.get(j), p.get(i).y, 30, 30, null);
					if (p.get(i).x + p.get(i).tbullet.get(j) > 1100) {
						p.get(i).tbullet.remove(p.get(i).tbullet.get(j));
					}
				}
			}
			g.drawImage(bossimage.getImage(), boss1.x, 80, 294, 440, null);
			g.drawImage(powerblue.getImage(), 225, 10, poweradd * 5, 45, null);
			g.setColor(Color.black);
			g.drawRect(boss1.x, 80 + 0 * 88, 200, 7);
			g.setColor(Color.red);
			g.fillRect(boss1.x, 80 + 0 * 88, boss1.hp / 15, 7);
			g.drawRect(boss1.x, 80 + 0 * 88, boss1.hp / 15, 7);
			g.setColor(Color.black);
			if (flagpea) {
				g.drawImage(bigppea.getImage(), px - 70, py - 70, 100, 100, null);
			}
			if (flagwall) {
				g.drawImage(bigpwall.getImage(), px - 70, py - 70, 100, 100, null);
			}
			g.drawString(c, 755, 35);
		}

	}

	static boolean flagpea = false, flagwall = false;
	static int peax, peay, wallx, wally, peaclick = 0;

	static class peamove implements MouseListener, MouseMotionListener, ppp {
		public void mousePressed(MouseEvent e) {

		}

		public void mouseDragged(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {
			JButton bt = (JButton) e.getSource();
			if (bt.getIcon() == pppea) {
				flagpea = true;
				flagwall = false;
			} else {
				if (poweradd >= 30) {
					if (flagpea == true) {
						bt.setIcon(peaground);
						pea p2 = new pea();
						p2.i = (bt.getY() - 80) / 88;
						p2.j = (bt.getX() - 170) / 69;
						p2.x = bt.getX() + 30;
						p2.y = bt.getY() + 10;
						p.add(p2);
						poweradd -= 30;
						flagpea = false;
					}
				}
			}
		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	class wallmove implements MouseListener, MouseMotionListener, ppp {
		public void mousePressed(MouseEvent e) {

		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			JButton bt = (JButton) e.getSource();
			if (bt == bwall) {
				flagwall = true;
				flagpea = false;
			} else {
				if (flagwall == true) {
					if (poweradd >= 35) {
						bt.setIcon(wallground);
						wall w2 = new wall();
						w2.i = (bt.getY() - 80) / 88;
						w2.j = (bt.getX() - 170) / 69;
						w2.x = bt.getX() + 30;
						w2.y = bt.getY() + 10;
						w.add(w2);
						poweradd -= 35;
						flagwall = false;
					}
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	static class back1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new levelchoose();
			reset();
			frm.dispose();
		}
	}

	static int px, py;

	public class start extends TimerTask {
		// TODO Auto-generated method stub
		public void run() {
			stime++;
			ptime++;
			Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
			px = point.x;
			py = point.y;
			if (ptime == 2 || ptime == 48 || ptime == 8 || ptime == 16 || ptime == 32) {
				AudioClip but = new AudioClip(getClass().getResource("browbeat1.mp3").toString());
				but.play();
			}
			if (stime >= 5) {
				stime = 0;
				if (poweradd < 100) {
					poweradd++;
					c = Integer.toString(poweradd);
				}
			}
		}
	}

	class ph extends TimerTask {// 殭屍攻擊植物

		public void run() {

			for (int i = 0; i < p.size(); i++) {
				p.get(i).hp -= boss1.attack * p.get(i).hurtnum;
			}
			for (int i = 0; i < w.size(); i++) {
				w.get(i).hp -= boss1.attack * w.get(i).hurtnum;
			}

		}
	}

	class t2 extends TimerTask {// 子彈加入arraylist

		public void run() {

			for (int i = 0; i < p.size(); i++) {
				AudioClip but = new AudioClip(getClass().getResource("launcher1.mp3").toString());
				but.play();
				p.get(i).tbullet.add(0);
			}

		}
	}

	class t1 extends TimerTask {// 子彈x軸增加

		public void run() {
			if (victorystop == false) {
				for (int i = 0; i < p.size(); i++) {
					for (int j = 0; j < p.get(i).tbullet.size(); j++) {
						p.get(i).tbullet.set(j, p.get(i).tbullet.get(j) + 2);

					}
				}
			}
		}
	}

	static boss1 boss1 = new boss1();

	class t3 extends TimerTask {// 一般殭屍x移動

		public void run() {
			if (!boss1.stop) {
				boss1.x--;
			}
		}
	}

	class fakethread extends TimerTask {
		JPanel panel;

		public fakethread(JPanel panel) {
			this.panel = panel;
		}

		public void run() {
			panel.repaint();
		}
	}

	class re extends TimerTask {// 碰撞偵測
		public void planthitboss() {
			for (int i = 0; i < p.size(); i++) {
				if (p.get(i).tbullet.size() > 0) {// 有子彈
					if (p.get(i).x + p.get(i).tbullet.get(0) > boss1.x) {// 子彈x>殭屍x
						p.get(i).tbullet.remove(0);
						boss1.hp -= p.get(i).attack;
					}
				}
			}
		}

		public void bossmeetplant() {
			for (int i = 0; i < p.size(); i++) {
				p.get(i).hurtnum = 0;
				if (p.get(i).x + 30 < boss1.x + 100 && p.get(i).x + 30 > boss1.x) {
					boss1.stop = true;
					boss1.skill=true;
					p.get(i).hurtnum++;
				}
			}
			for (int i = 0; i < w.size(); i++) {
				w.get(i).hurtnum = 0;
				
				if (w.get(i).x + 30 < boss1.x + 100 && w.get(i).x + 30 > boss1.x) {
					boss1.stop = true;
					boss1.skill=true;
					w.get(i).hurtnum++;
				}
			}
		}

		public void bosswalk() {
			boss1.stop = false;
		}

		public void run() {
			// 子彈殭屍碰撞
			planthitboss();
			bosswalk();
			bossmeetplant();

			// 植物血量歸零
			for (int k = 0; k < p.size(); k++) {// 豌豆
				if (p.get(k).hp <= 0) {
					grid[p.get(k).i][p.get(k).j].setIcon(ground);
					p.remove(k);

				}
			}
			for (int k = 0; k < w.size(); k++) {// 堅果
				if (w.get(k).hp <= 0) {

					grid[w.get(k).i][w.get(k).j].setIcon(ground);
					w.remove(k);

				}
			}
			if(boss1.hp<=0) {
				zombiedienum++;
			}
		}

	}

	public class zombiedie extends TimerTask {
		@Override
		public void run() {
			for (int i = 0; i < zd.size(); i++) {
				zd.get(i).time++;
			}
		}
	}

	static class pea implements ppp {
		int hp = 50;
		int attack = 15;
		int x, y, i, j;
		int hurtnum = 0;
		ArrayList<Integer> tbullet = new ArrayList<>();
	}

	static class zombie {
		int hp = 50;
		int attack = 30;
		int x = 1100, y;
	}

	static class wall {
		int hp = 200;
		int attack = 0;
		int x, y, i, j;
		int hurtnum = 0;
	}

	static class zomdie {
		int x, y;
		int time = 0;
	}

	static class boss1 {
		int x = 1100, y;
		int attack = 40;
		int hp = 3000;
		boolean stop = false;
		boolean skill=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	static class con implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new levelchoose();
			reset();
			frm.dispose();
		}
	}

	static void reset() {
		stime = 0;
		ptime = 0;
		poweradd = 0;
		boss1.x=1100;
		boss1.hp=3000;
		boss1.stop=false;
		boss1.skill=false;
		z0.clear();
		z2.clear();
		z3.clear();
		z4.clear();
		z5.clear();
		p.clear();
		w.clear();
		zd.clear();
		tim.cancel();
		timert1.cancel();
		timert2.cancel();
		timert3.cancel();
		timerstart.cancel();
		timer3.cancel();
		timer4.cancel();
		timer5.cancel();
		timer2.cancel();
		timerre.cancel();
		timerph.cancel();
		timerzd.cancel();
		jvictory.setVisible(false);
		victorystop = false;
		vback.setVisible(false);
		back.setVisible(false);
		bpea.setVisible(false);
		bwall.setVisible(false);
		flagpea = false;
		flagwall = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j].setVisible(false);
			}
		}
		zombienum = 0;
		zombiedienum = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				zombiestop[i][j] = false;
			}
		}

	}
}


