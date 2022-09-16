package ui.graphical;

import ui.audio.ProjectPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// The menu bar in GraphicalInterface that performs actions when items are selected.

public class MenuBar extends JMenuBar implements ActionListener {
    private GraphicalInterface graphical;

    private JMenu menuFile = new JMenu("File");
    private JMenuItem filePlay = new JMenuItem("Play");
    private JMenuItem fileNew = new JMenuItem("New");
    private JMenuItem fileLoad = new JMenuItem("Load");
    private JMenuItem fileSave = new JMenuItem("Save");

    private JMenu menuProject = new JMenu("Project");
    private JMenuItem projectAdd = new JMenuItem("Add");
    private JMenuItem projectRename = new JMenuItem("Rename");
    private JMenuItem projectTempo = new JMenuItem("Tempo");
    private JMenuItem projectExtend = new JMenuItem("Extend");

    private JMenu menuView = new JMenu("View");
    private JMenuItem viewPrev = new JMenuItem("Previous Page");
    private JMenuItem viewNext = new JMenuItem("Next Page");



    // Constructor for MenuBar
    // EFFECTS: Sets graphical to given value and adds menus.
    public MenuBar(GraphicalInterface graphical) {
        this.graphical = graphical;

        addFileMenu();
        addProjectMenu();
        addViewMenu();
    }

    // EFFECTS: Calls helper methods which performs actions corresponding to the selected menu item.
    //          Updates graphical elements.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        checkActionFile(source);
        checkActionProject(source);
        checkActionView(source);

        graphical.update();
    }



    // [HELPER METHODS FOR CONSTRUCTOR]

    // MODIFIES: this, menuFile
    // EFFECTS:  Adds menu items in the File tab.
    private void addFileMenu() {
        add(menuFile);

        filePlay.addActionListener(this);
        fileNew.addActionListener(this);
        fileLoad.addActionListener(this);
        fileSave.addActionListener(this);

        menuFile.add(filePlay);
        menuFile.add(fileNew);
        menuFile.add(fileLoad);
        menuFile.add(fileSave);
    }

    // MODIFIES: this, menuProject
    // EFFECTS:  Adds menu items in the Project tab.
    private void addProjectMenu() {
        add(menuProject);

        projectAdd.addActionListener(this);
        projectRename.addActionListener(this);
        projectTempo.addActionListener(this);
        projectExtend.addActionListener(this);

        menuProject.add(projectAdd);
        menuProject.add(projectRename);
        menuProject.add(projectTempo);
        menuProject.add(projectExtend);
    }

    // MODIFIES: this, menuView
    // EFFECTS:  Adds menu items in the View tab.
    private void addViewMenu() {
        add(menuView);

        viewPrev.addActionListener(this);
        viewNext.addActionListener(this);

        menuView.add(viewPrev);
        menuView.add(viewNext);
    }



    // [HELPER METHODS FOR actionPerformed]

    // MODIFIES: graphicalInterface
    // EFFECTS:  Performs actions according to the selected menu item.
    private void checkActionFile(Object source) {
        if (filePlay.equals(source)) {
            new ProjectPlayer(graphical.getProject());

        } else if (fileNew.equals(source)) {
            graphical.newProject();
            graphical.updateSize();
            JOptionPane.showMessageDialog(graphical,
                    "Created and loaded blank project.","New Project Created", JOptionPane.INFORMATION_MESSAGE);

        } else if (fileLoad.equals(source)) {
            JFileChooser fileChooser = new JFileChooser(GraphicalInterface.DIRECTORY_PATH);
            int option = fileChooser.showOpenDialog(graphical);
            if (option == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                graphical.loadProject(fileName);
            }

        } else if (fileSave.equals(source)) {
            graphical.saveProject();
            JOptionPane.showMessageDialog(graphical,
                    "Project saved successfully.","Project Saved", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: graphicalInterface.getProject()
    // EFFECTS:  Performs actions according to the selected menu item.
    private void checkActionProject(Object source) {
        if (projectAdd.equals(source)) {

            JFileChooser fileChooser = new JFileChooser(GraphicalInterface.DIRECTORY_PATH);
            int option = fileChooser.showOpenDialog(graphical);

            if (option == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                String trackName = JOptionPane.showInputDialog(graphical,"Enter Track Name");
                graphical.getProject().addTrack(trackName, fileName);
            }

            graphical.updateSize();

        } else if (projectTempo.equals(source)) {
            int tempo = Integer.parseInt(
                    JOptionPane.showInputDialog(graphical,"Enter New Tempo (in BPM)"));
            graphical.getProject().setTempo(tempo);

        } else if (projectRename.equals(source)) {
            String name = JOptionPane.showInputDialog(graphical,"Enter New Project Name");
            graphical.getProject().setName(name);

        } else if (projectExtend.equals(source)) {
            graphical.getProject().extendProject(GraphicalInterface.NOTES_PER_PAGE);
            JOptionPane.showMessageDialog(graphical,
                    "Extended the project by one page.","Project Extended",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: graphicalInterface
    // EFFECTS:  Performs actions according to the selected menu item.
    private void checkActionView(Object source) {
        if (viewPrev.equals(source)) {
            graphical.setPage(graphical.getPage() - 1);

        } else if (viewNext.equals(source)) {
            graphical.setPage(graphical.getPage() + 1);
        }
    }
}
