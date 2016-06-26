package me.hii488.general;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import me.hii488.World;

public class GeneralHelper {
	
	// LIST ORDERS
	public static int[] incrementListThing(int[] currentOrder){
		int[] out = currentOrder;
		
		// From right to left
		for(int i = currentOrder.length-1; i > 0; i--){
			// finds lowest out-of-order pair
			if(currentOrder[i]>currentOrder[i-1]){
				int left = currentOrder[i-1];
				int lowest = Integer.MAX_VALUE;
				int position = 0;
				for(int j = i; j < currentOrder.length; j++){
					if(currentOrder[j] < lowest && currentOrder[j] > left){
						lowest = currentOrder[j];
						position = j;
					}
				}
				
				// If lowest is the right of the pair.
				if(lowest == currentOrder[i]){
					out[i-1] = currentOrder[i];
					out[i] = left;
				}
				else{
					out[i-1] = lowest;
					out[position] = left;
					out = sortListFromPoint(out, i);
				}
				
				return out;
			}
		}
		return out;
	}
	
	public static int[] sortListFromPoint(int[] inc, int point){
		if(point>=inc.length){
			return inc;
		}
		
		int[] mid = new int[inc.length-point];
		
		for(int i = 0; i < mid.length; i++){
			mid[i] = inc[i+point];
		}
		
		Arrays.sort(mid);
		
		int[] out = inc;
		
		for(int i = 0; i < mid.length; i++){
			out[i+point] = mid[i];
		}
		
		return out;
	}
	
	
	// PATH THINGS
	public static double pathLenth(int[] nodeOrder){
		double totalDist = 0;
		for(int i = 0; i < nodeOrder.length-1; i++){
			totalDist += distBetweenNodesID(nodeOrder[i], nodeOrder[i+1]);
		}
		totalDist += distBetweenNodesID(nodeOrder[0], nodeOrder[nodeOrder.length-1]);
		
		return totalDist;
	}
	
	public static double distBetweenNodesID(int nodeA, int nodeB){
		Position a = World.getNodeFromID(nodeA).position;
		Position b = World.getNodeFromID(nodeB).position;
		
		return  Math.sqrt(Math.abs(Math.pow(a.getAbsX() - b.getAbsX(), 2) + Math.pow(a.getAbsY() - b.getAbsY(), 2)));
	}
	
	public static double distBetweenNodes(int nodeA, int nodeB){
		Position a = World.nodes.get(nodeA).position;
		Position b = World.nodes.get(nodeB).position;
		
		return  Math.sqrt(Math.abs(Math.pow(a.getAbsX() - b.getAbsX(), 2) + Math.pow(a.getAbsY() - b.getAbsY(), 2)));
	}
	
	public static int closestNode(int i) {
		
		double shortestFound = Integer.MAX_VALUE;
		int nodeValue = -1;
		
		for(int j = 0; j < World.nodeDistances[i].length; j++){
			if(i!=j){
				if(World.nodeDistances[i][j] < shortestFound && !World.nodes.get(j).visited){
					shortestFound = World.nodeDistances[i][j];
					nodeValue = j;
				}
			}
		}
		
		return nodeValue;
	}
	
	public static void doNodeDistance(){
		World.nodeDistances = new double[World.nodes.size()][World.nodes.size()];
		for(int i = 0; i < World.nodes.size(); i++){
			for(int j = 0; j < World.nodes.size(); j++){
				if(i!=j)World.nodeDistances[i][j] = distBetweenNodes(i,j);
				else World.nodeDistances[i][j] = -1;
			}
		}
	}
	
	public static Connection[] nearestToEachNodeInConnection(Connection[] connection) {
		
		return null;
	}
	
	public static Connection getClosest(Connection[] c){
		return null;
	}
	
	
	// RENDER THINGS
	public static void drawLineBetweenNodes(Graphics g, int nodeA, int nodeB){
		drawLineBetweenNodes(g, nodeA, nodeB, Color.black);
	}
	
	public static void drawLineBetweenNodes(Graphics g, int nodeA, int nodeB, Color c){
	//	System.out.println("NodeA:" + nodeA);
		Position a = World.getNodeFromID(nodeA).position;
		Position b = World.getNodeFromID(nodeB).position;
		Color before = g.getColor();
		g.setColor(c);
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
		g.setColor(before);
	}
	
	public static void drawLineBetweenNodesOffset(Graphics g, int nodeA, int nodeB, Position offset){
		drawLineBetweenNodesOffset(g, nodeA, nodeB, Color.black, offset);
	}
	
	public static void drawLineBetweenNodesOffset(Graphics g, int nodeA, int nodeB, Color c, Position offset){
	//	System.out.println("NodeA:" + nodeA);
		Position a = World.getNodeFromID(nodeA).position;
		Position b = World.getNodeFromID(nodeB).position;
		Color before = g.getColor();
		g.setColor(c);
		g.drawLine(a.getX() + offset.getX(), a.getY() + offset.getY(), b.getX() + offset.getX(), b.getY() + offset.getY());
		g.setColor(before);
	}
	
	
}
