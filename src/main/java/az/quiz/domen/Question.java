package az.quiz.domen;
import az.quiz.enums.QuestionResponseSelectionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    String description;

    @CreatedDate
    LocalDateTime createdAt;

    @NotNull
    @Max(11)
    Integer weight;

    @Enumerated(EnumType.STRING)
    QuestionResponseSelectionType type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "question")
    Set<Answer> answers;



}
