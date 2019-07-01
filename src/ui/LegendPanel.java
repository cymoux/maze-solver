package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public final class LegendPanel extends JPanel { 
	/* class of the legend explaining the color of the various boxes at the bottom */
	public LegendPanel(MazeApp mazeApp) {
		super();

		setLayout(new GridLayout(5, 2));
		
		final JEditorPane legendTitle = new JEditorPane("text/html", "");
		legendTitle.setText("<b> Legend: </b>");
		add(legendTitle);
		final JTextArea nothing = new JTextArea("");
		add(nothing);
		
		final JPanel departurePanel = new JPanel();
		departurePanel.setBackground(Color.GREEN);
		departurePanel.setForeground(Color.BLACK);
		add(departurePanel);
		final JTextArea departureText = new JTextArea(" Departure box");
        departureText.setEditable(false);
		add(departureText);
		
		
		final JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.WHITE);
		departurePanel.setForeground(Color.BLACK);
		add(emptyPanel);	
		final JTextArea emptyText = new JTextArea(" Empty box");
        emptyText.setEditable(false);
		add(emptyText);
		
		final JPanel wallPanel = new JPanel();
		wallPanel.setBackground(Color.BLACK);
		departurePanel.setForeground(Color.BLACK);
		add(wallPanel);	
		final JTextArea wallText = new JTextArea(" Wall box");
        wallText.setEditable(false);
		add(wallText);

		final JPanel arrivalPanel = new JPanel();
		arrivalPanel.setBackground(Color.RED);
		departurePanel.setForeground(Color.BLACK);
		add(arrivalPanel);
		final JTextArea arrivalText = new JTextArea(" Arrival box");
        arrivalText.setEditable(false);
		add(arrivalText);	
	}
}