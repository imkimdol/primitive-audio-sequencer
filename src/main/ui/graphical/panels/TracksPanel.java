package ui.graphical.panels;

import ui.graphical.GraphicalInterface;

import java.util.ArrayList;
import java.util.List;


// The panel in GraphicalInterface which displays information about all tracks.

public class TracksPanel extends PanelAbstract {
    private static final int X_POS = 0;
    private static final int Y_POS = GraphicalInterface.ROW_HEIGHT;
    private static final int WIDTH = GraphicalInterface.INTERFACE_WIDTH;

    private List<TrackPanel> trackPanels;


    // Constructor for TracksPanel
    // EFFECTS: Calls super(), sets trackNumber and graphicalInterface to given values.
    //          Calls updateSize() and update();.
    public TracksPanel(GraphicalInterface graphical) {
        super(graphical);

        updateSize();
        update();
    }

    // MODIFIES: this, trackPanels
    // EFFECTS: Updates the height and size of trackPanels to match project size.
    public void updateSize() {
        removeAll();
        int projectSize = graphical.getProject().getTrackList().size();

        trackPanels = new ArrayList<>();
        for (int i = 0; i < projectSize; i++) {
            TrackPanel trackPanel = new TrackPanel(i, graphical);

            add(trackPanel);
            trackPanels.add(trackPanel);
        }

        int height = GraphicalInterface.ROW_HEIGHT * projectSize;
        setBounds(X_POS, Y_POS, WIDTH, height);
    }

    // MODIFIES: trackPanels
    // EFFECTS: Updates all TrackPanels in trackPanels to display correct information.
    @Override
    public void update() {
        for (TrackPanel trackPanel : trackPanels) {
            trackPanel.update();
        }
    }
}
