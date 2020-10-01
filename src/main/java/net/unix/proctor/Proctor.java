package net.unix.proctor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

/**
 * @author Unix
 * @since 01.10.2020
 */

public class Proctor
{
    private final DecimalFormat decimalFormat = new DecimalFormat("##.##");

    public void onStart() throws Throwable {
        final File file = new File("images");
        if (!file.exists() && file.mkdirs())
            throw new UnsupportedOperationException("Created images file. Put images with name 1 and 2 with format .jpg in folder images");

        final BufferedImage image1 = ImageIO.read(new File("images/1.jpg")),
                            image2 = ImageIO.read(new File("images/2.jpg"));

        final int width1 = image1.getWidth(),
                  height1 = image1.getHeight(),
                  width2 = image2.getWidth(),
                  height2 = image2.getHeight();

        if (width1 != width2 && height1 != height2)
            throw new UnsupportedOperationException("Both images should have same dimensions");

        long diff = 0;
        for (int i = 0; i < height1; ++i) {
            for (int j = 0; j < width1; ++j) {
                final Color color1 = new Color(image1.getRGB(j, i), true),
                            color2 = new Color(image2.getRGB(j, i), true);
                diff += (Math.abs(color1.getRed() - color2.getRed())
                        + Math.abs(color1.getGreen() - color2.getGreen())
                        + Math.abs(color1.getBlue() - color2.getBlue())
                );
            }
        }

        final double average = diff / (width1 * height2 * 3.D);
        final double percentage = (average / 255.D) * 100.D;
        System.out.println("Average: " + decimalFormat.format(average));
        System.out.println("Diffrence: " + decimalFormat.format(percentage) + "%");
    }
}