package leverx.homework.dogfarm;

import java.util.Random;

public abstract class Dog {
	
	public final int id;
	protected float age;
	protected boolean isHungry;
	protected boolean isHealthy;
	protected boolean isReady;  // treated and ready for work or for other daily routine
	private Aviary aviary; 
	
	public Dog(Builder builder) {
		this.id = builder.id;
		this.age = builder.age;
		this.isHealthy = builder.isHealthy;  
		this.isHungry = builder.isHungry;
		this.isReady = false;
	}
	
	public float getAge() {
		return this.age;
	}
	
	public boolean getIsHealthy() {
		return this.isHealthy;
	}
	
	public void setIsHealthy(boolean isHealthy) {
		this.isHealthy = isHealthy;
	}
	
	public void setAviary(Aviary aviary) {
		this.aviary=aviary;
	}
	
	public Aviary getAviary() {
		return this.aviary;
	}
	
	public abstract boolean grownUp(); // return true only in case a dog has grown up from its age category
	
	public abstract void eats();
	
	public abstract void dailyRoutine();
	
	public void wakeUP(float ageToAdd) {  //in the morning a dog wakes up hungry, randomly sick, "ageToAdd" older!  
		this.age+=ageToAdd;
		this.isHungry=true;
		this.isReady=false;
		this.isHealthy = new Random().nextBoolean();
	}
	
	// to delete this method afterwards
	public String toString() {
		return "Dog "+id+" Age: " + age+" isHealthy: " + isHealthy +  " isHungry: " +isHungry;
	}
	
	public static abstract class Builder {
		private int id;
		private float age;
		private boolean isHungry;
		private boolean isHealthy;
		public boolean isReady;
		
		public Builder id(int id) { this.id = id; return this; } 
		
		public Builder age(float age) { this.age = age; return this; }   
		
		public Builder isHungry(boolean isHungry) { this.isHungry = isHungry; return this; } 
		
		public Builder isHealthy(boolean isHealthy) { this.isHealthy = isHealthy; return this; } 
		
	    public abstract <T extends Dog> T build();
		
	}

}
