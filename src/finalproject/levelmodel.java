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

public class levelmodel extends JFrame implements ppp, ActionListener {
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
	public levelmodel() {
		opennum++;
		threadrun = true;
		zombiedienum = 0;
		timert1 = new Timer();
		timert1.schedule(new t1(), 1, 10);
		timert2 = new Timer();
		timert2.schedule(new t2(), 1, 5000);
		timert3 = new Timer();
		timert3.schedule(new t3(), 1, 40);
		timerstart = new Timer();
		timerstart.schedule(new start(), 1, 40);
		timer0 = new Timer();
		timer0.schedule(new zt0(), 14000, 16000);
		timer3 = new Timer();
		timer3.schedule(new zt3(), 16000, 18000);
		timer4 = new Timer();
		timer4.schedule(new zt4(), 1000, 24000);
		timer5 = new Timer();
		timer5.schedule(new zt5(), 24000, 12000);
		timer2 = new Timer();
		timer2.schedule(new zt2(), 7000, 18000);
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
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				zombiestop[i][j] = false;
			}
		}
		jp2.add(Jbackground);
		cp.add(jp2);
		frm.setLocation(0, 0);
		frm.pack();
		frm.setVisible(true);
		frm.setSize(1440, 1080);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tim = new Timer();
		tim.schedule(new fakethread(jp2), 1, 10);
	}

	static int zombienum;
	static boolean victorystop = false;

	class jp2 extends JPanel {
		public jp2() {
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for(int j=0;j<9;j++) {
				int judge=0;
				for(int i=0;i<5;i++) {
					if(grid[i][j].getIcon()==peaground) {
						judge++;
					}
				}
				if(judge==5) {
					for(int i=0;i<p.size();i++) {
						p.get(i).attack=15;
						if(p.get(i).x>170 + j * 69&&p.get(i).x<170+(j+1)*69) {
							p.get(i).attack+=3;
							g.drawImage(attack.getImage(), 170+j*69,p.get(i).y+60,20,20,null);
						}
					}
				}
			}
			if (zombiedienum > 12 && z0.isEmpty() && z2.isEmpty() && z3.isEmpty() && z4.isEmpty() && z5.isEmpty()) {
				reset();
				AudioClip vic = new AudioClip(getClass().getResource("勝利音效.mp3").toString());
				vic.play();
				jvictory.setVisible(true);
				victorystop = true;
				vback.setVisible(true);
			}
			for (int i = 0; i < zd.size(); i++) {
				if (zd.get(i).time < 11) {
					g.drawImage(zombieddie4.getImage(), zd.get(i).x - 20, zd.get(i).y, 70, 88, null);
				} else if (zd.get(i).time >= 11 && zd.get(i).time <= 22) {
					g.drawImage(zombieddie.getImage(), zd.get(i).x - 20, zd.get(i).y, 70, 88, null);
				} else if (zd.get(i).time >= 22 && zd.get(i).time <= 33) {
					g.drawImage(zombieddie2.getImage(), zd.get(i).x - 30, zd.get(i).y, 70, 88, null);
				} else if (zd.get(i).time >= 33 && zd.get(i).time <= 66) {
					g.drawImage(zombieddie3.getImage(), zd.get(i).x - 40, zd.get(i).y, 70, 88, null);
				}
			}
			if (!victorystop) {
				for (int i = 0; i < p.size(); i++) {
					for (int j = 0; j < p.get(i).tbullet.size(); j++) {
						g.drawImage(peabullet.getImage(), p.get(i).x + p.get(i).tbullet.get(j), p.get(i).y, 30, 30,
								null);
						if (p.get(i).x + p.get(i).tbullet.get(j) > 1100) {
							p.get(i).tbullet.remove(p.get(i).tbullet.get(j));
						}
					}
				}
			}
			for (int i = 0; i < z0.size(); i++) {
				if (!zombiestop[0][i]) {
					g.drawImage(normalzombie.getImage(), z0.get(i).x, 90 + 0 * 88, 70, 80, null);
				} else {
					g.drawImage(zombieeat.getImage(), z0.get(i).x, 90 + 0 * 88, 70, 80, null);
				}
				g.setColor(Color.black);
				g.drawRect(z0.get(i).x, 80 + 0 * 88, 50, 7);
				g.setColor(Color.red);
				g.fillRect(z0.get(i).x, 80 + 0 * 88, z0.get(i).hp, 7);
				g.drawRect(z0.get(i).x, 80 + 0 * 88, z0.get(i).hp, 7);
				g.setColor(Color.black);
				if (z0.get(i).x <= 170) {
					new gamesover();
					reset();
					frm.dispose();
				}
			}
			for (int i = 0; i < z2.size(); i++) {
				if (!zombiestop[2][i]) {
					g.drawImage(normalzombie.getImage(), z2.get(i).x, 90 + 1 * 88, 70, 80, null);
				} else {
					g.drawImage(zombieeat.getImage(), z2.get(i).x, 90 + 1 * 88, 70, 80, null);
				}
				g.setColor(Color.black);
				g.drawRect(z2.get(i).x, 80 + 1 * 88, 50, 7);
				g.setColor(Color.red);
				g.fillRect(z2.get(i).x, 80 + 1 * 88, z2.get(i).hp, 7);
				g.drawRect(z2.get(i).x, 80 + 1 * 88, z2.get(i).hp, 7);
				g.setColor(Color.black);
				if (z2.get(i).x <= 170) {
					new gamesover();
					reset();
					frm.dispose();
				}
			}

			for (int i = 0; i < z3.size(); i++) {
				if (!zombiestop[3][i]) {
					g.drawImage(normalzombie.getImage(), z3.get(i).x, 90 + 2 * 88, 70, 80, null);
				} else {
					g.drawImage(zombieeat.getImage(), z3.get(i).x, 90 + 2 * 88, 70, 80, null);
				}
				g.setColor(Color.black);
				g.drawRect(z3.get(i).x, 80 + 2 * 88, 50, 7);
				g.setColor(Color.red);
				g.fillRect(z3.get(i).x, 80 + 2 * 88, z3.get(i).hp, 7);
				g.drawRect(z3.get(i).x, 80 + 2 * 88, z3.get(i).hp, 7);
				g.setColor(Color.black);
				if (z3.get(i).x <= 170) {
					new gamesover();
					reset();
					frm.dispose();
				}

			}
			for (int i = 0; i < z4.size(); i++) {
				if (!zombiestop[4][i]) {
					g.drawImage(normalzombie.getImage(), z4.get(i).x, 90 + 3 * 88, 70, 80, null);
				} else {
					g.drawImage(zombieeat.getImage(), z4.get(i).x, 90 + 3 * 88, 70, 80, null);
				}
				g.setColor(Color.black);
				g.drawRect(z4.get(i).x, 80 + 3 * 88, 50, 7);
				g.setColor(Color.red);
				g.fillRect(z4.get(i).x, 80 + 3 * 88, z4.get(i).hp, 7);
				g.drawRect(z4.get(i).x, 80 + 3 * 88, z4.get(i).hp, 7);
				g.setColor(Color.black);
				if (z4.get(i).x <= 170) {
					new gamesover();
					reset();
					frm.dispose();
				}
			}
			for (int i = 0; i < z5.size(); i++) {
				if (!zombiestop[5][i]) {
					g.drawImage(normalzombie.getImage(), z5.get(i).x, 90 + 4 * 88, 70, 80, null);
				} else {
					g.drawImage(zombieeat.getImage(), z5.get(i).x, 90 + 4 * 88, 70, 80, null);
				}
				g.setColor(Color.black);
				g.drawRect(z5.get(i).x, 80 + 4 * 88, 50, 7);
				g.setColor(Color.red);
				g.fillRect(z5.get(i).x, 80 + 4 * 88, z5.get(i).hp, 7);
				g.drawRect(z5.get(i).x, 80 + 4 * 88, z5.get(i).hp, 7);
				g.setColor(Color.black);
				if (z5.get(i).x <= 170) {
					new gamesover();
					reset();
					frm.dispose();
				}
			}
			g.drawImage(powerblue.getImage(), 225, 10, poweradd * 5, 45, null);
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

			zombie z = new zombie();
			for (int i = 0; i < p.size(); i++) {
				p.get(i).hp -= z.attack * p.get(i).hurtnum;
			}
			for (int i = 0; i < w.size(); i++) {
				w.get(i).hp -= z.attack * w.get(i).hurtnum;
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

	public class zt0 extends TimerTask {// 第一道殭屍
		@Override
		public void run() {

			if (zombienum > 12) {
				cancel();
			} else {
				z0.add(new zombie());
				zombienum++;
			}
		}
	}

	class zt5 extends TimerTask {// 第五道殭屍
		@Override
		public void run() {

			if (zombienum > 12) {
				cancel();
			} else {
				z5.add(new zombie());
				zombienum++;
			}

		}
	}

	class zt3 extends TimerTask {// 第三道殭屍
		@Override
		public void run() {

			if (zombienum > 12) {
				cancel();
			} else {
				z3.add(new zombie());
				zombienum++;
			}

		}
	}

	class zt2 extends TimerTask {// 第二道殭屍
		@Override
		public void run() {

			if (zombienum > 12) {
				cancel();
			} else {
				z2.add(new zombie());
				zombienum++;
			}

		}
	}

	class zt4 extends TimerTask {// 第四道殭屍
		@Override
		public void run() {

			if (zombienum > 12) {
				cancel();
			} else {
				z4.add(new zombie());
				zombienum++;
			}

		}
	}

	class t3 extends TimerTask {// 一般殭屍x移動

		public void run() {
			if (victorystop == false) {
				for (int i = 0; i < z0.size(); i++) {
					if (!zombiestop[0][i]) {
						z0.get(i).x--;
					}
				}
				for (int i = 0; i < z2.size(); i++) {
					if (!zombiestop[2][i]) {
						z2.get(i).x--;
					}
				}
				for (int i = 0; i < z3.size(); i++) {
					if (!zombiestop[3][i]) {
						z3.get(i).x--;
					}
				}
				for (int i = 0; i < z4.size(); i++) {
					if (!zombiestop[4][i]) {
						z4.get(i).x--;
					}
				}
				for (int i = 0; i < z5.size(); i++) {
					if (!zombiestop[5][i]) {
						z5.get(i).x--;
					}
				}
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
		public void planthitzombie() {// 子彈打殭屍
			for (int i = 0; i < p.size(); i++) {
				if (p.get(i).y < 90 + 1 * 88) {// 植物在第一排
					if (z0.size() > 0) {// 第一排有殭屍
						if (p.get(i).tbullet.size() > 0) {// 有子彈
							if (p.get(i).x + p.get(i).tbullet.get(0) > z0.get(0).x) {// 子彈x>殭屍x
								p.get(i).tbullet.remove(0);
								z0.get(0).hp -= p.get(i).attack;

							}
						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 2 * 88) {
					if (z2.size() > 0) {// 第2排有殭屍
						if (p.get(i).tbullet.size() > 0) {// 有子彈
							if (p.get(i).x + p.get(i).tbullet.get(0) > z2.get(0).x) {// 子彈x>殭屍x
								p.get(i).tbullet.remove(0);
								z2.get(0).hp -= p.get(i).attack;

							}
						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 3 * 88) {
					if (z3.size() > 0) {// 第3排有殭屍
						if (p.get(i).tbullet.size() > 0) {// 有子彈
							if (p.get(i).x + p.get(i).tbullet.get(0) > z3.get(0).x) {// 子彈x>殭屍x
								p.get(i).tbullet.remove(0);
								z3.get(0).hp -= p.get(i).attack;

							}
						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 4 * 88) {
					if (z4.size() > 0) {// 第4排有殭屍
						if (p.get(i).tbullet.size() > 0) {// 有子彈
							if (p.get(i).x + p.get(i).tbullet.get(0) > z4.get(0).x) {// 子彈x>殭屍x
								p.get(i).tbullet.remove(0);
								z4.get(0).hp -= p.get(i).attack;

							}
						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 5 * 88) {
					if (z5.size() > 0) {// 第5排有殭屍
						if (p.get(i).tbullet.size() > 0) {// 有子彈
							if (p.get(i).x + p.get(i).tbullet.get(0) > z5.get(0).x) {// 子彈x>殭屍x
								p.get(i).tbullet.remove(0);
								z5.get(0).hp -= p.get(i).attack;

							}
						}
					}
					continue;
				}

			}
		}
		public void zombiemeetpea() {// 殭屍遇到豌豆
			for (int i = 0; i < p.size(); i++) {
				p.get(i).hurtnum = 0;
				if (p.get(i).y < 90 + 1 * 88) {// 第1排
					for (int j = 0; j < z0.size(); j++) {
						if (p.get(i).x + 30 > z0.get(j).x) {// 殭屍在植物前面
							zombiestop[0][j] = true;
							p.get(i).hurtnum++;

						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 2 * 88) {// 第2排
					for (int j = 0; j < z2.size(); j++) {
						if (p.get(i).x + 30 > z2.get(j).x) {// 殭屍在植物前面
							zombiestop[2][j] = true;
							p.get(i).hurtnum++;

						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 3 * 88) {// 第3排
					for (int j = 0; j < z3.size(); j++) {
						if (p.get(i).x + 30 > z3.get(j).x) {// 殭屍在植物前面
							zombiestop[3][j] = true;
							p.get(i).hurtnum++;

						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 4 * 88) {// 第4排
					for (int j = 0; j < z4.size(); j++) {
						if (p.get(i).x + 30 > z4.get(j).x) {// 殭屍在植物前面
							zombiestop[4][j] = true;
							p.get(i).hurtnum++;

						}
					}
					continue;
				}
				if (p.get(i).y < 90 + 5 * 88) {// 第5排
					for (int j = 0; j < z5.size(); j++) {
						if (p.get(i).x + 30 > z5.get(j).x) {// 殭屍在植物前面
							zombiestop[5][j] = true;
							p.get(i).hurtnum++;

						}
					}
					continue;
				}
			}
		}

		public void zombiewalk() {
			for (int i = 0; i < z0.size(); i++) {
				zombiestop[0][i] = false;
			}
			for (int i = 0; i < z2.size(); i++) {
				zombiestop[2][i] = false;
			}
			for (int i = 0; i < z3.size(); i++) {
				zombiestop[3][i] = false;
			}
			for (int i = 0; i < z4.size(); i++) {
				zombiestop[4][i] = false;
			}
			for (int i = 0; i < z5.size(); i++) {
				zombiestop[5][i] = false;
			}
		}

		public void zombiemeetwall() {// 殭屍遇堅果
			for (int i = 0; i < w.size(); i++) {
				w.get(i).hurtnum = 0;
				if (w.get(i).y < 90 + 1 * 88) {// 第1排
					for (int j = 0; j < z0.size(); j++) {
						if (w.get(i).x + 30 > z0.get(j).x) {// 殭屍在植物前面
							zombiestop[0][j] = true;
							w.get(i).hurtnum++;
							continue;
						}
					}

				}
				if (w.get(i).y < 90 + 2 * 88) {// 第2排
					for (int j = 0; j < z2.size(); j++) {
						if (w.get(i).x + 30 > z2.get(j).x) {// 殭屍在植物前面
							zombiestop[2][j] = true;
							w.get(i).hurtnum++;
							continue;
						}
					}
				}
				if (w.get(i).y < 90 + 3 * 88) {// 第3排
					for (int j = 0; j < z3.size(); j++) {
						if (w.get(i).x + 30 > z3.get(j).x) {// 殭屍在植物前面
							zombiestop[3][j] = true;
							w.get(i).hurtnum++;
							continue;
						}
					}
				}
				if (w.get(i).y < 90 + 4 * 88) {// 第4排
					for (int j = 0; j < z4.size(); j++) {
						if (w.get(i).x + 30 > z4.get(j).x) {// 殭屍在植物前面
							zombiestop[4][j] = true;
							w.get(i).hurtnum++;
							continue;
						}
					}
				}
				if (w.get(i).y < 90 + 5 * 88) {// 第5排
					for (int j = 0; j < z5.size(); j++) {
						if (w.get(i).x + 30 > z5.get(j).x) {// 殭屍在植物前面
							zombiestop[5][j] = true;
							w.get(i).hurtnum++;
							continue;
						}
					}
				}
			}
		}
		public void run() {
			// 子彈殭屍碰撞
			planthitzombie();
			zombiewalk();
			zombiemeetpea();
			zombiemeetwall();
			// 殭屍血量為零消失
			for (int k = 0; k < z0.size(); k++) {
				if (z0.get(k).hp <= 0) {
					zombiestop[0][k] = false;
					zomdie z = new zomdie();
					z.x = z0.get(k).x;
					z.y = 90;
					zd.add(z);
					z0.remove(k);
					zombiedienum++;
				}
			}
			for (int k = 0; k < z2.size(); k++) {
				if (z2.get(k).hp <= 0) {
					zomdie z = new zomdie();
					z.x = z2.get(k).x;
					z.y = 90 + 88;
					zd.add(z);
					z2.remove(k);
					zombiestop[2][k] = false;
					zombiedienum++;
				}
			}
			for (int k = 0; k < z3.size(); k++) {
				if (z3.get(k).hp <= 0) {
					zomdie z = new zomdie();
					z.x = z3.get(k).x;
					z.y = 90 + 2 * 88;
					zd.add(z);
					zombiestop[3][k] = false;
					z3.remove(k);
					zombiedienum++;
				}
			}
			for (int k = 0; k < z4.size(); k++) {
				if (z4.get(k).hp <= 0) {
					zomdie z = new zomdie();
					z.x = z4.get(k).x;
					z.y = 90 + 3 * 88;
					zd.add(z);
					zombiestop[4][k] = false;
					z4.remove(k);
					zombiedienum++;
				}
			}
			for (int k = 0; k < z5.size(); k++) {
				if (z5.get(k).hp <= 0) {
					zomdie z = new zomdie();
					z.x = z5.get(k).x;
					z.y = 90 + 4 * 88;
					zd.add(z);
					zombiestop[5][k] = false;
					z5.remove(k);
					zombiedienum++;
				}
			}
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
		timer0.cancel();
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


