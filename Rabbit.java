package assignment4;

import assignment4.Critter.TestCritter;

public class Rabbit extends TestCritter {
	
	@Override
	public void doTimeStep() {
		walk(getRandomInt(8));
		if(getEnergy() < 65) {
			setEnergy(65);
		}
	}

	@Override
	public boolean fight(String opponent) {
		Rabbit crit = new Rabbit();
		int direction = getRandomInt(8);
		walk(direction);
		reproduce(crit,direction);
		reproduce(crit,direction);
		reproduce(crit,direction);
		return false;
	}

	@Override
	public String toString () {
		return "R";
	}
}
