package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.Line;
import goodtrailer.quizgen.util.MathConstants;
import goodtrailer.quizgen.util.MathUtils;

public class LineIntersectionProblem extends AbstractFrqProblem
{
    public static final int MAX_SLOPE = 12;
    public static final int MAX_Y_INTERSECT = 16;
    
    private Line line0;
    private Line line1;
    private double[] intersection;

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
                "Find the point where the lines { %s } and { %s } intersect. %s and %s are valid.",
                line0.toString(), line1.toString(), MathConstants.DOES_NOT_EXIST, MathConstants.TRUE);
    }

    @Override
    public boolean checkInput(String input)
    {
        input = input.trim();

        if (input.isBlank())
            return false;
        
        switch (line0.solutionType(line1))
        {
        case DOES_NOT_EXIST:
            return input.equals(MathConstants.DOES_NOT_EXIST);
        case TRUE:
            return input.equals(MathConstants.TRUE);
        case EXISTS:
            double[] inPoint;
            try
            {
                inPoint = MathUtils.parsePoint(input);
            }
            catch (NumberFormatException nfe)
            {
                // TODO: handle invalid inputs, maybe enum for correct/incorrect/invalid
                return false;
            }

            if (inPoint.length != 2)
                return false;

            return MathUtils.areEqual(inPoint, intersection);
        default:
            throw new IllegalStateException("illegal line solution type");
        }
    }
}
