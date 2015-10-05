package wumpusworld;

import javax.swing.SwingUtilities;

import wumpusworld.controllers.WorldController;
import wumpusworld.models.World;
import wumpusworld.models.WorldSettings;
import wumpusworld.views.WorldView;


public class Main {

	 public static void main(String[] args) {    
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {                                           
	                World model = new World(new WorldSettings());
	                WorldView view = new WorldView();
	                new WorldController(model,view);
	            }
	        });  
	    }	

}
