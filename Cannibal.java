package assignment4;

import java.util.*;

public class Cannibal extends Critter.TestCritter {

	@Override
	public void doTimeStep() {
		run(getRandomInt(8));
	}

	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("Cannibal")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "C";
	}
	
	public void test (List<Critter> l) {
		
	}
}
