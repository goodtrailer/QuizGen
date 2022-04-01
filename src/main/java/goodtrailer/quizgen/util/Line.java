package goodtrailer.quizgen.util;

// y = mx + b
public record Line(double m, double b)
{
    public static final int DEFAULT_MAX_M = 12;
    public static final int DEFAULT_MAX_B = 16;

    public double distance(Line other)
    {
        if (other.m != m)
            throw new IllegalArgumentException("non-parallel lines");

        return Math.abs(b - other.b) / Math.sqrt(m * other.m + 1);
    }
    
    public double evaluate(double x)
    {
        return m * x + b;
    }
    
    public boolean isZero(int places)
    {
        return MathUtils.areEqual(m, 0, places) && MathUtils.areEqual(b, 0, places);
    }
    
    public boolean isZero()
    {
        return isZero(MathConstants.DEFAULT_PLACES);
    }
    
    public Line offset(double offset)
    {
        return new Line(m, b + offset);
    }

    public Line scale(double scalar)
    {
        return new Line(scalar * m, scalar * b);
    }

    public SolutionType solutionType(Line other)
    {
        if (other.m == m)
            return other.b == b ? SolutionType.TRUE : SolutionType.DOES_NOT_EXIST;

        return SolutionType.EXISTS;
    }

    public Point solution(Line other)
    {
        double x = ((double) other.b - b) / (m - other.m);
        return new Point(x, m * x + b);
    }

    public String toString(int places)
    {
        if (MathUtils.areEqual(m, 0, places))
            return MathUtils.toString(b, places);

        if (MathUtils.areEqual(b, 0, places))
            return MathUtils.toString(m, places) + "x";

        return (m == 1 ? "" : MathUtils.toString(m, places)) + "x"
                + (b > 0 ? " + " : " \u2013 ") + MathUtils.toString(Math.abs(b), places);
    }

    @Override
    public String toString()
    {
        return toString(MathConstants.DEFAULT_PLACES);
    }

    public static Line random(int maxA, int maxB)
    {
        int m = MathUtils.randomInt(maxA);
        int b = MathUtils.randomInt(maxB);
        return new Line(m, b);
    }

    public static Line random()
    {
        return random(DEFAULT_MAX_M, DEFAULT_MAX_B);
    }

    public static Line randomParallel(Line other, int maxB)
    {
        int b = MathUtils.randomInt(maxB);
        return new Line(other.m, b);
    }

    public static Line randomParallel(Line other)
    {
        return randomParallel(other, DEFAULT_MAX_B);
    }
}
