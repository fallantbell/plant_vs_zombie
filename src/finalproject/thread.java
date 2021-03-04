package finalproject;

import javax.swing.*;

public class thread {
	JPanel panel;
	public thread(JPanel panel) {
		this.panel=panel;
	}
	public void run() {
		while (levelmodel.threadrun||level2test.threadrun) {
			panel.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			}
	}
}


