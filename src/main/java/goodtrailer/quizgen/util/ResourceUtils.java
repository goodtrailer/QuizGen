package goodtrailer.quizgen.util;

import javax.swing.ImageIcon;

public final class ResourceUtils
{
    private ResourceUtils()
    {
    }

    public static final ImageIcon getImage(String path)
    {
        if (path.isBlank())
            return null;

        return new ImageIcon(ResourceUtils.class.getResource(path));
    }
}
