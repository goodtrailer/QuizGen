package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.MathUtils;

public class LineDistanceProblem extends AbstractFrqProblem
{
    public static final int MAX_SLOPE = 12;
    public static final int MAX_Y_INTERSECT = 16;

    private int slope;
    private int yIntersect0;
    private int yIntersect1;
    private double distance;

    public LineDistanceProblem()
    {
        slope = (int) (Math.random() * (MAX_SLOPE + 1));
        yIntersect0 = (int) (Math.random() * (MAX_Y_INTERSECT + 1));
        yIntersect1 = (int) (Math.random() * (MAX_Y_INTERSECT + 1));
        distance = Math.abs(yIntersect1 - yIntersect0) / Math.sqrt(slope * slope + 1);

        SetPrompt(String.format("Find the minimum distance between the lines { %s } and { %s }.",
                MathUtils.LineAsString(slope, yIntersect0),
                MathUtils.LineAsString(slope, yIntersect1)));
    }

    @Override
    protected boolean CheckInput(String input)
    {
        double inDistance;
        try
        {
            inDistance = MathUtils.ParseFraction(input);
        }
        catch (NumberFormatException nfe)
        {
            // TODO: handle invalid inputs, maybe enum for correct/incorrect/invalid
            return false;
        }

        return MathUtils.AreEqual(inDistance, distance);
    }
}
