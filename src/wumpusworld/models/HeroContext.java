package wumpusworld.models;

public class HeroContext {
	
	HeroStrategy strategy;
	
	public HeroStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(HeroStrategy strategy) {
		this.strategy = strategy;
	}
	
	public HeroContext (HeroStrategy strategy){
		this.strategy = strategy;
	}
	
	public void doAction(){
		strategy.doAction();
	}

	
}
