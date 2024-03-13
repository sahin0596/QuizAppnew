package az.quiz.dto.response;

import az.quiz.domen.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerResponse {

    UUID uuid;

    String label;


    String description;

    boolean correct;

    Question question;

}
