package ui.graphical.panels;

import ui.graphical.GraphicalInterface;

import javax.swing.*;


// Abstract class for panels in GraphicalInterface.

public abstract class PanelAbstract extends JPanel {
    protected GraphicalInterface graphical;

    // Constructor for PanelAbstract
    // EFFECTS: Calls super with double buffering and sets layout to null;
    public PanelAbstract(GraphicalInterface graphical) {
        super(true);
        this.graphical = graphical;
        setLayout(null);
    }

    // EFFECTS: Updates the panels and their children to display correct information.
    public abstract void update();
}
