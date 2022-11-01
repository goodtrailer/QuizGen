package goodtrailer.quizgen.math;

import goodtrailer.quizgen.problem.Result;

public enum SolutionType
{
    DNE,
    TRUE,
    EXISTS;
    
    public Result equals(SolutionType other)
    {
        return Result.from(this == other);
    }

    public static SolutionType valueOf(String string, SolutionType fallback)
    {
        try
        {
            return valueOf(string);
        }
        catch (IllegalArgumentException iae)
        {
            return fallback;
        }
    }
}
