package leverx.homework.dogfarm;

import java.util.ArrayList;
import java.util.List;
/**
 * Aviary has a list of Dogs it is occupied by and a number of Dogs inside of the aviary at a particular moment
 * @author christinarahel
 *
 */
public class Aviary {
	public final int id;
	private boolean isClean;
	public List<Dog> petsList; 
	public int dogsNowIn; 

public Aviary(int id){   // each aviary is created clean and empty
		dogsNowIn =0; 
		this.isClean = true;
		this.id =id;
		petsList=new ArrayList<Dog>(); // how shall i do better ?????
	}

public void setIsClean(boolean b) {
	this.isClean=b;
}

public boolean getIsClean() {
	return this.isClean;
}

public void addDog(Dog dog) {
	 petsList.add(dog);
    }

}

