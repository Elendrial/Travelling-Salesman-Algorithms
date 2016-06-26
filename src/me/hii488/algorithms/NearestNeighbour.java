package me.hii488.algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import me.hii488.World;
import me.hii488.general.GeneralHelper;
import me.hii488.general.Position;

public class NearestNeighbour extends GeneralAlgorithm{

	public double shortestPathLength = Float.MAX_VALUE;
	public int[] shortestPathPath = new int[World.nodes.size()];

	public int[] currentPath = new int[World.nodes.size()];
	public int currentNodesVisited = 0;
	public int startingPoint = 0;
	
	public int[] empty = new int[World.nodes.size()];
	
	public NearestNeighbour() {
		super("Nearest Neighbour");
		for(int i = 0; i < empty.length; i++){
			empty[i] = 0;
		}
		currentPath = empty.clone();
	}
	
	public void tick(){
		if(currentNodesVisited == 0){
			currentPath[0] = startingPoint;
		}
		else{
			currentPath[currentNodesVisited] = GeneralHelper.closestNode(currentPath[currentNodesVisited-1]);
		}
		
		World.getNodeFromID(currentPath[currentNodesVisited]).visited = true;
		currentNodesVisited++;
		
		if(currentNodesVisited >= World.nodes.size()){
			
			if(GeneralHelper.pathLenth(currentPath) < shortestPathLength){
				shortestPathPath = currentPath.clone();
				shortestPathLength = GeneralHelper.pathLenth(currentPath);
			}
			
			currentNodesVisited = 0;
			
			for(int i = 0; i < currentPath.length; i++){
				currentPath[i] = 0;
			}
			
			if(World.getNodeIndexFromID(startingPoint)+1 < World.nodes.size()){
				startingPoint = World.nodes.get(World.getNodeIndexFromID(startingPoint)+1).ID;
			}
			else{
				World.algorithmFinished = true;
			}
			for(int i = 0; i < World.nodes.size();i++){
				World.nodes.get(i).visited = false;
			}
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		for(int i = 0; i < currentPath.length-1; i++){
			GeneralHelper.drawLineBetweenNodes(g, currentPath[i], currentPath[i+1]);
		}
		GeneralHelper.drawLineBetweenNodes(g, currentPath[0], currentPath[currentPath.length-1]);
		
		for(int i = 0; i < shortestPathPath.length-1; i++){
			GeneralHelper.drawLineBetweenNodesOffset(g, shortestPathPath[i], shortestPathPath[i+1], Color.red, new Position(0,5));
		}
		GeneralHelper.drawLineBetweenNodesOffset(g, shortestPathPath[0], shortestPathPath[shortestPathPath.length-1], Color.red, new Position(0,5));
		
		g.drawString("Shortest Path Length: " + shortestPathLength, 5, 15);
		g.drawString("Shortest Path: " + Arrays.toString(shortestPathPath), 5, 35);
		g.drawString("Current Path : " + Arrays.toString(currentPath), 5, 55);
	}

}
