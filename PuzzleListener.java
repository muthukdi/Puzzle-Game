import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PuzzleListener implements MouseListener, ActionListener {
	PuzzleArea area;
	Timer timer;
	JMenuItem scramble;
	public void mousePressed(MouseEvent e) {
		if (area.animation) {
			return;
		}
		int xValue = e.getX() + 100;
		int yValue = e.getY() + 100;
		Square square = null;
		for (int i = 0; i < area.squareArray.length; i++) {
			if (xValue > area.squareArray[i].x && xValue < area.squareArray[i].x + 100 && yValue > area.squareArray[i].y && yValue < area.squareArray[i].y + 100) {
				square = area.squareArray[i];
				break;
			}
		}
		if (square != null) {
			String direction = area.checkEmptySpace(xValue, yValue);
			Choice choice = new Choice(square, direction);
			area.performMove(choice);
			area.lastSquareMoved = square;
			area.updateImage();
		}
		if (area.isSolved()) {
			JOptionPane.showMessageDialog(area,"Congratulations! You have solved the puzzle!");
		}
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == timer) {
			area.scramble();
		}
		else if (source == scramble) {
			if (!timer.isRunning()) {
				for (int i = 0; i < area.squareArray.length; i++) {
					area.squareArray[i].color = Color.RED;
				}
				area.updateImage();
				timer.start();
				area.animation = true;
				scramble.setText("Stop Scramble");
			}
			else {
				timer.stop();
				for (int i = 0; i < area.squareArray.length; i++) {
					area.squareArray[i].color = Color.BLUE;
				}
				area.animation = false;
				scramble.setText("Start Scramble");
				area.updateImage();
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public PuzzleListener(PuzzleArea area) {
		this.area = area;
		area.addMouseListener(this);
		scramble = area.menuBar.getMenu(0).getItem(0);
		scramble.addActionListener(this);
		timer = new Timer(300,this);
	}
	
}