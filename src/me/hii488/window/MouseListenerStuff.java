package me.hii488.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import me.hii488.World;
import me.hii488.initialisation.GeneralInit;

public class MouseListenerStuff implements MouseListener{

	Object[] options = {"Edit Nodes", "Algorithms", "Edit Speed", "Cancel"};
	Object[] algoptions = {"Start Algorithm", "Swap Algorithm", "Cancel"};
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int i = JOptionPane.showOptionDialog(null, "What would you like to do?", "Travelling Salesman", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
		if(i == 0){
			int nodes = Integer.parseInt(JOptionPane.showInputDialog(null, "How many nodes should be shown?"));
			GeneralInit.changeNodes(nodes);
		}
		if(i == 1){
			int j = JOptionPane.showOptionDialog(null, "What would you like to do?", "Travelling Salesman", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, algoptions, algoptions[2]);
			if(j == 0){
				World.algorithmFinished = false;
			}
			if(j == 1){
				int algorithm = Integer.parseInt(JOptionPane.showInputDialog(null, "Which algorithm would you like to use?"));
				GeneralInit.changeAlgorithm(algorithm);;
			}
		}
		if(i == 2){
			Window.delay = Long.parseLong(JOptionPane.showInputDialog(null, "Current delay: " + Window.delay + "\nSet new delay"));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
