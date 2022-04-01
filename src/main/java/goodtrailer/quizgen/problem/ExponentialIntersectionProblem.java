package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.Exponential;
import goodtrailer.quizgen.util.MathConstants;
import goodtrailer.quizgen.util.Point;

public class ExponentialIntersectionProblem extends AbstractFrqProblem
{
    private Exponential exponential0;
    private Exponential exponential1;
    private Point intersection;

    @Override
    protected void initialize()
    {
        exponential0 = Exponential.random();
        exponential1 = Exponential.randomLikeBase(exponential0);
        intersection = exponential0.solution(exponential1);
    }

    @Override
    protected String getPrompt()
    {
        return String.format(
                "Find the point where the curves { y\u2080 = %s } and { y\u2081 = %s } intersect. %s and %s are valid.",
                exponential0.toString(), exponential1.toString(), MathConstants.DOES_NOT_EXIST,
                MathConstants.TRUE);
    }

    @Override
    protected Result checkInput(String input)
    {
        input = input.trim();

        if (input.isBlank())
            return Result.INVALID;

        switch (exponential0.solutionType(exponential1))
        {
        case DOES_NOT_EXIST:
            return Result.from(input.equals(MathConstants.DOES_NOT_EXIST));
        case TRUE:
            return Result.from(input.equals(MathConstants.TRUE));
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

            if (inPoint.dimensions() != 2)
                return Result.INVALID;

            return Result.from(intersection.equals(inPoint));
        default:
            throw new IllegalStateException("illegal line solution type");
        }
    }
}
