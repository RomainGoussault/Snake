public class Meat extends Cell implements Constants {

	Meat(){
		super();
	}

	public void generateNewPosition()
	{
		this.i = ((int)(1+Math.random() * ( N_COLUMNS-2)))  ;
		this.j = ((int)(1+Math.random() * (N_COLUMNS-2)))  ;
	}
}
