package goodtrailer.quizgen.app.problem.chapter10;

import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.Linear;
import goodtrailer.quizgen.util.Point;
import goodtrailer.quizgen.util.Solution;
import goodtrailer.quizgen.util.SolutionType;

class LineIntersectionProblem extends AbstractFrqProblem
{
    private Linear line0;
    private Linear line1;
    private Solution solution;

    @Override
    protected void initialize()
    {
        line0 = Linear.random();
        line1 = Linear.random();
        solution = line0.solution(line1);
    }

    @Override
    protected String getPrompt()
    {
        return String.format(
                "Find the point where the lines { y\u2080 = %s } and { y\u2081 = %s } intersect. %s and %s are valid.",
                line0.toString(), line1.toString(), SolutionType.DNE, SolutionType.TRUE);
    }

    @Override
    public Result checkInput(String input)
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

            if (!inPoint.is2d())
                return Result.INVALID;

            return Result.from(solution.point().equals(inPoint));
        default:
            throw new IllegalStateException("illegal solution type");
        }
    }
}
