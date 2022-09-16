package ui.graphical.buttons;

import ui.graphical.GraphicalInterface;

import javax.swing.*;


// Abstract class for toggle buttons used in TrackPanel.

public abstract class ToggleButtonAbstract extends JToggleButton {
    protected int trackNumber;
    protected GraphicalInterface graphical;

    // Constructor for ToggleButtonAbstract
    // EFFECTS: Sets trackNumber and graphicalInterface to given values.
    public ToggleButtonAbstract(int trackNumber, GraphicalInterface graphical) {
        this.trackNumber = trackNumber;
        this.graphical = graphical;
    }

    // EFFECTS: Updates the button's state.
    public abstract void update();
}
