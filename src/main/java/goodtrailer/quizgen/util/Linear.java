package goodtrailer.quizgen.util;

// y = mx + b
public record Linear(double m, double b)
{
    public static final int DEFAULT_MAX_M = 12;
    public static final int DEFAULT_MAX_B = 16;

    public double evaluate(double x)
    {
        return m * x + b;
    }

    public Linear withM(double m)
    {
        return new Linear(m, b);
    }

    public Linear withB(double b)
    {
        return new Linear(m, b);
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

    public double distance(Linear other)
    {
        var soln = solution(other);

        return switch (soln.type())
        {
        case DNE -> Math.abs(b - other.b) / Math.sqrt(m * other.m + 1);
        default -> 0;
        };
    }

    public Solution solution(Linear other, int places)
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

    public Solution solution(Linear other)
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

    public static Linear random(int maxA, int maxB)
    {
        int m = MathUtils.randomInt(maxA);
        int b = MathUtils.randomInt(maxB);
        return new Linear(m, b);
    }

    public static Linear random()
    {
        return random(DEFAULT_MAX_M, DEFAULT_MAX_B);
    }
}
