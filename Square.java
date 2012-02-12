import java.awt.*;

public class Square {

	int x, y, number;
	int solvedX, solvedY;
	Color color;
	public void moveRight() {
		x += 100;
	}
	public void moveLeft() {
		x -= 100;
	}
	public void moveUp() {
		y -= 100;
	}
	public void moveDown() {
		y += 100;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x,y,100,100);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(x,y,100,100);
		g.setColor(Color.GRAY);
		g.drawRect(x+1,y+1,98,98);
		g.setColor(Color.WHITE);
		g.drawRect(x+2,y+2,96,96);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD, 60));
		if (number < 10) {
			g.drawString(Integer.toString(number),x+35,y+70);
		}
		else {
			g.drawString(Integer.toString(number),x+20,y+70);
		}
	}
	public Square(int x, int y, int number) {
		this.x = x;
		this.y = y;
		solvedX = x;
		solvedY = y;
		this.number = number;
		this.color = Color.BLUE;
	}
	
}