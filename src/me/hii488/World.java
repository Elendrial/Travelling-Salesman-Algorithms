package me.hii488;

import java.util.ArrayList;
import java.util.Random;

import me.hii488.algorithms.GeneralAlgorithm;
import me.hii488.window.Window;

public class World {
	
	// TODO: Stop relying on ID, but on index if possible.
	
	public static Window window;
	
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	
	public static double[][] nodeDistances;
	
	public static ArrayList<GeneralAlgorithm> algorithms = new ArrayList<GeneralAlgorithm>();
	public static GeneralAlgorithm selectedAlgorithm;
	
	public static boolean algorithmFinished = true;
	public static boolean paused = false;
	
	public static Random rand = new Random();
	
	// NODE STUFF
	public static int getUnusedID(){
		int nextPossible = 0;
		boolean zeroIncluded = false;
		
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).ID >= nextPossible){
				nextPossible = nodes.get(i).ID + 1;
			}
			if(nodes.get(i).ID == 0){
				zeroIncluded = true;
			}
		}
		if(!zeroIncluded) return 0; // 0 is where all algorithms start from, if there ain't a 0, shit's gonna go wrong
		return nextPossible;
	}
	
	public static Node getNodeFromID(int ID){
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).ID == ID) return nodes.get(i);
		}
		return null;
	}
	
	public static int getNodeIndexFromID(int ID){
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).ID==ID)return i;
		}
		return 0;
	}
	
	
	// ALGORITHM STUFF
	public static void tickAlgorithm(){
		selectedAlgorithm.tick();
	}
	
}
