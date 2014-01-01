
public class Meat extends Cell implements Constants {




	Meat(){
		super();
		
	}

	public void generateNewPosition()
	{
		this.i = ((int)(Math.random() * N_COLUMNS))  ;
		this.j = ((int)(Math.random() * N_COLUMNS))  ;
	}

	

}
