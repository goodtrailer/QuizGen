package goodtrailer.quizgen.problem;

import java.awt.Color;

public enum Result
{
    INCORRECT,
    CORRECT,
    INVALID;

    public Color toColor()
    {
        return new Color(switch (this)
        {
        case INCORRECT -> 0xFFA9A9;
        case CORRECT -> 0xD7F4D2;
        case INVALID -> 0xFEC98F;
        });
    }

    public static Result from(boolean isCorrect)
    { return isCorrect ? CORRECT : INCORRECT; }
}
