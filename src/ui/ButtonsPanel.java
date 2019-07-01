package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

public final class ButtonsPanel extends JPanel { 
	/* Panel at the bottom, defining the area for Solve button and the Legend panel */
	private final SolveButton solveButton;
	
	public ButtonsPanel(MazeApp mazeApp) {
		super();
		setLayout(new GridLayout(1, 2));
		add(solveButton = new SolveButton(mazeApp));
		add(new LegendPanel(mazeApp));
	}
	
	public void notifyForUpdate() {
		solveButton.notifyForUpdate();
	}
	
}