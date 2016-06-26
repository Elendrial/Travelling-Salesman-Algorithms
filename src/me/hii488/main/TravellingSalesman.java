package me.hii488.main;

import me.hii488.World;
import me.hii488.initialisation.GeneralInit;
import me.hii488.window.Window;

public class TravellingSalesman {
	
	public static void main(String[] args){
		GeneralInit.init();
				
		World.window = new Window("Phys2D", 1000, 800);
		World.window.start();

	}
	
}
