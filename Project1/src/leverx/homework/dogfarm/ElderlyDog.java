package leverx.homework.dogfarm;

import java.util.logging.Logger;

public class ElderlyDog extends Dog {
    
	private static final Logger logger = Logger.getLogger(ElderlyDog.class.getName());
	
	public ElderlyDog(Builder builder) {
		super(builder);
	}
	
	@Override
	public boolean grownUp() {
		return false;
	}

	@Override
	public void eats() {
		if (this.getIsHungry()) {
			logger.info("The elderly dog with ID " + id + " is eating a speccial meal for elderly");
			this.setIsHungry(false);	
			}
	}

	@Override
	public void dailyRoutine() {
		logger.info("The elderly dog with ID " + id + " will rest in its dog house until the aviary is cleaned");
	}
	
	public static class Builder extends Dog.Builder<ElderlyDog>{
		
		public ElderlyDog build() {return new ElderlyDog(this);} 
	}

}
