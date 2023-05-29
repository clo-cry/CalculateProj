package alpha.render.core;

public class LookupTables {
    public static float[] sin;
    public static float[] cos;

    public static void init() {
        sin = new float[360];
        cos = new float[360];
        for (int i = 0; i < 360; i++) {
            sin[i] = (float) Math.sin(Math.PI*i/180);
            cos[i] = (float) Math.cos(Math.PI*i/180);
        }
    }
}
