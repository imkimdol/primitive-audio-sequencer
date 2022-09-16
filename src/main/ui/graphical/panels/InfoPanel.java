package ui.graphical.panels;

import model.Project;
import ui.graphical.GraphicalInterface;

import javax.swing.*;


// The panel in GraphicalInterface which displays project information.

public class InfoPanel extends PanelAbstract {
    private static final int X_POS = 0;
    private static final int Y_POS = 0;
    private static final int LABEL_WIDTH = 100;

    private static final int WIDTH = GraphicalInterface.INTERFACE_WIDTH;
    private static final int HEIGHT = GraphicalInterface.ROW_HEIGHT;
    private static final int SPACE = GraphicalInterface.SPACE;

    private JLabel tempoLabel;
    private JLabel nameLabel;
    private JLabel pageLabel;


    // Constructor for InfoPanel
    // EFFECTS: Calls super(), sets graphical to given value, sets panel bounds.
    //          Initializes and adds tempoLabel, nameLabel, pageLabel.
    public InfoPanel(GraphicalInterface graphical) {
        super(graphical);

        setBounds(X_POS, Y_POS, WIDTH, HEIGHT);

        tempoLabel = new JLabel("Project BPM");
        tempoLabel.setBounds(SPACE, 0, LABEL_WIDTH, HEIGHT);
        add(tempoLabel);

        nameLabel = new JLabel("Project Name");
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setBounds((WIDTH / 2) - (LABEL_WIDTH / 2), 0, LABEL_WIDTH, HEIGHT);
        add(nameLabel);

        pageLabel = new JLabel("Current Page");
        pageLabel.setBounds(WIDTH - (LABEL_WIDTH + SPACE), 0, LABEL_WIDTH, HEIGHT);
        add(pageLabel);
    }

    // MODIFIES: tempoLabel, nameLabel, pageLabel
    // EFFECTS:  Updates tempoLabel, nameLabel, and pageLabel to display correct information.
    @Override
    public void update() {
        Project project = graphical.getProject();

        tempoLabel.setText(project.getTempo() + " bpm");

        nameLabel.setText(project.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        pageLabel.setText("Page "
                + (graphical.getPage() + 1)
                + "/"
                + (graphical.getProject().getLength() / GraphicalInterface.NOTES_PER_PAGE));
        pageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
