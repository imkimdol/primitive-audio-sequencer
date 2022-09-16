package ui.graphical.buttons;

import ui.graphical.GraphicalInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


// The button in TrackPanel responsible for muting a track.

public class MuteButton extends ToggleButtonAbstract implements ActionListener {
    // Constructor for MuteButton
    // EFFECTS: Calls super(), sets the button icon, and adds this as actionListener.
    public MuteButton(int trackNumber, GraphicalInterface graphical) {
        super(trackNumber, graphical);

        String iconPath = GraphicalInterface.DIRECTORY_PATH + "icon" + File.separator + "volume-mute-line.png";
        setIcon(new ImageIcon(iconPath));

        addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  Resets selected state to corresponding track's enabled state.
    @Override
    public void update() {
        setSelected(!graphical.getProject().getTrack(trackNumber).getEnabled());
    }

    // MODIFIES: graphicalInterface.getProject().getTrack(trackNumber)
    // EFFECTS:  Mutes corresponding track.
    @Override
    public void actionPerformed(ActionEvent e) {
        graphical.getProject().getTrack(trackNumber).setEnabled(!isSelected());
    }
}
