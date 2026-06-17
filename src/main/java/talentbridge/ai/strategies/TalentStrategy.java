package talentbridge.ai.strategies;

public interface TalentStrategy<T> {

    T execute(Object... args);
}
