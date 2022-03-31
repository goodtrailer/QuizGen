package goodtrailer.quizgen.problem;

public enum Result
{
    INCORRECT,
    CORRECT,
    INVALID;
    
    public static Result fromBoolean(boolean isCorrect)
    {
        return isCorrect ? CORRECT : INCORRECT;
    }
}
