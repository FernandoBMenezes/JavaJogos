package com.mondergames.Sounds;

import java.net.URL;

public class Sounds {
	public void shotSound() {
		URL url1 = this.getClass().getResource("/Resources_Sounds/shot.wav");
		Sound sound1 = new Sound(url1);
		sound1.setVolume(-20);
		sound1.play();
	}
	public void pageSound() {
		URL url2 = this.getClass().getResource("/Resources_Sounds/page.wav");
		Sound sound2 = new Sound(url2);
		sound2.setVolume(-20);
		sound2.play();
	}
	public void entredSound() {
		URL url3 = this.getClass().getResource("/Resources_Sounds/page.wav");
		Sound sound3 = new Sound(url3);
		sound3.setVolume(-40);
		sound3.play();
	}
}
