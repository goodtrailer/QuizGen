package goodtrailer.quizgen.util;

// y = ab^(mx + c)
public record Exponential(double a, double b, double m, double c)
{

    public static final int DEFAULT_MAX_A = 8;
    public static final int DEFAULT_MAX_B = 5;
    public static final int DEFAULT_MAX_M = 4;
    public static final int DEFAULT_MAX_C = 3;

    public Exponential(double a, double b, double m, double c)
    {
        if (b < 0)
            throw new IllegalArgumentException("negative base");

        this.a = a;
        this.b = b;
        this.m = m;
        this.c = c;
    }

    public double evaluate(double x)
    {
        return a * Math.pow(b, m * x + c);
    }

    public GrowthType growthType()
    {
        if (m == 0 || b <= 0)
            return GrowthType.NEITHER;
        return m > 0 ? GrowthType.GROWTH : GrowthType.DECAY;
    }

    public boolean isZero(int places)
    {
        return MathUtils.areEqual(a, 0, places) || MathUtils.areEqual(b, 0, places);
    }

    public boolean isZero()
    {
        return isZero(MathConstants.DEFAULT_PLACES);
    }

    public Exponential scale(double scalar)
    {
        return new Exponential(a * scalar, b, m, c);
    }

    public SolutionType solutionType(Exponential other, int places)
    {
        if (Math.signum(b) != Math.signum(other.b))
            return SolutionType.DOES_NOT_EXIST;

        if (MathUtils.areEqual(a, other.a, places)
                && MathUtils.areEqual(b, other.b, places)
                && MathUtils.areEqual(m, other.m, places)
                && MathUtils.areEqual(c, other.c, places))
            return SolutionType.TRUE;

        double lnb0 = Math.log(b);
        double lnb1 = Math.log(other.b);
        double numer = Math.log(other.a / a) + other.c * lnb1 - c * lnb0;
        double denom = m * lnb0 - other.m * lnb1;
        return Double.isFinite(numer / denom) ? SolutionType.EXISTS : SolutionType.DOES_NOT_EXIST;
    }
    
    public SolutionType solutionType(Exponential other)
    {
        return solutionType(other, 3);
    }

    public Point solution(Exponential other)
    {
        double lnb0 = Math.log(b);
        double lnb1 = Math.log(other.b);
        double numer = Math.log(other.a / a) + other.c * lnb1 - c * lnb0;
        double denom = m * lnb0 - other.m * lnb1;
        double x = numer / denom;
        return new Point(x, evaluate(x));
    }

    public String toString(int places)
    {
        if (isZero(places))
            return "0";

        String coefficient = MathUtils.toString(a, places);

        var line = new Line(m, c);
        if (line.isZero())
            return coefficient;

        String base = String.format(b < 0 ? "(%s)" : "%s", MathUtils.toString(b, places));
        return String.format("%s \u22C5 %s^(%s)", coefficient, base, line.toString(places));
    }

    @Override
    public String toString()
    {
        return toString(MathConstants.DEFAULT_PLACES);
    }

    public static Exponential random(int maxA, int maxB, int maxM, int maxC)
    {
        int a = MathUtils.randomInt(maxA);
        int b = Math.abs(MathUtils.randomInt(maxB));
        int m = MathUtils.randomInt(maxM);
        int c = MathUtils.randomInt(maxC);
        return new Exponential(a, b, m, c);
    }

    public static Exponential random()
    {
        return random(DEFAULT_MAX_A, DEFAULT_MAX_B, DEFAULT_MAX_M, DEFAULT_MAX_C);
    }

    public static Exponential randomLikeBase(Exponential other, int maxA, int maxM, int maxC)
    {
        int a = MathUtils.randomInt(maxA);
        int m = MathUtils.randomInt(maxM);
        int c = MathUtils.randomInt(maxC);
        return new Exponential(a, other.b, m, c);
    }

    public static Exponential randomLikeBase(Exponential other)
    {
        return randomLikeBase(other, DEFAULT_MAX_A, DEFAULT_MAX_M, DEFAULT_MAX_B);
    }

    public static Exponential randomSimple(int maxA, int maxB)
    {
        int a = MathUtils.randomInt(maxA);
        int b = Math.abs(MathUtils.randomInt(maxB));
        int m = 0;
        while (m == 0)
            m = (int) Math.signum(Math.random() - 0.5);
        return new Exponential(a, b, m, 0);
    }

    public static Exponential randomSimple()
    {
        return randomSimple(DEFAULT_MAX_A, DEFAULT_MAX_B);
    }

    public enum GrowthType
    {
        GROWTH,
        DECAY,
        NEITHER;

        @Override
        public String toString()
        {
            var string = super.toString();
            return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        }
    }
}
