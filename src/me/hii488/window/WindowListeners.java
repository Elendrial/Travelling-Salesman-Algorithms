package me.hii488.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import me.hii488.World;
import me.hii488.general.Position;
import me.hii488.initialisation.GeneralInit;

public class WindowListeners implements MouseListener, KeyListener{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		doMainMenu();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE || arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			doMainMenu();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_P){
			World.paused = !World.paused;
			
		}
	}

	
	public void doMainMenu(){
		doOptionsMenu();
	}
	
	public void doOptionsMenu(){
		Object[] options = {"Nodes", "Algorithms", "Tick delay", "Cancel"};
		
		int initial = JOptionPane.showOptionDialog(null, "What would you like to change?", "TSP Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
		
		// NODES
		if(initial == 0){
			Object[] nodeOptions = {"Add/Remove", "Reset to Preset", "Cancel"};
			
			int node = JOptionPane.showOptionDialog(null, "What would you like to do?", "TSP Node Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nodeOptions, nodeOptions[2]);
			
			if(node == 0){
				Object[] nodeOptions2 = {"Add", "Remove", "Remove All", "Cancel"};
				int node2 = JOptionPane.showOptionDialog(null, "What would you like to do?", "TSP Node Options 2", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nodeOptions2, nodeOptions2[3]);
				
				if(node2 == 0){
					String s = JOptionPane.showInputDialog(null, "What are the coordinates of this point?\n(1 pixel = 1 unit, format: \"(x1, y1) - (x2, y2)...\" or \"(rand)\")");
					if(s!=null){
						for(int i = 0; i < s.split("-").length; i++){
							String s2 = s.split("-")[i].replace("(", " ").replace(")", " ").trim();
							if(s2.contains("rand"))	GeneralInit.addSpecificNode(new Position(World.rand.nextInt(World.window.width), World.rand.nextInt(World.window.height)));
							else GeneralInit.addSpecificNode(new Position(Float.parseFloat(s.trim().split(",")[0]),World.window.height - Float.parseFloat(s.trim().split(",")[1])));
						}
					}
				}
				
				if(node2 == 1){
					String s = JOptionPane.showInputDialog(null, "What are the Node IDs?\n(Format: ID1, ID2...");
					for(int i = 0; i < s.split(",").length; i++){
						GeneralInit.removeSpecificNode(Integer.parseInt(s.split(",")[i].trim()));
					}
				}
				if(node2 == 2){
					World.nodes.clear();
				}
			}
			
			if(node == 1){
				int nodes = Integer.parseInt(JOptionPane.showInputDialog(null, "How many nodes should be shown?"));
				GeneralInit.changeNodes(nodes);
			}
		}
		
		// ALGORITHMS
		if(initial == 1){
			Object[] algoptions = {"Start Algorithm", "Swap Algorithm", "Cancel"};
			int alg = JOptionPane.showOptionDialog(null, "What would you like to do?", "TSP Algorithm Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, algoptions, algoptions[2]);
			if(alg == 0){
				World.algorithmFinished = false;
			}
			if(alg == 1){
				int algorithm = Integer.parseInt(JOptionPane.showInputDialog(null, "Which algorithm would you like to use?"));
				GeneralInit.changeAlgorithm(algorithm);
			}
		}
		
		// DELAY
		if(initial == 2){
			World.window.delay = Long.parseLong(JOptionPane.showInputDialog(null, "Current delay: " + World.window.delay + "\nSet new delay"));
		}
		
	}
	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
