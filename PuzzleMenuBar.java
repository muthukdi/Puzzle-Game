import java.awt.*;
import javax.swing.*;

public class PuzzleMenuBar extends JMenuBar {

	JMenu tools;
	JMenuItem scramble;
	public PuzzleMenuBar() {
		tools = new JMenu("Tools");
		scramble = new JMenuItem("Start Scramble");
		tools.add(scramble);
		add(tools);
	}
	
}