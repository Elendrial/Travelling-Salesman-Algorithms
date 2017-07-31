package me.hii488.general;

import java.awt.Color;
import java.awt.Graphics;

import me.hii488.Node;

public class Connection {
	public Node nodeA;
	public Node nodeB;
	private float length = Float.MAX_VALUE;
	
	public Connection(){}
	
	public Connection(Node nodeA, Node nodeB){
		setNodes(nodeA, nodeB);
	}
	
	public float getLength(){
		return length;
	}
	
	public void setNodes(Node nodeA, Node nodeB){
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		length = (float) GeneralHelper.distBetweenNodes(nodeA, nodeB);
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(nodeA.position.getX(), nodeA.position.getY(), nodeB.position.getX(), nodeB.position.getY());
	}
	
	public Connection clone(){
		return new Connection(nodeA, nodeB);
	}
	
	public Connection reverse(){
		return new Connection(nodeB, nodeA);
	}
	
	public String toString(){
		return "A: " + nodeA.ID + ";\tB: " + nodeB.ID;
	}
}
