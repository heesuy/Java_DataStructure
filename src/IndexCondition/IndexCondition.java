package IndexCondition;

@FunctionalInterface
public interface IndexCondition {
    boolean guard(int index, int boundary);
}
