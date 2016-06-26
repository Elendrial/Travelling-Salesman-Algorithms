package me.hii488.window.gfx;

import java.awt.Canvas;
import java.awt.Graphics;

import me.hii488.World;
import me.hii488.window.Window;

@SuppressWarnings("serial")
public class Display extends Canvas{

	public Display(Window window) {
		setBounds(0, 0, window.width, window.height);
	}
	
	public void render(Graphics g){
		World.selectedAlgorithm.render(g);
		for(int i = 0; i < World.nodes.size(); i++){
			World.nodes.get(i).render(g);
		}
		g.drawString("Ticks per Second: " + World.window.currentTPS, World.window.width - 106 - (7*("" + World.window.currentTPS).length()), World.window.height-10);
		g.drawString("Delay between ticks (ms): " + World.window.delay, World.window.width - 149 - (7*("" + World.window.delay).length()), World.window.height-25);
		g.drawString("Algorithm: " + World.selectedAlgorithm.displayName, 5, World.window.height-10);
	}
	
}
