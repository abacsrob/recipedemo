package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.NoteCommand;
import guru.springframework.recipedemo.recipedemo.domain.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {

    NoteToNoteCommand noteConverter;

    @Before
    public void setUp() throws Exception {
        noteConverter = new NoteToNoteCommand();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(noteConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(noteConverter.convert(new Note()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String notes = "notes";

        Note note = new Note();
        note.setId(id);
        note.setNotes(notes);

        NoteCommand noteCommand = noteConverter.convert(note);
        assertNotNull(noteCommand);
        assertEquals(noteCommand.getId(), id);
        assertEquals(noteCommand.getNotes(), notes);
    }
}
