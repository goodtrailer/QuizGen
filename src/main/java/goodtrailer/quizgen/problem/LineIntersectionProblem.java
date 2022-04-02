package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.Line;
import goodtrailer.quizgen.util.Point;
import goodtrailer.quizgen.util.SolutionType;

public class LineIntersectionProblem extends AbstractFrqProblem
{
    private Line line0;
    private Line line1;
    private Point intersection;

    @Override
    protected void initialize()
    {
        line0 = Line.random();
        line1 = Line.random();
        intersection = line0.solution(line1);
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
            return Result.from(inType == line0.solutionType(line1));
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

            if (inType != line0.solutionType(line1))
                return Result.INCORRECT;

            if (inPoint.dimensions() != 2)
                return Result.INVALID;

            return Result.from(intersection.equals(inPoint));
        default:
            throw new IllegalStateException("illegal solution type");
        }
    }
}
