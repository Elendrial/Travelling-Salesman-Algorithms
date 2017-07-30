package me.hii488.general;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import me.hii488.Node;
import me.hii488.World;

public class GeneralHelper {
	
	// LIST ORDERS
	// I'm kinda proud of this I'm not going to lie :3
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
	public static double distBetweenNodes(Node nodeA, Node nodeB){
		Position a = nodeA.position;
		Position b = nodeB.position;
		
		return  Math.sqrt(Math.abs(Math.pow(a.getAbsX() - b.getAbsX(), 2) + Math.pow(a.getAbsY() - b.getAbsY(), 2)));
	}
	
	public static int closestNode(int i) {
		
		double shortestFound = Integer.MAX_VALUE;
		int nodeValue = -1;
		
		for(int j = 0; j < World.nodeDistances[World.getNodeIndexFromID(i)].length; j++){
			if(i!=j){
				if(World.nodeDistances[World.getNodeIndexFromID(i)][j] < shortestFound && !World.nodes.get(j).visited){
					shortestFound = World.nodeDistances[World.getNodeIndexFromID(i)][j];
					nodeValue = World.nodes.get(j).ID;
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
	
	// TODO: Improve this algorithm.
	/* This returns a list of connections between each node in the list of connections and it's nearest node not in the connections.*/
	public static Connection[] nearestToEachNodeInConnection(ArrayList<Connection> connections) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Connection[] nearestNeighbours;
		Connection shortest;
		
		for(Connection c : connections){
			if(!nodes.contains(c.nodeA)) nodes.add(c.nodeA);
			if(!nodes.contains(c.nodeB)) nodes.add(c.nodeB);
		}
		
		nearestNeighbours = new Connection[nodes.size()];
		Node n;
		for(int i = 0; i < nodes.size(); i++){
			
			n = nodes.get(i);
			shortest = new Connection();
			
			for(Node n2 : World.nodes) // If this is really slow, change this to only calc the dist once.
				if(!nodes.contains(n2))
					if(GeneralHelper.distBetweenNodes(n, n2) < shortest.getLength())
						shortest.setNodes(n, n2);
			
			nearestNeighbours[i] = shortest;
		}
		
		return nearestNeighbours;
	}
	
	public static Connection getClosest(Connection[] c){
		Connection shortest = new Connection();
		
		for(Connection c2: c)
			if(c2.getLength() < shortest.getLength())
				shortest = c2;
		
		return shortest;
	}
	
	
	// RENDER THINGS
	public static void drawLineBetweenNodes(Graphics g, int nodeA, int nodeB){
		drawLineBetweenNodes(g, nodeA, nodeB, Color.black);
	}
	
	public static void drawLineBetweenNodes(Graphics g, int nodeA, int nodeB, Color c){
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
		Position a = World.getNodeFromID(nodeA).position;
		Position b = World.getNodeFromID(nodeB).position;
		Color before = g.getColor();
		g.setColor(c);
		g.drawLine(a.getX() + offset.getX(), a.getY() + offset.getY(), b.getX() + offset.getX(), b.getY() + offset.getY());
		g.setColor(before);
	}
	
	
}
