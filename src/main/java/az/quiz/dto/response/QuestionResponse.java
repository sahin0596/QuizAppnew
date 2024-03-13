package az.quiz.dto.response;
import az.quiz.domen.Answer;
import az.quiz.enums.QuestionResponseSelectionType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {

    UUID uuid;

    String description;

    LocalDateTime createdAt;

    Integer weight;

    QuestionResponseSelectionType type;

    Set<Answer> answers;


}


   
