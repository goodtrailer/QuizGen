package goodtrailer.quizgen.math;

import java.util.List;

public interface IFunction
{
    double evaluate(double input);

    boolean isConstant(int places);

    boolean isConstant();

    boolean isZero(int places);

    boolean isZero();

    List<Interval> domain(int places);

    List<Interval> domain();

    List<Interval> range(int places);

    List<Interval> range();

    Solution solution(IFunction otherFunc, int places);

    Solution solution(IFunction otherFunc);

    String toString(String variable, int places);

    String toString(String variable);

    String toString(int places);
}
