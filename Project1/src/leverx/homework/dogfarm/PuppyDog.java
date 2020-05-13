package leverx.homework.dogfarm;

import java.util.logging.Logger;

public class PuppyDog extends Dog {

	private static final Logger logger = Logger.getLogger(PuppyDog.class.getName());

	private boolean needsTraining;

	public PuppyDog(Builder builder) {
		super(builder);
		this.needsTraining = builder.needsTraining;

	}

	@Override
	public boolean grownUp() {
		if (DogFarm.AGE_OF_ADULTHOOD < this.getAge()) { // then the puppy became adult
			logger.info("The puppy dog with ID " + id + " became adult");
			return true;
		} else
			return false;
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
		if (this.needsTraining) {
			logger.info("The puppy dog with ID " + id + " goes for a training");
			this.needsTraining = false;
		}

	}

	public void wakeUp(float ageToAdd) {
		super.wakeUP(ageToAdd);
		this.needsTraining = false;
	}

	public static class Builder extends Dog.Builder<PuppyDog> {
		public boolean needsTraining = true;

		public Builder needsTraining(boolean needsTraining) {
			this.needsTraining = needsTraining;
			return this;
		}

		public PuppyDog build() {
			return new PuppyDog(this);
		}

	}

}
