package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.NoteCommand;
import guru.springframework.recipedemo.recipedemo.domain.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {
    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand noteCommand) {
        if (noteCommand == null) {
            return null;
        }
        Note note = new Note();
        note.setId(noteCommand.getId());
        note.setNotes(noteCommand.getNotes());
        return note;
    }
}
