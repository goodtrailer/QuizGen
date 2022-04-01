package goodtrailer.quizgen.util;

import java.util.concurrent.ThreadLocalRandom;
import java.text.DecimalFormat;

public final class MathUtils
{
    private MathUtils()
    {
    }

    public static double parseFraction(String string)
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

    public static boolean areEqual(double a, double b, int places)
    {
        var epsilon = Math.pow(10.0, -places) / 2;
        return Math.abs(a - b) <= epsilon;
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
