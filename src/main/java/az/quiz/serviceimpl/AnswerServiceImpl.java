package az.quiz.serviceimpl;

import az.quiz.domen.Answer;
import az.quiz.dto.request.AnswerRequest;
import az.quiz.error.ApplicationException;
import az.quiz.error.Errors;
import az.quiz.repository.AnswerRepository;
import az.quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final  AnswerRepository answerRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getSearchQuestions(UUID searchAnswerById, Pageable pageable) {
        return answerRepository.findById(searchAnswerById)
                .orElseThrow(()-> new ApplicationException(Errors.ANSWER_NOT_FOUND));
    }

    @Override
    public void deleteAnswer(UUID deleteUuid) {
        Answer answer = answerRepository.findById(deleteUuid)
                .orElseThrow(()-> new ApplicationException(Errors.ANSWER_NOT_FOUND));
        answerRepository.delete(answer);

    }
    @Override
    public AnswerRequest createOrUpdate(UUID uuid, AnswerRequest answerDto) {
        log.trace("Update answer by id {}, answer {}", uuid, answerDto);
        Answer answerEntity = answerRepository.findById(uuid)
                .orElseGet(() -> {
                    Answer newAnswerEntity = modelMapper.map(answerDto, Answer.class);
                    newAnswerEntity.setUuid(uuid);
                    return newAnswerEntity;
                });


        modelMapper.map(answerDto, answerEntity);


        return modelMapper.map(answerRepository.save(answerEntity), AnswerRequest.class);
    }
}
