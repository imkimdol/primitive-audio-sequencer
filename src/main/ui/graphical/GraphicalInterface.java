package ui.graphical;

import model.Project;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.LogPrinter;
import ui.graphical.panels.InfoPanel;
import ui.graphical.panels.TracksPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// The interface which handles display and user input.
// Responsible for all graphics user interactions.

public class GraphicalInterface extends JFrame {
    public static final int NOTES_PER_PAGE = 4 * 4 * 4; // 4 bars * 4 beats * 4 16th notes
    public static final String DIRECTORY_PATH = "." + File.separator + "data" + File.separator;

    public static final int ROW_HEIGHT = 30;
    public static final int BUTTON_SIZE = 20;
    public static final int TRACK_LABEL_WIDTH = 150;
    public static final int SPACE = BUTTON_SIZE / 2;
    public static final int TRACK_INFO_WIDTH = SPACE + BUTTON_SIZE + BUTTON_SIZE + SPACE + TRACK_LABEL_WIDTH + SPACE;
    public static final int INTERFACE_WIDTH = TRACK_INFO_WIDTH + (BUTTON_SIZE * NOTES_PER_PAGE) + SPACE;

    private Project project;

    private MenuBar menuBar;
    private InfoPanel infoPanel;
    private TracksPanel tracksPanel;

    private int page;


    // Constructor for TrackPanel
    // EFFECTS: Creates a new project,
    //          initializes the JFrame, and updates all visual elements.
    public GraphicalInterface() {
        newProject();
        initializeJframe();
        update();
    }



    // [PUBLIC METHODS]

    // MODIFIES: this, infoPanel, tracksPanel
    // EFFECTS:  Updates all visual elements.
    public void update() {
        int interfaceHeight = menuBar.getHeight() + ROW_HEIGHT * (project.getTrackList().size() + 3);
        setSize(new Dimension(INTERFACE_WIDTH, interfaceHeight));

        infoPanel.update();
        tracksPanel.update();
    }

    // MODIFIES: tracksPanel
    // EFFECTS:  Updates the size of tracksPanel.
    public void updateSize() {
        tracksPanel.updateSize();
    }

    // MODIFIES: project
    // EFFECTS:  Creates a new project.
    public void newProject() {
        setPage(0);

        project = new Project("Untitled Project", 120, NOTES_PER_PAGE);
        project.addTrack("Beep", "beep.wav");
    }

    // MODIFIES: project
    // EFFECTS:  Loads the project file with given fileName.
    //           Updates elements to match new project.
    public void loadProject(String fileName) {
        String projectPath = DIRECTORY_PATH + fileName;

        JsonReader jsonReader = new JsonReader(projectPath);

        try {
            project = jsonReader.loadProject();
        } catch (IOException e) {
            System.out.println("File not found!!");
        }

        updateSize();
        update();
    }

    // EFFECTS: Saves current project to file.
    public void saveProject() {
        try {
            new JsonWriter(project, DIRECTORY_PATH + project.getName() + ".json");
        } catch (FileNotFoundException e) {
            System.out.println("File path not found!!");
        }
    }



    // [HELPER METHODS]

    // MODIFIES: this
    // EFFECTS:  Sets up the JFrame and adds visual elements. Adds a LogPrinter as WindowListener.
    private void initializeJframe() {
        setTitle("Primitive Audio Sequencer");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new LogPrinter());

        addComponents();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  Initializes visual elements and adds them to the JFrame.
    private void addComponents() {
        menuBar = new MenuBar(this);
        infoPanel = new InfoPanel(this);
        tracksPanel = new TracksPanel(this);

        setJMenuBar(menuBar);
        add(infoPanel);
        add(tracksPanel);
    }



    // [SETTERS AND GETTERS]

    public int getPage() {
        return page;
    }

    public void setPage(int index) {
        page = index;
    }

    public Project getProject() {
        return project;
    }
}
