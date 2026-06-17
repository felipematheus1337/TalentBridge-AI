package talentbridge.ai.llm;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.ai.chat.client.ChatClient;
import talentbridge.ai.dto.JobDetails;

public class LLMOpenAIClient {

    private final ChatClient chatClient;
    private final JsonMapper jsonMapper;
    private final PromptConfig resumePromptConfig;
    private final PromptConfig fittingPromptConfig;

    public LLMOpenAIClient(ChatClient chatClient, JsonMapper jsonMapper,
                           PromptConfig resumePromptConfig, PromptConfig fittingPromptConfig) {
        this.chatClient = chatClient;
        this.jsonMapper = jsonMapper;
        this.resumePromptConfig = resumePromptConfig;
        this.fittingPromptConfig = fittingPromptConfig;
    }

    public String generateResume(String candidateResume, String jobDescription) {
        return chatClient.prompt()
                .system(resumePromptConfig.system())
                .user(u -> u.text(resumePromptConfig.user())
                        .param("userResume", candidateResume)
                        .param("jobDescription", jobDescription))
                .call()
                .content();
    }

    public JobDetails evaluateFitting(String candidateResume, String jobDescription) {
        return chatClient.prompt()
                .system(fittingPromptConfig.system())
                .user(u -> u.text(fittingPromptConfig.user())
                        .param("userResume", candidateResume)
                        .param("jobDescription", jobDescription))
                .call()
                .entity(JobDetails.class);
    }
}
