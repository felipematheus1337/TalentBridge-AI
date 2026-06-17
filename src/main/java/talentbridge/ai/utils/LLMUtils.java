package talentbridge.ai.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import talentbridge.ai.llm.LLM_EVENT;
import talentbridge.ai.strategies.FittingStrategy;
import talentbridge.ai.strategies.ResumeStrategy;
import talentbridge.ai.strategies.TalentStrategy;

@Component
@RequiredArgsConstructor
public class LLMUtils {

    public static final LLM_EVENT RESUME_EVENT = LLM_EVENT.RESUME;
    public static final LLM_EVENT FITTING_EVENT = LLM_EVENT.FITTING;

    private final ResumeStrategy resumeStrategy;
    private final FittingStrategy fittingStrategy;

    public TalentStrategy<?> getStrategyByEvent(LLM_EVENT event) {
        return switch (event) {
            case RESUME -> resumeStrategy;
            case FITTING -> fittingStrategy;
            case null, default -> throw new IllegalArgumentException("Unsupported LLM_EVENT: " + event);
        };
    }
}
