package talentbridge.ai.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import talentbridge.ai.llm.LLM_EVENT;
import talentbridge.ai.strategies.TalentStrategy;
import talentbridge.ai.utils.LLMUtils;

@Component
@RequiredArgsConstructor
public class LLMFacade {

    private final LLMUtils llmUtils;

    @SuppressWarnings("unchecked")
    public <T> T execute(String candidateId, String jobId, LLM_EVENT event) {
        if (event == null) throw new RuntimeException();

        TalentStrategy<T> strategy = (TalentStrategy<T>) llmUtils.getStrategyByEvent(event);

        return strategy.execute(candidateId, jobId);
    }
}
