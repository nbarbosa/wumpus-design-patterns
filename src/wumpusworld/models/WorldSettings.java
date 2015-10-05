package wumpusworld.models;

import java.util.HashMap;
import java.util.Map;

public class WorldSettings {
	
	private final String IMAGE_PATH  = "img/";
	
	public static final char WUMPUS_SYMBOL = 'W';
	public static final char PIT_SYMBOL    = 'P';
	public static final char BREEZE_SYMBOL  = '~';
	public static final char STENCH_SYMBOL  = 'A';
	public static final char START_SYMBOL   = 'S';
	public static final char HERO_SYMBOL   = 'H';
	public static final char HERO_WITH_GOLD_SYMBOL = 'F';
	public static final char GOLD_SYMBOL   = 'G';
	public static final char EMPTY_SYMBOL = '.';
	public static final char WALL_SYMBOL = '#';
	public static final char BREEZE_STENCH_SYMBOL   = 'Ã';
	
	private final String WUMPUS_NAME = "wumpus";
	private final String PIT_NAME    = "pit";
	private final String BREEZE_NAME = "breeze";
	private final String STENCH_NAME = "stench";
	private final String GOLD_NAME   = "gold";
	private final String START_NAME   = "start";
	private final String HERO_NAME   = "hero";
	private final String HERO_WITH_GOLD_NAME   = "hero_with_gold";
	private final String EMPTY_NAME  = "empty";
	private final String WALL_NAME   = "wall";
	private final String BREEZE_STENCH_NAME = "breeze_stench";
	

	
	
	private final Map<String, String> images = new HashMap<String, String>()
		{
			{
				put(WUMPUS_NAME,"wumpus.png");
				put(STENCH_NAME,"stench.png");
				put(PIT_NAME,"pit.png");
				put(BREEZE_NAME,"breeze.png");					
				put(GOLD_NAME,"gold.png");
				put(START_NAME,"start.png");
				put(HERO_NAME,"hero.png");
				put(HERO_WITH_GOLD_NAME,"hero_with_gold.png");
				put(EMPTY_NAME,"empty.png");
				put(WALL_NAME,"wall.png");
				put(BREEZE_STENCH_NAME,"breeze_stench.png");
			}
																					   
		};
	
	public Map<String, String> getImages() {
		return images;
	}
	
	public String getIconImagePath(char c){
		String icon = null;
		switch (c){
			case WALL_SYMBOL:
				icon = WALL_NAME;
				break;
			case EMPTY_SYMBOL:
				icon = EMPTY_NAME;
				break;
			case GOLD_SYMBOL:
				icon = GOLD_NAME;
				break;
			case PIT_SYMBOL :
				icon = PIT_NAME;
				break;
			case HERO_SYMBOL:
				icon = HERO_NAME;
				break;
			case HERO_WITH_GOLD_SYMBOL:
				icon = HERO_WITH_GOLD_NAME;
				break;
			case START_SYMBOL:
				icon = START_NAME;
				break;
			case WUMPUS_SYMBOL:
				icon = WUMPUS_NAME;
				break;
			case BREEZE_SYMBOL:
				icon = BREEZE_NAME;
				break;
			case STENCH_SYMBOL:
				icon = STENCH_NAME;
				break;
			case BREEZE_STENCH_SYMBOL:
				icon = BREEZE_STENCH_NAME;
				break;
		}
		
		String imagePath = IMAGE_PATH + images.get(icon);
		return imagePath;
	}
	
}
