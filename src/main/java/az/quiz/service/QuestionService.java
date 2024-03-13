package az.quiz.service;
import az.quiz.domen.Question;
import az.quiz.dto.request.QuestionRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface QuestionService {

    List<Question> getAllQuestion();

    Question getSearchQuestions(UUID searchQuestionById, Pageable pageable);


    void deleteQuestion(UUID deleteUuid);


    QuestionRequest createOrUpdate(UUID uuid, QuestionRequest questionDto);
}
