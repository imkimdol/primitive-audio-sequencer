package ui.graphical.panels;

import model.Project;
import ui.graphical.GraphicalInterface;
import ui.graphical.buttons.DeleteTrackButton;
import ui.graphical.buttons.MuteButton;
import ui.graphical.buttons.NoteToggleButton;
import ui.graphical.buttons.ToggleButtonAbstract;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;


// The panel in GraphicalInterface which displays information about a single track.


public class TrackPanel extends PanelAbstract {
    private static final int X_POS = 0;
    private static final int WIDTH = GraphicalInterface.INTERFACE_WIDTH;
    private static final int HEIGHT = GraphicalInterface.ROW_HEIGHT;
    private static final int BUTTON_SIZE = GraphicalInterface.BUTTON_SIZE;
    private static final int SPACE = GraphicalInterface.SPACE;
    private static final int NOTES_PER_PAGE = GraphicalInterface.NOTES_PER_PAGE;
    private static final int LABEL_WIDTH = GraphicalInterface.TRACK_LABEL_WIDTH;
    private static final int INFO_WIDTH = GraphicalInterface.TRACK_INFO_WIDTH;

    private ToggleButtonAbstract deleteButton;
    private ToggleButtonAbstract muteButton;
    private JLabel trackLabel;
    private List<ToggleButtonAbstract> toggleButtonList;

    private int trackNumber;


    // Constructor for TrackPanel
    // EFFECTS: Calls super(), sets trackNumber and graphical to given values.
    //          Sets panel bounds and background.
    //          Initializes and adds deleteButton, muteButton, trackLabel, and toggle buttons.
    public TrackPanel(int trackNumber, GraphicalInterface graphical) {
        super(graphical);
        this.trackNumber = trackNumber;

        setBounds(X_POS,HEIGHT * trackNumber, WIDTH, HEIGHT);
        setBackground(Color.lightGray);

        deleteButton = new DeleteTrackButton(trackNumber, graphical);
        deleteButton.setBounds(SPACE, (HEIGHT / 2) - (BUTTON_SIZE / 2), BUTTON_SIZE, BUTTON_SIZE);
        add(deleteButton);

        muteButton = new MuteButton(trackNumber, graphical);
        muteButton.setBounds(SPACE + BUTTON_SIZE, (HEIGHT / 2) - (BUTTON_SIZE / 2), BUTTON_SIZE, BUTTON_SIZE);
        add(muteButton);

        trackLabel = new JLabel();
        trackLabel.setBounds(SPACE + BUTTON_SIZE + BUTTON_SIZE + SPACE, 0, LABEL_WIDTH, HEIGHT);
        add(trackLabel);

        toggleButtonList = new ArrayList<>();
        for (int i = 0; i < NOTES_PER_PAGE; i++) {
            NoteToggleButton toggleButton = new NoteToggleButton(trackNumber, i, graphical);
            toggleButton.setBounds(INFO_WIDTH + (BUTTON_SIZE * i),
                    (HEIGHT / 2) - (BUTTON_SIZE / 2), BUTTON_SIZE, BUTTON_SIZE);
            add(toggleButton);

            toggleButtonList.add(toggleButton);
        }
    }

    // MODIFIES: this, trackLabel, deleteButton, muteButton, toggleButtonList
    // EFFECTS: Updates this and all children to display correct information.
    @Override
    public void update() {
        Project project = graphical.getProject();

        if (trackNumber >= project.getTrackList().size()) {
            setVisible(false);
        } else {
            trackLabel.setText(project.getTrack(trackNumber).getName());
            deleteButton.update();
            muteButton.update();

            for (ToggleButtonAbstract toggleButton : toggleButtonList) {
                toggleButton.update();
            }

            setVisible(true);
        }
    }
}

