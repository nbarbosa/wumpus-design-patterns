package wumpusworld.models;

public abstract class HeroStrategy  {
	
	private int x, y, lastX, lastY;
	private int facingAngle = 0;
	World world;
	boolean alive = true;
	boolean gotGold = false;
	boolean fellIntoPit = false;
	boolean ranIntoWumpus = false;
	
	public int getLastX() {
		return lastX;
	}
	
	public int getLastY() {
		return lastY;
	}	
	
	public int getX() {
		return x;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public boolean foundGold(){
		return gotGold;
	}
	
	public boolean leftWithGold(){
		return this.world == null && gotGold;
	}
	
	public int getY() {
		return y;
	}

	public void turnLeft(){
		System.out.println("Turning left");
		facingAngle += 90;
	}
	
	public void enterWorld(World world){
		this.world = world;		
		this.x = world.getStartX();
		this.y = world.getStartY();
	}
	
	public void turnRight(){
		System.out.println("Turning right");
		facingAngle -= 90;
	}
	
	public boolean deadByPit (){
		return fellIntoPit;
	}
	
	public boolean deadByWumpus (){
		return ranIntoWumpus;
	}
	
	public void forward(){		
		 int deltaX;
	     int deltaY;	
		 deltaX = (int) Math.cos(Math.toRadians(facingAngle));
         deltaY = (int) Math.sin(Math.toRadians(facingAngle));
         if (legalPosition(x + deltaX, y + deltaY)) {
        	 lastX = x;
        	 lastY = y;
             x += deltaX;
             y += deltaY;
             if (world.hasWumpus(x,y)) {
                 this.alive = false;
                 this.ranIntoWumpus = true;
             }
             else if (world.hasPit(x,y)) {
                 this.alive = false;
                 this.fellIntoPit = true;
             }
             else if (world.isStart(x, y) && gotGold) {
                 this.world = null;
             }
             else if (world.hasGold(x, y)){
            	 world.setContent(x, y, WorldSettings.EMPTY_SYMBOL);
            	 this.gotGold = true;
             }
         }
         System.out.println("Last X:" + lastX + " Last Y:" + lastY + " X: " + x+" Y: "+y);
	}
	
	public void doAction(){
		
		
	}	
	
	private boolean legalPosition(int x, int y) {
        if (x < 0) return false;
        if (y < 0) return false;
        if (x > (this.world.getSizeXNoWalls())) return false;
        if (y > (this.world.getSizeYNoWalls(x))) return false;
        if (facingWall(x, y)) {
        	turnLeft();
        	return false;
        }
        
        return true;
    }
	
	protected boolean facingWall(int x, int y){
		return this.world.isWall(x, y);
	}
	
	
}
