package az.quiz.dto.request;

import az.quiz.domen.Answer;
import az.quiz.enums.QuestionResponseSelectionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionRequest {

    String description;

    LocalDateTime createdAt;

    Integer weight;

    QuestionResponseSelectionType type;

    Set<Answer> answers;


}
