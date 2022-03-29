package goodtrailer.quizgen.util;

public abstract class MathUtils
{
    public static double ParseFraction(String string)
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
            return 0.0;
        case 1:
            return Double.parseDouble(subs[0]);
        case 2:
            return Double.parseDouble(subs[0]) / Double.parseDouble(subs[1]);
        default:
            throw new NumberFormatException("multiple fraction bars");
        }
    }

    public static double[] ParsePoint(String string)
    {
        string = string.trim();

        if (string.charAt(0) != '(' || string.charAt(string.length() - 1) != ')')
            throw new NumberFormatException("invalid opening/closing parentheses");

        var components = string.substring(1, string.length() - 1).split(",");
        var point = new double[components.length];
        for (var i = 0; i < components.length; i++)
            point[i] = ParseFraction(components[i]);
        return point;
    }

    public static boolean AreEqual(double a, double b, int places)
    {
        var epsilon = Math.pow(10.0, -places) / 2;
        return Math.abs(a - b) <= epsilon;
    }

    public static boolean AreEqual(double a, double b)
    {
        return AreEqual(a, b, 3);
    }
    
    public static boolean AreEqual(double[] a, double[] b, int places)
    {
        if (a.length != b.length)
            return false;
        
        for (int i = 0; i < a.length; i++)
            if (!AreEqual(a[i], b[i], places))
                return false;
        
        return true;
    }
    
    public static boolean AreEqual(double[] a, double[] b)
    {
        return AreEqual(a, b, 3);
    }

    public static String LineAsString(int slope, int yIntersect)
    {
        return String.format("y = %dx + %d", slope, yIntersect);
    }
}
