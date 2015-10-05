package wumpusworld.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Scanner;

import javax.swing.SwingWorker;

import wumpusworld.models.HeroContext;
import wumpusworld.models.World;
import wumpusworld.models.WorldSettings;
import wumpusworld.views.WorldView;

public class WorldController {
	
	private World model;
	private WorldView view;
	
	private ActionListener actionListener;
	
	public WorldController (World model, WorldView view) {
		this.model = model;
		this.view = view;
		addActionListeners();
		view.showFileChooser();
	}

	private void addActionListeners(){
		
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (view.getSelectedFile() != null){
					createWorld(view.getSelectedFile());
				}else {
					view.dispose();
				}				
				
			}
		};		
		view.getFileChooser().addActionListener(actionListener);
	}
	
	public void refresh(){
		try {
			
			int oldX = model.getHero().getLastX();
			int oldY = model.getHero().getLastY();
			int currentX = model.getHero().getX();
			int currentY = model.getHero().getY();
			
			char symbol = WorldSettings.HERO_SYMBOL;
			if (model.getHero().foundGold())
				symbol = WorldSettings.HERO_WITH_GOLD_SYMBOL;
			
				
			view.refreshWorldIcon(model.getWorldSettings()
									.getIconImagePath(symbol), currentX, currentY);
			
			
			view.refreshWorldIcon(model.getWorldSettings()
					.getIconImagePath(model.getContent(oldX, oldY)), oldX, oldY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void createWorld(File file){
		
		readFile(file);
	
		for (int i = 0; i < model.getSizeX(); i++){
			view.createWorldRow();
			for (int j = 0; j < model.getSizeY(i); j++) {
				view.createWorldIcon(model.getWorldSettings()
										  .getIconImagePath(model.getContent(i, j)),i,j);			
				
			}	
		}
		
		view.renderWorld();
		model.placeHero();
		
		refresh();
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		   @Override
		   protected Void doInBackground() throws Exception {
			   do {				
				    HeroContext heroContext = new HeroContext(model.getHero());
				   	heroContext.doAction();
					refresh();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}while (!model.isOver());
			   
			   if (model.getHero().deadByPit()){
				   view.gameOverByPit();
			   }else if (model.getHero().deadByWumpus()){
				   view.gameOverByWumpus();
			   }else {
				   view.gameOverWin();
			   }
			   
		    return null;
		   }
		  };
		  
		  worker.execute();
		
	}
		
		
		
	public Scanner setFileScanner(File file){
		try {
			Scanner scanner = new Scanner(file);
			return scanner;
			
		} catch (FileNotFoundException e){
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
			return null;
		}			
	}
		
		
	public void readFile(File file){
		Scanner scanner = setFileScanner(file);
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(file));
			lnr.skip(Long.MAX_VALUE);
			int crtLnCount = 0;
			int lineCount = lnr.getLineNumber() + 1;
		
	
			String line = scanner.nextLine();
			
			char world[][] = new char[lineCount][line.length()]; 
			
			view.setIconGrid(lineCount, line.length());
	
			
			while (line != null) {			
				line.toUpperCase();
				for (int i=0; i < line.length(); i++){
					world[crtLnCount][i] =  line.charAt(i);
				}
				
				if (scanner.hasNext()) {
					line = scanner.nextLine().toUpperCase();
					crtLnCount++;
	
				}else {
					line = null;
				}
			}
			model.setContent(world);
			
			if (!model.placeWumpus()){
				view.noSpaceForWumpus();
			}	
			
			model.completeWorld();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
