package goodtrailer.quizgen.problem;

public enum Result
{
    INCORRECT,
    CORRECT,
    INVALID;
    
    public static Result from(boolean isCorrect)
    {
        return isCorrect ? CORRECT : INCORRECT;
    }
}
