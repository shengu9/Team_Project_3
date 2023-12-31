package spring.project.groupware.academy.chatbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.project.groupware.academy.chatbot.dto.AnswerDTO;
import spring.project.groupware.academy.chatbot.dto.ScenarioDTO;
import spring.project.groupware.academy.chatbot.entity.Answer;
import spring.project.groupware.academy.chatbot.entity.Scenario;
import spring.project.groupware.academy.chatbot.repository.AnswerRepository;
import spring.project.groupware.academy.chatbot.repository.ScenarioRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final Random random = new Random();

    public String getResponseByTriggerKeyword(String message) {
        List<Answer> answers = answerRepository.findByTriggerKeywordInMessage(message);

        if (answers.isEmpty()) {
            log.info("트리거 키워드에 대한 응답을 찾을 수 없습니다.");
            return "해당 키워드에 대한 응답을 찾을 수 없습니다.";
        }
        // 리스트를 섞어서 랜덤으로 응답을 선택.
        Collections.shuffle(answers);
        Answer randomAnswer = answers.get(0);

        return randomAnswer.getResponse();
    }
}
