package leverx.homework.dogfarm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DogFarm {

	private static final Logger logger = Logger.getLogger("leverx.homework.dogfarm.DogFarm");

	public static final float AGE_OF_ADULTHOOD = 2.0f;
	public static final float AGE_OF_RETIREMENT = 8.0f;
	public static final float LIFE_TERM = 15.0f;

	private List<Dog> dogsList;
	private int dogsNumber;

	private List<Aviary> aviariesList;
	private int aviariesNumber;

	private List<Veterinarian> vetList;
	private int vetNumber;

	private List<ServingStaff> staffList;
	private int staffNumber;

	private DogFarm(Builder builder) {
		this.dogsNumber = builder.dogsNumber;
		this.dogsList = new ArrayList<Dog>(dogsNumber);

		this.aviariesNumber = builder.aviariesNumber;
		this.aviariesList = new ArrayList<Aviary>(aviariesNumber);

		this.vetNumber = builder.vetNumber;
		this.vetList = new ArrayList<Veterinarian>(vetNumber);

		this.staffNumber = builder.staffNumber;
		this.staffList = new ArrayList<ServingStaff>(dogsNumber);
	}

	public static class Builder {
		private int dogsNumber;
		private int aviariesNumber;
		private int vetNumber;
		private int staffNumber;

		public Builder dogsNumber(int dogsNumber) {
			this.dogsNumber = dogsNumber;
			return this;
		}

		public Builder aviariesNumber(int aviariesNumber) {
			this.aviariesNumber = aviariesNumber;
			return this;
		}

		public Builder vetNumber(int vetNumber) {
			this.vetNumber = vetNumber;
			return this;
		}

		public Builder staffNumber(int staffNumber) {
			this.staffNumber = staffNumber;
			return this;
		}

		public DogFarm build() {
			return new DogFarm(this);
		};
	}

	/**
	 * DogFarm consists of: a list of the Dogs (dog can be a puppy, an adult, an
	 * elderly); list of ServingStaff, list of Veterinarians. A Dog needs to be fed
	 * twice daily, checked by a Veterinarian and served by the ServingStaff.
	 * 
	 * Every morning the dogs become older and get sick randomly. Veterinarian
	 * checks each Dog, if the dog is not healthy - treats, and declares a dog ready
	 * for work (other duties). Serving staff works only with Dogs inspected by the
	 * Veterinarian. Serving staff trains puppies, sends the dogs to work or to rest
	 * in order to empty the aviary and to clean the aviary only when it's empty.
	 * When all the aviaries cleaned, dogs can come back to their aviaries and can
	 * eat for the second time. Then the day is over.
	 */

	public static void main(String[] args) {

		DogFarm dogFarm = new DogFarm.Builder().dogsNumber(5).aviariesNumber(3).staffNumber(3).vetNumber(2).build();
		dogFarm.initialize();

		dogFarm.startTheDay(1); // the parameter lets us choose how many days have passed
		dogFarm.feedAll();
		dogFarm.vetDuties();
		dogFarm.staffDuties();
		dogFarm.sendAllHome();
		dogFarm.feedAll();
		logger.info("\nThe day on the Farm is over");

	}

	public void initialize() {
		logger.info("initialize the farm data");
		// creating a list of "aviariesNumber" aviaries
		for (int i = 0; i < aviariesNumber; i++) {
			aviariesList.add(new Aviary(i + 1));
		}

		// creating dogs and assign to each an aviary
		for (int i = 0; i < dogsNumber; i++) {

			float age = (float) Math.random() * LIFE_TERM; // the gods' age is defined randomly

			if (age < AGE_OF_ADULTHOOD) { // if a dog is still young, we consider it a puppy
				PuppyDog.Builder b = new PuppyDog.Builder();
				b.needsTraining(true).id(i + 1).age(age).isHealthy(true).isHungry(false);
				PuppyDog pd = (PuppyDog) b.build();
				dogsList.add(pd);
				aviariesList.get(i % aviariesNumber).addDog(pd); // accommodate the dog to an aviary
				pd.setAviary(aviariesList.get(i % aviariesNumber)); // informing a dog about its aviary

			} else if (age < AGE_OF_RETIREMENT) {// if a dog is above the retirement age, we consider it an adult
				AdultDog ad = (AdultDog) new AdultDog.Builder().id(i + 1).age(age).isHealthy(true).isHungry(false)
						.build();
				dogsList.add(ad);
				aviariesList.get(i % aviariesNumber).addDog(ad); // accommodate the dog to an aviary
				ad.setAviary(aviariesList.get(i % aviariesNumber)); // informing a dog about its aviary
			}

			else { // if a dog has reached retirement age we consider it an elderly
				ElderlyDog ed = (ElderlyDog) new ElderlyDog.Builder().id(i + 1).age(age).isHealthy(true).isHungry(false)
						.build();
				dogsList.add(ed);
				aviariesList.get(i % aviariesNumber).addDog(ed); // accommodate the dog to an aviary
				ed.setAviary(aviariesList.get(i % aviariesNumber)); // informing a dog about its aviary
			}

		}
		for (int i = 0; i < aviariesNumber; i++) {
			aviariesList.get(i).dogsNowIn = aviariesList.get(i).petsList.size(); // all the pets are in the aviaries
																					// originally
		}

		// creating list of veterinarian and giving to each the link of dogsList to be
		// served
		for (int i = 0; i < vetNumber; i++) {
			vetList.add(new Veterinarian(i + 1));
		}

		// creating list of staff and giving to each the link of dogsList to be served
		for (int i = 0; i < staffNumber; i++) {
			staffList.add(new ServingStaff(i + 1));
		}
	}

	public void startTheDay(int dayPassed) {
		logger.info("starting a new day after " + dayPassed + " day(s) have passed");
		for (int i = 0; i < dogsNumber; i++) {
			dogsList.get(i).wakeUP(dayPassed / 364.0f); // TO REMOVE THIS NUMBER

			if (dogsList.get(i).grownUp()) { // checking if someone grown from its age category
				Dog dog = dogsList.get(i);
				if (dog.getAge() > AGE_OF_RETIREMENT) {
					dog.getAviary().petsList.remove(dog);
					ElderlyDog ad = (ElderlyDog) new ElderlyDog.Builder().id(dog.id).age(dog.getAge())
							.isHealthy(dog.getIsHealthy()).isHungry(true).build();
					dogsList.set(i, ad);
					dog.getAviary().petsList.add(ad);

				} else {
					dog.getAviary().petsList.remove(dog);
					AdultDog ad = (AdultDog) new AdultDog.Builder().id(dog.id).age(dog.getAge())
							.isHealthy(dog.getIsHealthy()).isHungry(true).build();
					dogsList.set(i, ad);
					dog.getAviary().petsList.add(ad);
				}
			}
		}

		for (int i = 0; i < aviariesNumber; i++) {
			aviariesList.get(i).setIsClean(false); // all the aviaries are dirty in the morning
		}
	}

	public void feedAll() {
		logger.info("starting to feed all the dogs");
		for (int i = 0; i < dogsNumber; i++) {
			dogsList.get(i).eats();
		}
	}

	public void vetDuties() {
		logger.info("vets start to exam all the dogs");
		for (int i = 0; i < dogsNumber; i++)
			vetList.get(i % vetNumber).toCheck(dogsList.get(i));
	}

	public void staffDuties() {
		logger.info("serving staff starts to send the dogs for their daily duties and to clean empty aviaries");
		for (int i = 0, j = 0; i < aviariesNumber; j++) {
			if (!aviariesList.get(i).getIsClean())
				if (aviariesList.get(i).dogsNowIn == 0) {
					staffList.get(j % staffNumber).toClean(aviariesList.get(i));
					i++;
				} else {
					aviariesList.get(i).petsList.get(aviariesList.get(i).dogsNowIn - 1).dailyRoutine();
					aviariesList.get(i).dogsNowIn--;
				}
		}
	}

	public void sendAllHome() {
		logger.info("all the dogs are coming back and are getting hungry");
		for (int i = 0; i < dogsList.size(); i++) {
			dogsList.get(i).setIsHungry(true); // dog has got hungry
			dogsList.get(i).getAviary().dogsNowIn++;
		}
	}
}
