package az.quiz.controller;
import az.quiz.domen.Question;
import az.quiz.dto.request.QuestionRequest;
import az.quiz.serviceimpl.QuestionServiceImpl;
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
@RequestMapping("/q1")
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @GetMapping()
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/{searchQuestionById}/pageable")
    public Question getSearchQuestionById(
            @PathVariable UUID searchQuestionById, Pageable pageable) {
        return questionService.getSearchQuestions(searchQuestionById, pageable);
    }

    @DeleteMapping("/{deleteUuid}")
    public void  deleteQuestion(@PathVariable UUID deleteUuid){
        questionService.deleteQuestion(deleteUuid);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<QuestionRequest> createOrUpdate(@PathVariable UUID uuid,
                                                          @RequestBody @Valid QuestionRequest questionDto) {
        log.trace("Get question by uuid {} ", uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body (questionService.createOrUpdate(uuid, questionDto));
    }





}






