package goodtrailer.quizgen.problem;

import javax.swing.JComponent;

import goodtrailer.quizgen.util.Line;
import goodtrailer.quizgen.util.MathConstants;
import goodtrailer.quizgen.util.Point;

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
                "Find the point where the lines { y_0 = %s } and { y_1 = %s } intersect. %s and %s are valid.",
                line0.toString(), line1.toString(), MathConstants.DOES_NOT_EXIST,
                MathConstants.TRUE);
    }

    @Override
    protected JComponent[] getComponents()
    {
        return null;
    }

    @Override
    public Result checkInput(String input)
    {
        input = input.trim();

        if (input.isBlank())
            return Result.INVALID;

        switch (line0.solutionType(line1))
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
