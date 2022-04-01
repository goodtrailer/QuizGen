package goodtrailer.quizgen.util;

// y = ab^(mx + c)
public record Exponential(double a, double b, double m, double c)
{

    public static final int DEFAULT_MAX_A = 8;
    public static final int DEFAULT_MAX_B = 6;
    public static final int DEFAULT_MAX_M = 10;
    public static final int DEFAULT_MAX_C = 12;

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

    public SolutionType solutionType(Exponential other)
    {
        if (Math.signum(b) != Math.signum(other.b))
            return SolutionType.DOES_NOT_EXIST;

        if (b != other.b)
            return SolutionType.HARD;

        if (a == other.a && m == other.m && c == other.c)
            return SolutionType.TRUE;

        double scalar = Math.log((double) other.a / a) / Math.log(b);
        var line0 = new Line(m, c);
        var line1 = new Line(other.m, other.c).scale(scalar);
        return line0.solutionType(line1);
    }

    public Point solution(Exponential other)
    {
        double scalar = Math.log((double) other.a / a) / Math.log(b);
        var line0 = new Line(m, c);
        var line1 = new Line(other.m, other.c).scale(scalar);
        return line0.solution(line1);
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
        int b = MathUtils.randomInt(maxB);
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
        int b = MathUtils.randomInt(maxB);
        int m = 0;
        while (m == 0)
            m = (int)Math.signum(Math.random() - 0.5);
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
