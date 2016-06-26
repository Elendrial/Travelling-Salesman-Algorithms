package me.hii488.algorithms;

import me.hii488.general.Connection;
import me.hii488.general.GeneralHelper;

public class Christofides extends GeneralAlgorithm{

	public Connection[] connection;
	public int position = 0;
	
	public int phase = 0;
	
	public Christofides() {
		super("Christofides' Algorithm");
	}
	
	public void tick(){
		if(phase == 0){
			connection[position] =  GeneralHelper.getClosest(GeneralHelper.nearestToEachNodeInConnection(connection));
			position++;
		}
	}

}
