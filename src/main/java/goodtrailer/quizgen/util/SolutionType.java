package goodtrailer.quizgen.util;

public enum SolutionType
{
    DNE,
    TRUE,
    EXISTS;
    
    public static SolutionType valueOf(String string, SolutionType fallback)
    {
        try
        {
            return SolutionType.valueOf(string);
        }
        catch (IllegalArgumentException iae)
        {
            return fallback;
        }
    }
}
