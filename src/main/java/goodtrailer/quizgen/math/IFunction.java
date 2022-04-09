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

    public static IFunction zero()
    {
        return new AbstractFunction()
        {

            @Override
            public double evaluate(double input)
            { return 0; }

            @Override
            public boolean isConstant(int places)
            { return true; }

            @Override
            public boolean isZero(int places)
            { return true; }

            @Override
            public List<Interval> domain(int places)
            { return List.of(Interval.real()); }

            @Override
            public List<Interval> range(int places)
            { return List.of(Interval.point(0)); }

            @Override
            public Solution solution(IFunction otherFunc, int places)
            {
                var type = IMathUtils.areEqual(otherFunc.evaluate(0), 0, places)
                        ? SolutionType.EXISTS
                        : SolutionType.DNE;
                var point = Point.zero(2);

                return new Solution(type, point);
            }

            @Override
            public String toString(String variable, int places)
            { return "0"; }
        };
    }
}
