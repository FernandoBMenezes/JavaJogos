package com.mondergames.Sounds;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

/**
 * @author Fernando B.M(Monderdragon)
 * @Examples
 * @URL URL url1 = this.getClass().getResource("/Sounds/Limpado.wav");
 * @Sound Sound sound1 = new Sound(url1);
 * @sound1 sound1.play();
 */
public class Sound {
	private URL local;
	private float volume;
	private Clip clip;

	/**
	 * 
	 * @param url
	 *            used to get audio for play
	 */
	public Sound(URL url) {
		this.setLocal(url);
		this.setVolume(0.0f);
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param url
	 *            used to pick up the location of the audio
	 * @param volume
	 *            used to set Volume
	 */
	public Sound(URL url, float volume) {
		this.setLocal(url);
		this.setVolume(volume);
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/** For play that sound */
	public void play() {
		try {
			if(clip.isOpen())clip.close();
			AudioInputStream ais = AudioSystem.getAudioInputStream(local);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** For stop that sound */
	public void stop() {
		clip.stop();
	}
	
	public boolean running() {
		return clip.isActive();
	}

	/** @return the local */
	public URL getLocal() {
		return this.local;
	}

	/**
	 * @param local2
	 *            the local to set
	 */
	public void setLocal(URL local2) {
		this.local = local2;
	}

	/** @return the volume */
	public float getVolume() {
		return this.volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
}
