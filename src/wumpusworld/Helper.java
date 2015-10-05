package wumpusworld;

import java.util.Random;

public class Helper {
	
	public static int random(int min, int max){
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}

}
