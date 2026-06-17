package talentbridge.ai.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import talentbridge.ai.llm.LLMOpenAIClient;
import talentbridge.ai.llm.PromptConfig;

import java.nio.charset.Charset;

@Configuration
public class ApplicationConfiguration {

    private static final String SYSTEM_TEMPLATE_PATH_FORMAT = "classpath:prompts/%s/system.txt";
    private static final String USER_TEMPLATE_PATH_FORMAT = "classpath:prompts/%s/user.txt";

    private final ResourceLoader resourceLoader;

    public ApplicationConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper.builder().build();
    }

    @Bean
    public LLMOpenAIClient llmOpenAIClient(ChatClient.Builder builder, JsonMapper jsonMapper) {
        return new LLMOpenAIClient(
                builder.build(),
                jsonMapper,
                getPrompt("resume"),
                getPrompt("fitting")
        );
    }

    private PromptConfig getPrompt(String feature) {
        var system = getResourceContent(String.format(SYSTEM_TEMPLATE_PATH_FORMAT, feature));
        var user = getResourceContent(String.format(USER_TEMPLATE_PATH_FORMAT, feature));
        return new PromptConfig(system, user);
    }

    private String getResourceContent(String resourcePath) {
        try {
            var resource = this.resourceLoader.getResource(resourcePath);
            return resource.getContentAsString(Charset.defaultCharset());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load resource: " + resourcePath, ex);
        }
    }
}
