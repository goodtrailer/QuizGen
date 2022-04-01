package goodtrailer.quizgen.util;

import javax.swing.ImageIcon;

public final class ResourceUtils
{
    private ResourceUtils()
    {
    }

    public static final ImageIcon getImage(Object origin, String path)
    {
        var resource = origin.getClass().getResource(path);
        if (resource == null)
            return null;
        return new ImageIcon(resource);
    }

    public static final ImageIcon getImage(Class<?> origin, String path)
    {
        var resource = origin.getResource(path);
        if (resource == null)
            return null;
        return new ImageIcon(resource);
    }
}
