package com.mondergames;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyBoard implements KeyListener {
	public static Set<Integer> keys = new HashSet<Integer>();

	@Override
	public synchronized void keyTyped(KeyEvent e) {
		if (e.getID() == KeyEvent.VK_ESCAPE) {
			keys.add(e.getKeyCode());
		}
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		if (!(e.getID() == KeyEvent.VK_ESCAPE)) {
			keys.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.remove(e.getKeyCode());
	}
}
