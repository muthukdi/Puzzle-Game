import java.awt.*;
// This is a commment.
public class Puzzle {

	public static void main(String[] args) {
		PuzzleArea area = new PuzzleArea();
		PuzzleListener listener = new PuzzleListener(area);
		int number = 0;
		for (int j = 100; j < 500; j += 100) {
			for (int i = 100; i < 500; i += 100) {
				number++;
				if (number < 16) {
					area.addSquare(new Square(i,j,number));
				}
			}
		}
	}

}