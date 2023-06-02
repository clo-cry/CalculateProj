package beta;

import beta.test.L.simpleSoundPlayer;

import java.io.File;

public class ReadSign {
    public static void read(String str) {
        char[] si = str.toCharArray();
        char s = 0;
        char[] signs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '万', '十', '千', '点', '百'};
//        long count = System.currentTimeMillis();
        for (char c : si) {
            switch (c) {
                case '0' -> s = signs[0];
                case '1' -> s = signs[1];
                case '2' -> s = signs[2];
                case '3' -> s = signs[3];
                case '4' -> s = signs[4];
                case '5' -> s = signs[5];
                case '6' -> s = signs[6];
                case '7' -> s = signs[7];
                case '8' -> s = signs[8];
                case '9' -> s = signs[9];
                case '万' -> s = signs[10];
                case '十' -> s = signs[11];
                case '千' -> s = signs[12];
                case '.' -> s = signs[13];
                case '百' -> s = signs[14];
            }

            char finalS = s;

//            long num;
//
//            while (true) {
//                num = System.currentTimeMillis();
//                if ((num - count) > 1000) {
//                    count = num;
//                    break;
//                }
//            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                return;
            }

            Runnable runnable = () -> {
                String fileName = finalS + ".wav";
                String thePath = new File("").getAbsolutePath() + "/Test/sound/" + fileName;
                simpleSoundPlayer player = new simpleSoundPlayer();
                player.play(thePath);
            };
            if (VolatileCalculator.needRead) {
                Thread thread = new Thread(runnable, "read");
                thread.start();

            } else
                Thread.currentThread().interrupt();

        }
    }
}

