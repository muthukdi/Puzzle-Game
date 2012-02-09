import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class PuzzleListener implements MouseListener {

	PuzzleArea area;
	Clip sound1;
	public void playSound() {
		if (!sound1.isRunning()) {
			sound1.start();
			sound1.setFramePosition(0);
		}
	}
	public void mousePressed(MouseEvent e) {
		int xValue = e.getX() + 100;
		int yValue = e.getY() + 100;
		Square square = null;
		for (int i = 0; i < area.squareArray.length; i++) {
			if (xValue > area.squareArray[i].x && xValue < area.squareArray[i].x + 100 && yValue > area.squareArray[i].y && yValue < area.squareArray[i].y + 100) {
				square = area.squareArray[i];
				break;
			}
		}
		String direction = area.checkEmptySpace(xValue, yValue);
		if (direction.equals("right")) {
			square.moveRight();
			playSound();
		}
		else if (direction.equals("right")) {
			square.moveRight();
			playSound();
		}
		else if (direction.equals("left")) {
			square.moveLeft();
			playSound();
		}
		else if (direction.equals("up")) {
			square.moveUp();
			playSound();
		}
		else if (direction.equals("down")) {
			square.moveDown();
			playSound();
		}
		area.updateImage();
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public PuzzleListener(PuzzleArea area) {
		this.area = area;
		this.area.addMouseListener(this);
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(new File("collect.wav"));
			sound1 = AudioSystem.getClip();
			sound1.open(input);
		}
		catch (Exception e) {}
	}
	
}