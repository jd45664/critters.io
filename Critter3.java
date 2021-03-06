package assignment4;

import assignment4.Critter.TestCritter;

public class Critter3 extends TestCritter {
	/**
	 * this critter will reproduce every timestep until it dies
	 */
	@Override
	public void doTimeStep() {
		Critter3 offspring = new Critter3();
		Critter3 offspring2 = new Critter3();
		int randomDir = Critter.getRandomInt(8);
		int randomDir2 = Critter.getRandomInt(8);
		this.reproduce(offspring, randomDir);
	}
	/**
	 * always fights
	 */
	@Override
	public boolean fight(String opponent) {
		return true;
	}
	
	@Override
	public String toString () {
		return "3";
	}
}
