import java.util.function.Function;

public interface UseLambdaOnIt {
    <Gets, Retuns> Retuns useLambdaInArg(Function<Gets, Retuns> f);
}
