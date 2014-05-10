public class Cell implements Constants{

	// Current position
	protected int i;
	protected int j;

	public Cell()
	{
this.i=0;
this.i=0;
	}
	
	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public int getX(){
		return i*CELL_SIZE;
	}
	
	public int getY(){
		return j*CELL_SIZE;
	}

	@Override
	public String toString() {
		return "Cell [i=" + i + ", j=" + j + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}	
}
