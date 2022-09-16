package ui.graphical.buttons;

import ui.graphical.GraphicalInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// The button in TrackPanel responsible for toggling a certain note.

public class NoteToggleButton extends ToggleButtonAbstract implements ActionListener {
    private static final int NOTES_PER_PAGE = GraphicalInterface.NOTES_PER_PAGE;
    private int noteIndex;
    private int currentIndex;

    // Constructor for NoteToggleButton
    // EFFECTS: Calls super(), sets noteIndex to given value, sets button label to index mod 16,
    //          and adds this as actionListener.
    public NoteToggleButton(int trackNumber, int noteIndex, GraphicalInterface graphical) {
        super(trackNumber, graphical);

        this.noteIndex = noteIndex;

        setText(Integer.toString(noteIndex % 16 + 1));
        addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  Resets selected state to corresponding note's state.
    @Override
    public void update() {
        int page = graphical.getPage();
        currentIndex = noteIndex + (NOTES_PER_PAGE * page);
        setSelected(graphical.getProject().getTrack(trackNumber).getNote(currentIndex));
    }

    // MODIFIES: graphicalInterface.getProject().getTrack(trackNumber)
    // EFFECTS:  Toggles corresponding note in corresponding track.
    @Override
    public void actionPerformed(ActionEvent e) {
        graphical.getProject().getTrack(trackNumber).toggleNote(currentIndex);
    }
}
