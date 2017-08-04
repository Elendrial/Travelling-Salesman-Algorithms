package me.hii488.algorithms;

import java.awt.Graphics;
import java.util.ArrayList;

import me.hii488.Node;
import me.hii488.World;
import me.hii488.general.Connection;
import me.hii488.general.GeneralHelper;

public class Christofides extends GeneralAlgorithm{
	
	public ArrayList<Connection> connections, hamiltonConnections;
	public ArrayList<Node> visitedNodes;
	public int position = 0;
	public int phase = 0;
	
	public Christofides() {
		super("Christofides' Algorithm");
		connections = new ArrayList<Connection>();
		hamiltonConnections = new ArrayList<Connection>();
		visitedNodes = new ArrayList<Node>();
	}
	
	// Phases are separated out so that it can be slowed down & so it is more readable.
	public void tick(){
		
		if(phase == 3){ // Turn the Eulerian Path into a Hamilton Cycle
			boolean completed = false;
			Node nodeA = connections.get(position).nodeA;
			if(!visitedNodes.contains(nodeA)) visitedNodes.add(nodeA);
			
			do{
				if(!visitedNodes.contains(connections.get(position).nodeB)){
					hamiltonConnections.add(new Connection(nodeA, connections.get(position).nodeB));
					visitedNodes.add(connections.get(position).nodeB);
					completed = true;
				}
				position++;
			}while(!completed);
			
			if(position >= connections.size()-2){
				position = 0;
				connections = hamiltonConnections;
				hamiltonConnections = new ArrayList<Connection>();
				
				completed = true;
				ArrayList<Node> nodes = new ArrayList<Node>();
				for(Connection c : connections){
					if(nodes.contains(c.nodeB)) completed = false;
					nodes.add(c.nodeB);
				}
				
				if(completed) phase++;
			}
		}
		
		if(phase == 2){ // Turn the minimum spanning tree into an Eulerian Path.
			connections.remove(0); // TODO: This is because the 0th is always erroneous, this should be fixed in an earlier stage.
			
			int size = connections.size();
			
			for(int i = 0; i < size; i++) connections.add(connections.get(i).reverse());
			
			ArrayList<Connection> eulerianConnections = new ArrayList<Connection>();
			
			boolean found;
			int connectionAmount;
			Connection backupConnection = new Connection();

			eulerianConnections.add(connections.get(0));
			connections.remove(0);
			
			connectionAmount = connections.size();
			
			for(int i = 0; i < connectionAmount; i++){
				found = false;

				for(int j = 0; j < connections.size() && !found; j++){
					if(connections.get(j).nodeA.equals(eulerianConnections.get(i).nodeB)){
						if(!connections.get(j).nodeB.equals(eulerianConnections.get(i).nodeA)){
							found = true;
							eulerianConnections.add(connections.get(j));
							connections.remove(j);
						}
						else{
							backupConnection = connections.get(j);
						}
					}
				}
				
				if(!found){
					eulerianConnections.add(backupConnection);
					connections.remove(backupConnection);
				}
			}
			
			connections = eulerianConnections;
			position = 0;
			phase++;
		}
		
		if(phase == 1){ // Make the minimum spanning tree.
			connections.add(GeneralHelper.getClosestInConnectionList(GeneralHelper.nearestToEachNodeInConnection(connections)));
			
			if(connections.size() == World.nodes.size()) phase++;
		}
		
		if(phase == 0){
			Node[] closestNodes = GeneralHelper.getClosestNodes();
			connections.add(new Connection(closestNodes[0], closestNodes[1]));
			
			phase++;
		}
		
	}

	public void render(Graphics g){
		for(Connection c : connections) c.render(g);
		for(Connection c : hamiltonConnections) c.render(g);
	}
	
}
