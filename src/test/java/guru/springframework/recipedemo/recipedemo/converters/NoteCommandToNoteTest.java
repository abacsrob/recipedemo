package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.NoteCommand;
import guru.springframework.recipedemo.recipedemo.domain.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteCommandToNoteTest {

    NoteCommandToNote noteCommandToNote;

    @Before
    public void setUp() throws Exception {
        noteCommandToNote = new NoteCommandToNote();
    }

    @Test
    public void testNull() {
        assertNull(noteCommandToNote.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(noteCommandToNote.convert(new NoteCommand()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String notes = "notes";

        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(id);
        noteCommand.setNotes(notes);

        Note note = noteCommandToNote.convert(noteCommand);
        assertNotNull(note);
        assertEquals(note.getId(), id);
        assertEquals(note.getNotes(), notes);
    }
}
