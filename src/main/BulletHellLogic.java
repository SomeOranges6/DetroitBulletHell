package main;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import main.gameplay.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BulletHellLogic {

	public ArrayList<EntityBase> entitiesToRender;
	public ArrayList<IUpdatable> entitiestoUpdate;
	public static int tick = 0;
	Random rand = new Random();
	public static Player player;

	public Timer centralTick = new Timer(50, new CentralClock());

	public static void main(String[] args) {
		SwingUtilities.invokeLater(BulletHellLogic::new);
	}

	BulletHellLogic(){

	}

	static class CentralClock implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tick++ == Integer.MAX_VALUE){
				tick = 0;
			}
		}
	}
}
