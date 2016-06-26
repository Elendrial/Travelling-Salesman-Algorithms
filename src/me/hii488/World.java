package me.hii488;

import java.util.ArrayList;

import me.hii488.algorithms.GeneralAlgorithm;

public class World {
	
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	
	public static double[][] nodeDistances;
	
	public static ArrayList<GeneralAlgorithm> algorithms = new ArrayList<GeneralAlgorithm>();
	public static GeneralAlgorithm selectedAlgorithm;
	
	public static boolean algorithmFinished = true;
	
	// NODE STUFF
	public static int getUnusedID(){
		int nextPossible = 0;
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).ID >= nextPossible){
				nextPossible = nodes.get(i).ID + 1;
			}
		}
		return nextPossible;
	}
	
	
	
	// ALGORITHM STUFF
	public static void tickAlgorithm(){
		selectedAlgorithm.tick();
	}
	
	public static Node getNodeFromID(int ID){
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).ID == ID) return nodes.get(i);
		}
		return null;
	}
	
}
