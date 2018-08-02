package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.NoteCommand;
import guru.springframework.recipedemo.recipedemo.domain.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteCommandToNoteTest {

    NoteCommandToNote noteConverter;

    @Before
    public void setUp() throws Exception {
        noteConverter = new NoteCommandToNote();
    }

    @Test
    public void testNull() {
        assertNull(noteConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(noteConverter.convert(new NoteCommand()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String notes = "notes";

        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(id);
        noteCommand.setNotes(notes);

        Note note = noteConverter.convert(noteCommand);
        assertNotNull(note);
        assertEquals(note.getId(), id);
        assertEquals(note.getNotes(), notes);
    }
}
