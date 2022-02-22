package cope.name.util.renderer;

public class ColorUtil {
    public static float[] fromHex(int color) {
        float alpha = (color >> 24 & 0xff) / 255f;
        float red = (color >> 16 & 0xff) / 255f;
        float green = (color >> 8 & 0xff) / 255f;
        float blue = (color & 0xff) / 255f;

        return new float[] { red, green, blue, alpha };
    }

    public static int fromRGBA(int red, int green, int blue, int alpha) {
        return (red << 16) + (green << 8) + blue + (alpha << 24);
    }
}
