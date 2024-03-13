package az.quiz.domen;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid;
    @NotBlank
    String label;

    @NotBlank
    String description;

    boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @Getter(AccessLevel.NONE)
    Question question;


}