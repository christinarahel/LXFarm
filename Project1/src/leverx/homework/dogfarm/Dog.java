package leverx.homework.dogfarm;

import java.util.Random;

public abstract class Dog {

	public final int id;
	private float age;
	private boolean isHungry;
	private boolean isHealthy;
	private boolean isReady; // treated and ready for work or for other daily routine
	private Aviary aviary;

	public Dog(Builder<?> builder) {  //here is a warning
		this.id = builder.id;
		this.age = builder.age;
		this.isHealthy = builder.isHealthy;
		this.isHungry = builder.isHungry;
		this.isReady = false;
	}

	public float getAge() {
		return this.age;
	}
	
	public void setAge(float age) {
		this.age =age;
	}
    
	public boolean getIsHungry() {
		return this.isHealthy;
	}

	public void setIsHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}
	
	public boolean getIsHealthy() {
		return this.isHealthy;
	}

	public void setIsHealthy(boolean isHealthy) {
		this.isHealthy = isHealthy;
	}

	public boolean getIsReady() {
		return this.isReady;
	}

	public void setIsReady(boolean isReady) {
		this.isHealthy = isReady;
	}
		
	public void setAviary(Aviary aviary) {
		this.aviary = aviary;
	}

	public Aviary getAviary() {
		return this.aviary;
	}

	public abstract boolean grownUp(); // return true only in case a dog has grown up from its age category

	public abstract void eats();

	public abstract void dailyRoutine();

	public void wakeUP(float ageToAdd) { // in the morning a dog wakes up hungry, randomly sick, "ageToAdd" older!
		this.age += ageToAdd;
		this.isHungry = true;
		this.isReady = false;
		this.isHealthy = new Random().nextBoolean();
	}

	// to delete this method afterwards
	public String toString() {
		return "Dog " + id + " Age: " + age + " isHealthy: " + isHealthy + " isHungry: " + isHungry;
	}

	public static abstract class Builder<T extends Dog> {
		private int id;
		private float age;
		private boolean isHungry;
		private boolean isHealthy;
		public boolean isReady;

		public Builder<T> id(int id) {
			this.id = id;
			return this;
		}

		public Builder<T> age(float age) {
			this.age = age;
			return this;
		}

		public Builder<T> isHungry(boolean isHungry) {
			this.isHungry = isHungry;
			return this;
		}

		public Builder<T> isHealthy(boolean isHealthy) {
			this.isHealthy = isHealthy;
			return this;
		}

		public abstract T build();

	}

}
