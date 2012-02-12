import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.sound.sampled.*;
import java.io.*;

public class PuzzleArea extends JPanel {

	PuzzleMenuBar menuBar;
	Square[] squareArray;
	BufferedImage image;
	BufferedImage subimage;
	Timer timer;
	Clip sound;
	boolean animation;
	Square lastSquareMoved;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(subimage,0,0,null);
	}
	public void playSound() {
		if (!sound.isRunning()) {
			sound.start();
			sound.setFramePosition(0);
		}
	}
	public void performMove(Choice choice) {
		if (choice.direction.equals("right")) {
			choice.square.moveRight();
			playSound();
		}
		else if (choice.direction.equals("left")) {
			choice.square.moveLeft();
			playSound();
		}
		else if (choice.direction.equals("up")) {
			choice.square.moveUp();
			playSound();
		}
		else if (choice.direction.equals("down")) {
			choice.square.moveDown();
			playSound();
		}
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
	public void scramble() {
		Choice[] choice = new Choice[0];
		for (int j = 0; j < squareArray.length; j++) {
			int x = squareArray[j].x;
			int y = squareArray[j].y;
			String direction = checkEmptySpace(x+50,y+50);
			if (!direction.equals("none") && squareArray[j] != lastSquareMoved) {
				choice = Arrays.copyOf(choice,choice.length+1);
				choice[choice.length-1] = new Choice(squareArray[j],direction);
			}
		}
		int index = (int)(choice.length * Math.random());
		performMove(choice[index]);
		lastSquareMoved = choice[index].square;
		updateImage();
	}
	public boolean isSolved() {
		for (int i = 0; i < squareArray.length; i++) {
			if (squareArray[i].x != squareArray[i].solvedX || squareArray[i].y != squareArray[i].solvedY) {
				return false;
			}
		}
		return true;
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
		squareArray = Arrays.copyOf(squareArray,squareArray.length+1);
		squareArray[squareArray.length-1] = square;
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
		menuBar = new PuzzleMenuBar();
		squareArray = new Square[0];
		image = new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB);
		updateImage();
		animation = false;
		lastSquareMoved = new Square(0,0,18);
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(new File("collect.wav"));
			sound = AudioSystem.getClip();
			sound.open(input);
		}
		catch (Exception e) {}
		window.setJMenuBar(menuBar);
		window.setContentPane(this);
		window.setSize(406,451);
		window.setLocation(200,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}
	
}