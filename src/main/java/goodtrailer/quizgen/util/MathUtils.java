package goodtrailer.quizgen.util;

import java.util.concurrent.ThreadLocalRandom;
import java.text.DecimalFormat;

public final class MathUtils
{
    private MathUtils()
    {
    }

    public static double parseFraction(String string) throws NumberFormatException
    {
        string = string.trim();

        if (string.isEmpty())
            throw new NumberFormatException("empty string");

        if (string.charAt(0) == '/')
            throw new NumberFormatException("starts with fraction bar");

        var subs = string.split("/");
        switch (subs.length)
        {
        case 0:
            throw new NumberFormatException("empty string");
        case 1:
            return Double.parseDouble(subs[0]);
        case 2:
            return Double.parseDouble(subs[0]) / Double.parseDouble(subs[1]);
        default:
            throw new NumberFormatException("multiple fraction bars");
        }
    }

    public static double parsePercentage(String string) throws NumberFormatException
    {
        string = string.trim();

        if (string.isEmpty())
            throw new NumberFormatException("empty string");

        if (string.charAt(0) == '%')
            throw new NumberFormatException("starts with percentage symbol '%'");

        if (string.charAt(string.length() - 1) != '%')
            throw new NumberFormatException("does not end with percentage symbol '%'");

        return Double.parseDouble(string.substring(0, string.length() - 1)) / 100.;
    }
    
    public static boolean areEqual(double a, double b, double epsilon)
    {
        return Math.abs(a - b) <= epsilon;
    }

    public static boolean areEqual(double a, double b, int places, double leeway)
    {
        return areEqual(a, b, Math.pow(10.0, -places) / (2.0 - leeway));
    }

    public static boolean areEqual(double a, double b, int places)
    {
        return areEqual(a, b, places, MathConstants.DEFAULT_LEEWAY);
    }

    public static boolean areEqual(double a, double b)
    {
        return areEqual(a, b, MathConstants.DEFAULT_PLACES);
    }

    public static String toString(double a, int places)
    {
        if (places < 0)
            throw new IllegalArgumentException("negative places");

        String format = "0";
        if (places > 0)
            format += "." + "#".repeat(places);

        return new DecimalFormat(format).format(a);
    }

    public static String toString(double a)
    {
        return toString(a, MathConstants.DEFAULT_PLACES);
    }

    public static int randomInt(int inclusiveMax)
    {
        return ThreadLocalRandom.current().nextInt(-inclusiveMax, inclusiveMax + 1);
    }

    public static double randomDouble(double max)
    {
        return ThreadLocalRandom.current().nextDouble(-max, max);
    }
}
