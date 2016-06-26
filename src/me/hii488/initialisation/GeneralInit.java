package me.hii488.initialisation;

import me.hii488.Node;
import me.hii488.World;
import me.hii488.algorithms.BruteForce;
import me.hii488.algorithms.GeneralAlgorithm;
import me.hii488.algorithms.NearestNeighbour;
import me.hii488.general.GeneralHelper;
import me.hii488.general.Position;

public class GeneralInit {
	
	public static int currentNodes = 0;
	public static int currentAlgorithm = 0;
	
	public static void init(){
		nodeInit(-1);
		algorithmInit(0);
	}
	
	public static void nodeInit(int limit){
		currentNodes = limit;
		
		World.nodes.add(new Node(new Position(100,100)));
		World.nodes.add(new Node(new Position(455,263)));
		World.nodes.add(new Node(new Position(174,532)));
		World.nodes.add(new Node(new Position(365,354)));
		World.nodes.add(new Node(new Position(421, 56)));
		World.nodes.add(new Node(new Position(675,100)));
		World.nodes.add(new Node(new Position(867,632)));
		World.nodes.add(new Node(new Position(532,294)));
		World.nodes.add(new Node(new Position(82 ,582)));
		World.nodes.add(new Node(new Position(8  ,734)));
		World.nodes.add(new Node(new Position(482,149)));
		World.nodes.add(new Node(new Position(918,637)));
		World.nodes.add(new Node(new Position(241,743)));
		World.nodes.add(new Node(new Position(823,524)));
		World.nodes.add(new Node(new Position(635,673)));
		World.nodes.add(new Node(new Position(472, 34)));
		World.nodes.add(new Node(new Position(78 ,235)));
		World.nodes.add(new Node(new Position(245,643)));
		World.nodes.add(new Node(new Position(963, 54)));
		World.nodes.add(new Node(new Position(543,752)));
		
		if(limit > 3){
			for(int i = World.nodes.size()-1; i >= limit; i--){
				World.nodes.remove(i);
			}
		}
		
		GeneralHelper.doNodeDistance();
	//	for(int i = 0; i < World.nodeDistances.length;i++)	System.out.println(Arrays.toString(World.nodeDistances[i]));
	}
	
	public static void algorithmInit(int selected){
		currentAlgorithm = selected;
		
		World.algorithms.add(new GeneralAlgorithm("test"));
		World.algorithms.add(new BruteForce());
		World.algorithms.add(new NearestNeighbour());
		World.selectedAlgorithm = World.algorithms.get(selected);
	}
	
	
	
	public static void addSpecificNode(Position p){
		World.nodes.add(new Node(p));
		GeneralHelper.doNodeDistance();
	}
	
	public static void removeSpecificNode(int ID){
		int index = -1;
		for(int i = 0; i < World.nodes.size(); i++){
			if(World.nodes.get(i).ID == ID) index = i;
		}
		World.nodes.remove(index);
		GeneralHelper.doNodeDistance();
	}
	
	public static void changeNodes(int limit){
		World.nodes.clear();
		nodeInit(limit);
	}
	
	public static void changeAlgorithm(int selected){
		World.algorithms.clear();
		algorithmInit(selected);
	}
	
}
