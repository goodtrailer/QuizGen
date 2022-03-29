package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.MathConstants;
import goodtrailer.quizgen.util.MathUtils;

public class LineIntersectionProblem extends AbstractFrqProblem
{
    public static final int MAX_SLOPE = 12;
    public static final int MAX_Y_INTERSECT = 16;

    private int slope0;
    private int yIntersect0;
    private int slope1;
    private int yIntersect1;
    private double[] intersection;

    public LineIntersectionProblem()
    {
        slope0 = (int) (Math.random() * (MAX_SLOPE + 1));
        yIntersect0 = (int) (Math.random() * (MAX_Y_INTERSECT + 1));
        slope1 = (int) (Math.random() * (MAX_SLOPE + 1));
        yIntersect1 = (int) (Math.random() * (MAX_Y_INTERSECT + 1));
        double x = ((double) yIntersect1 - yIntersect0) / (slope0 - slope1);
        intersection = new double[] { x, slope0 * x + yIntersect0 };
        SetPrompt(String.format(
                "Find the point where the lines { %s } and { %s } intersect. %s and %s are valid.",
                MathUtils.LineAsString(slope0, yIntersect0),
                MathUtils.LineAsString(slope1, yIntersect1),
                MathConstants.DOES_NOT_EXIST,
                MathConstants.TRUE));
    }

    @Override
    public boolean CheckInput(String input)
    {
        input = input.trim();
        
        if (input.isBlank())
            return false;
        
        if (intersection[0] == Double.NaN)
        {
            if (yIntersect0 == yIntersect1)
                return input.equals(MathConstants.TRUE);
            else
                return input.equals(MathConstants.DOES_NOT_EXIST);
        }

        double[] inPoint;
        try
        {
            inPoint = MathUtils.ParsePoint(input);
        }
        catch (NumberFormatException nfe)
        {
            // TODO: handle invalid inputs, maybe enum for correct/incorrect/invalid
            return false;
        }

        if (inPoint.length != 2)
            return false;
        
        return MathUtils.AreEqual(inPoint, intersection);
    }
}
