package goodtrailer.quizgen.util;

// y = mx + b
public record Line(double m, double b)
{
    public static final int DEFAULT_MAX_M = 12;
    public static final int DEFAULT_MAX_B = 16;

    public double evaluate(double x)
    {
        return m * x + b;
    }

    public Line withM(double m)
    {
        return new Line(m, b);
    }

    public Line withB(double b)
    {
        return new Line(m, b);
    }

    public boolean isConstant(int places)
    {
        return MathUtils.areEqual(m, 0, places);
    }

    public boolean isConstant()
    {
        return isConstant(MathConstants.DEFAULT_PLACES);
    }

    public boolean isZero(int places)
    {
        return MathUtils.areEqual(m, 0, places) && MathUtils.areEqual(b, 0, places);
    }

    public boolean isZero()
    {
        return isZero(MathConstants.DEFAULT_PLACES);
    }

    public double distance(Line other)
    {
        var soln = solution(other);

        return switch (soln.type())
        {
        case DNE -> Math.abs(b - other.b) / Math.sqrt(m * other.m + 1);
        default -> 0;
        };
    }

    public Solution solution(Line other, int places)
    {
        double x = ((double) other.b - b) / (m - other.m);
        var point = new Point(x, evaluate(x));
        var type = SolutionType.EXISTS;

        if (MathUtils.areEqual(m, other.m, places))
        {
            boolean same = MathUtils.areEqual(b, other.b, places);
            type = same ? SolutionType.TRUE : SolutionType.DNE;
        }

        return new Solution(type, point);
    }

    public Solution solution(Line other)
    {
        return solution(other, MathConstants.DEFAULT_PLACES);
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
}
