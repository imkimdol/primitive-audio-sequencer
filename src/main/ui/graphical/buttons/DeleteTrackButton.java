package ui.graphical.buttons;

import ui.graphical.GraphicalInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


// The button in TrackPanel responsible for deleting a track.

public class DeleteTrackButton extends ToggleButtonAbstract implements ActionListener {
    // Constructor for DeleteTrackButton
    // EFFECTS: Calls super(), sets button icon, and adds this as actionListener.
    public DeleteTrackButton(int trackNumber, GraphicalInterface graphical) {
        super(trackNumber, graphical);

        String iconPath = GraphicalInterface.DIRECTORY_PATH + "icon" + File.separator + "delete-bin-line.png";
        setIcon(new ImageIcon(iconPath));

        addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  Resets selected state to false.
    @Override
    public void update() {
        setSelected(false);
    }

    // EFFECTS: Removes corresponding track updates interface.
    @Override
    public void actionPerformed(ActionEvent e) {
        graphical.getProject().removeTrack(trackNumber);
        graphical.updateSize();
        graphical.update();
    }
}
