package leverx.homework.dogfarm;

import java.util.logging.Logger;

public class Veterinarian {

	private static final Logger logger = Logger.getLogger(Veterinarian.class.getName());
	public final int id;

	public Veterinarian(int id) {
		this.id = id;
	}

	public void toCheck(Dog dog) {
		logger.info("The veterinarian " + id + " just started to check the dog " + dog.id);

		if (dog.getIsHealthy()) {
			logger.info("The dog number " + dog.id + " is healthy");
		} else {
			logger.info("The dog number " + dog.id + " is not healthy");
			toTreat(dog);
		}
		logger.info("The veterinarian number " + id + " is done with the dog number " + dog.id + "\nThe dog number "
				+ dog.id + " is ready for its daily routine");
		dog.setIsReady(true);
	}

	public void toTreat(Dog dog) {
		logger.info("The veterinarian number " + id + " is treating the dog number " + dog.id);
		dog.setIsHealthy(true); // the dog is cured!
		logger.info("The dog number " + dog.id + " is fine now");
	}

}
