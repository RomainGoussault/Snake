import java.util.LinkedList;




public class Snake implements Constants{

	private int size;
	private LinkedList<Cell> body; // = new ArrayList<Position>();



	Snake()
	{
		size = 3;
		body= new LinkedList<Cell>();
		int i;
		
		
		
		for(i = 0; i<size; i++)
		{
			body.add(new Cell(i,2));

		}

	}

	Snake(int s)
	{
		
		size = s;
		body= new LinkedList<Cell>();
		int i;
		int i0 = (int) ((N_COLUMNS - size)/2);
		for(i = 0; i<size; i++)
		{
			body.add(new Cell(i0 + i,(int) (N_LINES/2)));

		}
		
	}


	public Cell getNextCell(Direction dir)
	{
		Cell head = this.getHead();
		int i = head.getI();
		int j = head.getJ();

		
		switch (dir)
		{
		case UP:
			return new Cell(i,j-1);

		case LEFT:
			return new Cell(i-1,j);

		case DOWN:
			return new Cell(i,j+1);

		case RIGHT:
			return new Cell(i+1,j);

		default:
			return new Cell(i,j);

		}
	}


	public void move(Direction dir)
	{
		body.addFirst(getNextCell(dir));
		body.removeLast();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void increaseSize() {
		this.size++;
	}


	public LinkedList<Cell> getBody() {
		return body;
	}

	public void setBody(LinkedList<Cell> body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Snake [size=" + size + ", body=" + body + "]";
	}

	public Cell getHead() {
		return body.getFirst();
	}

}
