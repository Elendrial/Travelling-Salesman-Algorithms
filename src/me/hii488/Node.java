package me.hii488;

import java.awt.Graphics;

import me.hii488.general.Position;

public class Node {
	
	public Position position = new Position();
	public boolean visited;
	
	public final int ID;
	
	public Node(Position p){
		this.position = p;
		this.ID = World.getUnusedID();
	}
	
	private Node(Position p, int ID){
		position = p;
		this.ID = ID;
	}
	
	public void render(Graphics g){
		g.drawOval(position.getX(), position.getY(), 5, 5);
		g.drawString(""+ID, position.getX()-7, position.getY()-7);
	}
	
	public Node clone(){
		return new Node(position.clone(), ID);
	}
	
}
