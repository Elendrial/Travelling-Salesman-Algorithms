package me.hii488.algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import me.hii488.World;
import me.hii488.general.GeneralHelper;
import me.hii488.window.Window;

public class BruteForce extends GeneralAlgorithm{

	public double shortestPathLength = Float.MAX_VALUE;
	public int[] shortestPathPath = new int[World.nodes.size()];
	
	public int[] currentPath = new int[World.nodes.size()];
	
	
	
	public BruteForce() {
		super("Brute force");
		for(int i = 0; i < currentPath.length; i++){
			currentPath[i] = World.nodes.get(i).ID;
			shortestPathPath[i] = World.nodes.get(i).ID;
		}
		Arrays.sort(currentPath);
		shortestPathLength = GeneralHelper.pathLenth(currentPath);
	}
	
	
	public void tick(){
		currentPath = GeneralHelper.incrementListThing(currentPath);
		double pathLength = GeneralHelper.pathLenth(currentPath);
		//System.out.println("Tick");
		if(pathLength < shortestPathLength){
			shortestPathLength = pathLength;
			shortestPathPath = currentPath.clone();
			System.out.println("shorter found");
		}
		 World.algorithmFinished = (currentPath[0] != 0);
		 if(World.algorithmFinished) System.out.println(System.currentTimeMillis());
	}
	
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		for(int i = 0; i < currentPath.length-1; i++){
			GeneralHelper.drawLineBetweenNodes(g, currentPath[i], currentPath[i+1]);
		}
		GeneralHelper.drawLineBetweenNodes(g, currentPath[0], currentPath[currentPath.length-1]);
		
		for(int i = 0; i < shortestPathPath.length-1; i++){
			GeneralHelper.drawLineBetweenNodes(g, shortestPathPath[i], shortestPathPath[i+1], Color.red);
		}
		GeneralHelper.drawLineBetweenNodes(g, shortestPathPath[0], shortestPathPath[shortestPathPath.length-1], Color.red);
		
		g.drawString("Shortest Path Length: " + shortestPathLength, 5, 15);
		g.drawString("Shortest Path: " + Arrays.toString(shortestPathPath), 5, 35);
		g.drawString("Current Path : " + Arrays.toString(currentPath), 5, 55);
		g.drawString("Per sec: " + Window.currentFPS, 5, 75);
	}
	
}
