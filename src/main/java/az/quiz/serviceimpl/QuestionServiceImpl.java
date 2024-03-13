package az.quiz.serviceimpl;

import az.quiz.domen.Answer;
import az.quiz.domen.Question;
import az.quiz.dto.request.QuestionRequest;
import az.quiz.error.ApplicationException;
import az.quiz.error.Errors;
import az.quiz.repository.AnswerRepository;
import az.quiz.repository.QuestionRepository;
import az.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<Question> getAllQuestion() {
        return questionRepository.findAll();

    }

    @Override
    public Question getSearchQuestions(UUID searchQuestionById, Pageable pageable) {
        return questionRepository.findById(searchQuestionById)
                .orElseThrow(() -> new ApplicationException(Errors.QUESTION_NOT_FOUND));
    }

    @Override
    public void deleteQuestion(UUID deleteUuid) {
        Question question = questionRepository.findById(deleteUuid)
                .orElseThrow(() -> new ApplicationException(Errors.QUESTION_NOT_FOUND));
        questionRepository.delete(question);
    }
    @Override
    public QuestionRequest createOrUpdate(UUID uuid, QuestionRequest questionDto) {
        log.trace("Update question by id {}, question {}", uuid, questionDto);
        Question questionEntity = questionRepository.findById(uuid)
                .orElseGet(() -> {
                    Question newQuestionEntity = modelMapper.map(questionDto, Question.class);
                    newQuestionEntity.setUuid(uuid);
                    return newQuestionEntity;
                });
        Set<Answer> answers = questionEntity.getAnswers();
        if (answers == null) {
            answers = new HashSet<>();
            questionEntity.setAnswers(answers);
        } else {
            answers.clear();
        }

        modelMapper.map(questionDto, questionEntity);

        answers.forEach(answerEntity -> answerEntity.setQuestion(questionEntity));

        return modelMapper.map(questionRepository.save(questionEntity), QuestionRequest.class);
    }
}












