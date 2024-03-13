package az.quiz.controller;

import az.quiz.domen.Answer;
import az.quiz.domen.Question;
import az.quiz.dto.request.AnswerRequest;
import az.quiz.serviceimpl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/a1")
public class AnswerController {

    private final AnswerServiceImpl answerService;
    @GetMapping()
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }
    @GetMapping("/{searchAnswerById}/pageable")
    public Answer getSearchAnswerById(
            @PathVariable UUID searchAnswerById, Pageable pageable) {
        return answerService.getSearchQuestions(searchAnswerById, pageable);
    }
    @DeleteMapping("/{deleteUuid}")
    public void  deleteAnswer(@PathVariable UUID deleteUuid){
        answerService.deleteAnswer(deleteUuid);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<AnswerRequest> createOrUpdate(@PathVariable UUID uuid,
                                                        @RequestBody @Valid AnswerRequest answerDto) {
        log.trace("Get  answer by uuid {} ", uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body (answerService.createOrUpdate(uuid, answerDto));
    }




}
