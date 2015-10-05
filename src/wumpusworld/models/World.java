package wumpusworld.models;

import wumpusworld.Helper;


public class World {
	
	private WorldSettings worldSettings;
	private char[][] content;
	private HeroStrategy hero;
	

	private int startX;
	private int startY;
	
	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	
	public World(WorldSettings worldSettings){
		this.worldSettings = worldSettings;
	}
			
	public int getSizeX() {
		return content.length;
	}
	
	public int getSizeY(int x) {
		return content[x].length;
	}
	
	public HeroStrategy getHero() {
		return this.hero;
	}	

	
	public boolean isOver(){
		return !this.hero.isAlive() || this.hero.leftWithGold();
	}
	
	
	public int getSizeXNoWalls() {
		// not to consider walls
		return content.length -1;
	}
	
	public int getSizeYNoWalls(int x) {
		// not to consider walls
		return content[x].length -1;
	}

	public void setContent(char[][] content) {
		this.content = content;
	}
	
	public char getContent(int x, int y) {
		return content[x][y];
	}
	
	public void setContent(int x, int y, char c) {
		this.content[x][y] = c;
	}
	
	public WorldSettings getWorldSettings() {
		return worldSettings;
	}

	public void setWorldSettings(WorldSettings worldSettings) {
		this.worldSettings = worldSettings;
	}	
	
	public boolean hasPit(int x, int y){
		return content[x][y] == WorldSettings.PIT_SYMBOL;
	}
	
	public boolean hasStench(int x, int y){
		return content[x][y] == WorldSettings.STENCH_SYMBOL;
	}
	
	public boolean hasBreeze(int x, int y){
		return content[x][y] == WorldSettings.STENCH_SYMBOL;
	}
	
	public boolean isStart(int x, int y){
		return content[x][y] == WorldSettings.START_SYMBOL;
	}
	
	public boolean isWall(int x, int y){
		return content[x][y] == WorldSettings.WALL_SYMBOL;
	}
	
	public boolean hasWumpus(int x, int y){
		return content[x][y] == WorldSettings.WUMPUS_SYMBOL;
	}
	
	public boolean hasGold(int x, int y){
		return content[x][y] == WorldSettings.GOLD_SYMBOL;
	}
	
	public boolean isEmpty(int x, int y){
		return content[x][y] == WorldSettings.EMPTY_SYMBOL;
	}
	
	public boolean hasBreezeOrStench(int x, int y){
		return content[x][y] == WorldSettings.BREEZE_STENCH_SYMBOL;
	}	

	public boolean placeWumpus(){
		boolean wumpusIn = false;
		int trials = 0;
		
		while (!wumpusIn) {	
			// 1's - Because of walls
			int x = Helper.random(1, getSizeXNoWalls());			
			int y = Helper.random (1, getSizeYNoWalls(x));
			
			if (isEmpty(x, y)){
				setContent(x, y, WorldSettings.WUMPUS_SYMBOL);
				wumpusIn = true;
			}else {
				trials++;
			}		
			
			if (trials >= 5){
				// not to consider walls
				for (int i = 1; i < getSizeXNoWalls(); i++){				
					for (int j = 1; j < getSizeYNoWalls(i); j++) {
						if (isEmpty(i, j)){
							setContent(i, j, WorldSettings.WUMPUS_SYMBOL);
							wumpusIn = true;
							break;
						}
						
					}
					if (wumpusIn){
						break;
					}
				}
				if (!wumpusIn){
					break;
				}
			}
		}
		
		return wumpusIn;		
	}
	
	public void completeWorld(){
		for (int x = 0; x< getSizeXNoWalls(); x++){
			for (int y = 0; y < getSizeYNoWalls(x);y++){
				if (hasWumpus(x, y) || hasPit(x, y))
					addAdjacentElem(x, y);
			}
		}		
	}
	
	public void findStart(){
		for (int x = 0; x < getSizeXNoWalls(); x++){
			for (int y = 0; y < getSizeYNoWalls(x); y++){
				if (isStart(x, y)){
					startX = x;
					startY = y;		
				}
			}
		}		
	}
	
	public void placeHero(){
		findStart();

		HeroStrategy hero = new ForwardHeroStrategy();
	
		hero.enterWorld(this);
		
		this.hero = hero;		
	}
	
	public void addAdjacentElem(int x, int y){
		
		char ac = ' ';
		if (hasPit(x,y)){
			ac = WorldSettings.BREEZE_SYMBOL;
		}else if (hasWumpus(x,y)){
			ac =  WorldSettings.STENCH_SYMBOL;
		}
		
		for (int i = x-1; i <= x+1; i++ ){
			for (int j = y-1; j <= y+1; j++){	
				// only if not diagonal
				if (x == i || y == j) {
					if (isEmpty(i,j)){
						setContent(i, j, ac);
					}else if (hasStench(i, j) || hasBreeze(i, j)){
						setContent(i, j, WorldSettings.BREEZE_STENCH_SYMBOL);
					}	
				}
			}				
		}		
	}
	
}
