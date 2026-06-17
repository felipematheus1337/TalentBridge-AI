package talentbridge.ai.strategies;

public interface TalentStrategy<T> {

    T execute(String candidateId, String jobId);
}
