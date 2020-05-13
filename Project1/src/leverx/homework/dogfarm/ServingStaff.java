package leverx.homework.dogfarm;

import java.util.logging.Logger;

public class ServingStaff {

	private static final Logger logger = Logger.getLogger(ServingStaff.class.getName());
	public final int id;

	public ServingStaff(int id) {
		this.id = id;
	}

	public void toClean(Aviary aviary) {

		logger.info("The staff member number " + id + " just started to clean aviary number " + aviary.id);
		logger.info("The aviary number " + aviary.id + " is clean now");
		aviary.setIsClean(true);

	}

	public void toSendForDailyRoutine(Dog dog) {
		logger.info("The staff member number: " + id + " takes the dog number " + dog.id + " for its daily routing ");
		dog.dailyRoutine();

	}

}
