package goodtrailer.quizgen.problem;

import javax.swing.JComponent;

public interface IProblem
{
    Result submit();
    JComponent getRootComponent();
}
