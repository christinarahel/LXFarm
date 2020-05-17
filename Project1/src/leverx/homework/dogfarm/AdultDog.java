package leverx.homework.dogfarm;

import java.util.logging.Logger;

public class AdultDog extends Dog {

	private static final Logger logger = Logger.getLogger(AdultDog.class.getName());

	public AdultDog(Builder builder) {
		super(builder);
	}

	@Override
	public boolean grownUp() {
		if (DogFarm.AGE_OF_RETIREMENT < this.getAge()) { // then the adult became elderly
			logger.info("The adult dog with ID " + id + " became elderly");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void eats() {
		if (this.getIsHungry()) {
			logger.info("The adult dog with ID " + id + " is eating a big meal for adults");
			this.setIsHungry(false);
		}
	}

	@Override
	public void dailyRoutine() {
		logger.info("The adult dog with ID " + id + " goes to work");
	}

	public static class Builder extends Dog.Builder<AdultDog> {

		@Override
		public AdultDog build() {
			return new AdultDog(this);
		}
	}

}
