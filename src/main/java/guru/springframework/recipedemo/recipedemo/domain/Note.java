package guru.springframework.recipedemo.recipedemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String notes;

    @OneToOne
    @EqualsAndHashCode.Exclude
    private Recipe recipe;

    public Note() {
    }

    public Note(String notes) {
        this.notes = notes;
    }

}
