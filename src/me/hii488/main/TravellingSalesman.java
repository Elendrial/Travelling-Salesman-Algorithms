package me.hii488.main;

import me.hii488.initialisation.GeneralInit;
import me.hii488.window.Window;

public class TravellingSalesman {
	
	public static void main(String[] args){
		GeneralInit.init();
		
		System.out.println(System.currentTimeMillis());
		
		Window window = new Window("Phys2D", 1000, 800);
		window.start();
		
		
		/*
		int[] i = new int[]{0,1,2,3,4,5,6,7};
		System.out.println(Arrays.toString(i));
		i = new GeneralHelper().incrementListThing(i);
		System.out.println(Arrays.toString(i));
		*/
	}
	
}
