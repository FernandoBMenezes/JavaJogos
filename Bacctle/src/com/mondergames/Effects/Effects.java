package com.mondergames.Effects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import com.mondergames.Entities.Entity;

public class Effects {
	EffectsType effecttype;

	public Effects(EffectsType effect) {
		this.setEffecttype(effect);
	}

	public EffectsType getEffecttype() {
		return effecttype;
	}

	public void setEffecttype(EffectsType effecttype) {
		this.effecttype = effecttype;
	}
	
	public void doAction(final Entity entity) {//TODO: final ADICIONADO POR ESTAR COMPILANDO NA VERSÃO 1.7
		entity.addEffect(this);
		Timer timer = new Timer(1000*3, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				entity.addEffect(null);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
}
