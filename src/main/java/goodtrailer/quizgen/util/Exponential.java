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

    public Exponential withA(double a)
    {
        return new Exponential(a, b, m, c);
    }

    public Exponential withB(double b)
    {
        return new Exponential(a, b, m, c);
    }

    public Exponential withM(double m)
    {
        return new Exponential(a, b, m, c);
    }

    public Exponential withC(double c)
    {
        return new Exponential(a, b, m, c);
    }

    public boolean isConstant(int places)
    {
        return MathUtils.areEqual(b, 1, places) || MathUtils.areEqual(m, 0, places)
                || isZero(places);
    }

    public boolean isConstant()
    {
        return isConstant(MathConstants.DEFAULT_PLACES);
    }

    public boolean isZero(int places)
    {
        return MathUtils.areEqual(a, 0, places) || MathUtils.areEqual(b, 0, places);
    }

    public boolean isZero()
    {
        return isZero(MathConstants.DEFAULT_PLACES);
    }

    public GrowthType growthType(int places)
    {
        if (isConstant() || b < 0)
            return GrowthType.NEITHER;

        boolean isGrowth = m > 0;
        if (b < 1)
            isGrowth = !isGrowth;

        return isGrowth ? GrowthType.GROWTH : GrowthType.DECAY;
    }

    public GrowthType growthType()
    {
        return growthType(MathConstants.DEFAULT_PLACES);
    }

    public Solution solution(Exponential other, int places)
    {
        double lnb0 = Math.log(b);
        double lnb1 = Math.log(other.b);
        double numer = Math.log(other.a / a) + other.c * lnb1 - c * lnb0;
        double denom = m * lnb0 - other.m * lnb1;
        double x = numer / denom;

        var type = Double.isFinite(numer / denom) ? SolutionType.EXISTS : SolutionType.DNE;
        var point = new Point(x, evaluate(x));

        boolean zeros = isZero(places) && other.isZero(places);
        boolean equal = MathUtils.areEqual(a, other.a, places)
                && MathUtils.areEqual(b, other.b, places)
                && MathUtils.areEqual(m, other.m, places)
                && MathUtils.areEqual(c, other.c, places);
        if (zeros || equal)
            type = SolutionType.TRUE;

        return new Solution(type, point);
    }

    public Solution solution(Exponential other)
    {
        return solution(other, 3);
    }

    public String toString(String variable, int places)
    {
        if (isZero(places))
            return "0";

        String coef = MathUtils.toString(a, places);

        var line = new Linear(m, c);
        if (line.isZero())
            return coef;

        String base = String.format(b < 0 ? "(%s)" : "%s", MathUtils.toString(b, places));
        
        return String.format("%s \u22C5 %s^(%s)", coef, base, line.toString(variable, places));
    }

    public String toString(int places)
    {
        return toString(MathConstants.DEFAULT_VARIABLE, places);
    }
    
    public String toString(String variable)
    {
        return toString(variable, MathConstants.DEFAULT_PLACES);
    }

    @Override
    public String toString()
    {
        return toString(MathConstants.DEFAULT_VARIABLE, MathConstants.DEFAULT_PLACES);
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
