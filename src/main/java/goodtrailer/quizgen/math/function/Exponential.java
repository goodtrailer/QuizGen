package goodtrailer.quizgen.math.function;

import java.util.List;

import goodtrailer.quizgen.math.AbstractFunction;
import goodtrailer.quizgen.math.Interval;
import goodtrailer.quizgen.math.IMathConstants;
import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.Point;
import goodtrailer.quizgen.math.Solution;
import goodtrailer.quizgen.math.SolutionType;

// y = ab^(mx + c)
public class Exponential extends AbstractFunction
{
    public static final int DEFAULT_MAX_A = 10;
    public static final int DEFAULT_MAX_B = 8;
    public static final int DEFAULT_MAX_M = 4;
    public static final int DEFAULT_MAX_C = 3;

    private final double a, b, m, c;

    public Exponential(double a, double b, double m, double c)
    {
        if (b < 0)
            throw new IllegalArgumentException("negative base");

        this.a = a;
        this.b = b;
        this.m = m;
        this.c = c;
    }

    public double a()
    { return a; }

    public double b()
    { return b; }

    public double m()
    { return m; }

    public double c()
    { return c; }

    public Exponential withA(double a)
    { return new Exponential(a, b, m, c); }

    public Exponential withB(double b)
    { return new Exponential(a, b, m, c); }

    public Exponential withM(double m)
    { return new Exponential(a, b, m, c); }

    public Exponential withC(double c)
    { return new Exponential(a, b, m, c); }

    // ------------------------------------------------------------------------------------- customs

    public ExponentialGrowthType growthType(int places)
    {
        if (isConstant() || b < 0)
            return ExponentialGrowthType.NEITHER;

        boolean isGrowth = m > 0;
        if (b < 1)
            isGrowth = !isGrowth;

        return isGrowth ? ExponentialGrowthType.GROWTH : ExponentialGrowthType.DECAY;
    }

    public ExponentialGrowthType growthType()
    { return growthType(IMathConstants.DEFAULT_PLACES); }
    
    public Solution solution(Exponential other)
    { return solution(other, IMathConstants.DEFAULT_PLACES); }

    public Solution solution(Exponential other, int places)
    {
        double lnb0 = Math.log(b);
        double lnb1 = Math.log(other.b);
        double numer = Math.log(other.a / a) + other.c * lnb1 - c * lnb0;
        double denom = m * lnb0 - other.m * lnb1;
        double x = numer / denom;

        var type = Double.isFinite(x) ? SolutionType.EXISTS : SolutionType.DNE;
        var point = new Point(x, evaluate(x));

        boolean constants = isConstant(places) && other.isConstant(places)
                && IMathUtils.equals(evaluate(1), other.evaluate(1), places);
        boolean identical = IMathUtils.equals(a, other.a, places)
                && IMathUtils.equals(b, other.b, places)
                && IMathUtils.equals(m, other.m, places)
                && IMathUtils.equals(c, other.c, places);
        if (constants || identical)
            type = SolutionType.TRUE;

        return new Solution(type, point);
    }

    // ----------------------------------------------------------------------------------- overrides

    @Override
    public double evaluate(double input)
    { return a * Math.pow(b, m * input + c); }

    @Override
    public boolean isConstant(int places)
    {
        return IMathUtils.equals(b, 1, places) || IMathUtils.equals(m, 0, places)
                || isZero(places);
    }

    @Override
    public boolean isZero(int places)
    { return IMathUtils.equals(a, 0, places); }

    @Override
    public List<Interval> domain(int places)
    {
        return List.of(Interval.real());
    }

    @Override
    public List<Interval> range(int places)
    {
        if (isConstant())
            return List.of(Interval.point(evaluate(0)));
        
        if (a > 0)
            return List.of(Interval.real().withLower(0));
        else
            return List.of(Interval.real().withUpper(0));
    }

    @Override
    public String toString(String variable, int places)
    {
        if (isZero(places))
            return "0";

        String coef = IMathUtils.toString(a, places);

        var line = new Linear(m, c);
        if (line.isZero())
            return coef;
        
        String operator = " \u22C5 ";
        
        if (coef.equals("1"))
        {
            coef = "";
            operator = " ";
        }

        String base = String.format(b < 0 ? "(%s)" : "%s", IMathUtils.toString(b, places));

        return String.format("%s%s%s^(%s)", coef, operator, base, line.toString(variable, places));
    }

    // ------------------------------------------------------------------------------------- statics

    public static Exponential random(int maxA, int maxB, int maxM, int maxC)
    {
        int a = IMathUtils.randomInt(maxA);
        int b = IMathUtils.randomInt(0, maxB);
        int m = IMathUtils.randomInt(maxM);
        int c = IMathUtils.randomInt(maxC);
        return new Exponential(a, b, m, c);
    }

    public static Exponential random()
    { return random(DEFAULT_MAX_A, DEFAULT_MAX_B, DEFAULT_MAX_M, DEFAULT_MAX_C); }
    
    public static Exponential constant(double constant)
    { return new Exponential(constant, 1, 0, 0); }
}
