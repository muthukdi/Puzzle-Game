import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class PuzzleArea extends JPanel {

	Square[] squareArray;
	BufferedImage image;
	BufferedImage subimage;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(subimage,0,0,null);
	}
	public boolean checkOverLap(Square square) {
		for (int i = 0; i < squareArray.length; i++) {
			if (square.x == squareArray[i].x && square.y == squareArray[i].y) {
				return true;
			}
		}
		return false;
	}
	public String checkEmptySpace(int x, int y) {
		Color spaceColor = new Color(0,0,50);
		if (new Color(image.getRGB(x+100,y)).equals(spaceColor)) {
			return "right";
		}
		else if (new Color(image.getRGB(x-100,y)).equals(spaceColor)) {
			return "left";
		}
		else if (new Color(image.getRGB(x,y-100)).equals(spaceColor)) {
			return "up";
		}
		else if (new Color(image.getRGB(x,y+100)).equals(spaceColor)) {
			return "down";
		}
		else {
			return "none";
		}
	}
	public boolean addSquare(Square square) {
		if (square.x < 100 || square.y < 100 || square.x > 400 || square.y > 400) {
			return false;
		}
		else if (square.x % 100 != 0 || square.y % 100 != 0) {
			return false;
		}
		else if (checkOverLap(square)) {
			return false;
		}
		Square[] tempArray = new Square[squareArray.length];
		for (int i = 0; i < squareArray.length; i++) {
			tempArray[i] = squareArray[i];
		}
		squareArray = new Square[tempArray.length+1];
		for (int i = 0; i < tempArray.length; i++) {
			squareArray[i] = tempArray[i];
		}
		squareArray[tempArray.length] = square;
		updateImage();
		return true;
	}
	public void updateImage() {
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,50));
		g.fillRect(100,100,400,400);
		for (int i = 0; i < squareArray.length; i++) {
			squareArray[i].draw(g);
		}
		g.dispose();
		subimage = image.getSubimage(100,100,400,400);
		repaint();
	}
	public PuzzleArea() {
		JFrame window = new JFrame("15 Puzzle");
		squareArray = new Square[0];
		image = new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB);
		updateImage();
		window.setContentPane(this);
		window.setSize(406,428);
		window.setLocation(200,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}
	
}