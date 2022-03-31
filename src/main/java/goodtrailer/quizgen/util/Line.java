package goodtrailer.quizgen.util;

public record Line(int slope, int yIntercept)
{
    public static final int DEFAULT_MAX_SLOPE = 12;
    public static final int DEFAULT_MAX_Y_INTERCEPT = 16;

    public double distance(Line other)
    {
        if (other.slope != slope)
            throw new IllegalArgumentException("non-parallel lines");

        return Math.abs(yIntercept - other.yIntercept) / Math.sqrt(slope * other.slope + 1);
    }

    public SolutionType solutionType(Line other)
    {
        if (other.slope == slope)
            return other.yIntercept == yIntercept ? SolutionType.TRUE : SolutionType.DOES_NOT_EXIST;

        return SolutionType.EXISTS;
    }
    
    public double[] solution(Line other)
    {
        double x = ((double) other.yIntercept - yIntercept) / (slope - other.slope);
        return new double[] { x, slope * x + yIntercept };
    }
    
    public String toString()
    {
        String operation = yIntercept >= 0 ? "+" : "-";
        return String.format("y = %dx %s %d", slope, operation, Math.abs(yIntercept));
    }

    public static Line random(int maxSlope, int maxYIntercept)
    {
        int slope = MathUtils.randomInt(maxSlope);
        int yIntercept = MathUtils.randomInt(maxYIntercept);
        return new Line(slope, yIntercept);
    }

    public static Line random()
    {
        return random(DEFAULT_MAX_SLOPE, DEFAULT_MAX_Y_INTERCEPT);
    }

    public static Line randomParallel(Line line, int maxYIntercept)
    {
        int yIntercept = MathUtils.randomInt(maxYIntercept);
        return new Line(line.slope, yIntercept);
    }

    public static Line randomParallel(Line line)
    {
        return randomParallel(line, DEFAULT_MAX_Y_INTERCEPT);
    }
}
