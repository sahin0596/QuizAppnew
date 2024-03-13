package az.quiz.service;

import az.quiz.domen.Answer;
import az.quiz.dto.request.AnswerRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AnswerService {


    List<Answer> getAllAnswers();

    Answer getSearchQuestions(UUID searchAnswerById, Pageable pageable);

    void deleteAnswer(UUID deleteUuid);

    AnswerRequest createOrUpdate(UUID uuid, AnswerRequest answerDto);
}
