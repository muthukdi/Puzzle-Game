public class Choice {

	Square square;
	String direction;
	public String toString() {
		String data = Integer.toString(square.number)+", "+direction;
		return data;
	}
	public Choice(Square square, String direction) {
		this.square = square;
		this.direction = direction;
	}

}