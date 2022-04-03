package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.Exponential;
import goodtrailer.quizgen.util.Point;
import goodtrailer.quizgen.util.Solution;
import goodtrailer.quizgen.util.SolutionType;

class ExponentialIntersectionProblem extends AbstractFrqProblem
{
    private Exponential exponential0;
    private Exponential exponential1;
    private Solution solution;

    @Override
    protected void initialize()
    {
        exponential0 = Exponential.random();
        exponential1 = Exponential.random().withB(exponential0.b());
        solution = exponential0.solution(exponential1);
    }

    @Override
    protected String getPrompt()
    {
        return String.format(
                "Find the point where the curves { y\u2080 = %s } and { y\u2081 = %s } intersect. %s and %s are valid.",
                exponential0.toString(), exponential1.toString(), SolutionType.DNE,
                SolutionType.TRUE);
    }

    @Override
    protected Result checkInput(String input)
    {
        input = input.trim();

        if (input.isBlank())
            return Result.INVALID;

        SolutionType inType = SolutionType.valueOf(input, SolutionType.EXISTS);
        switch (inType)
        {
        case DNE:
        case TRUE:
            return Result.from(inType == solution.type());
        case EXISTS:
            Point inPoint;
            try
            {
                inPoint = Point.parse(input);
            }
            catch (NumberFormatException nfe)
            {
                return Result.INVALID;
            }

            if (inType != solution.type())
                return Result.INCORRECT;

            if (inPoint.dimensions() != 2)
                return Result.INVALID;

            return Result.from(solution.point().equals(inPoint));
        default:
            throw new IllegalStateException("illegal solution type");
        }
    }
}
