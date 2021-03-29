package net.unix.proctor;

import net.unix.proctor.util.Pair;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * @author Unix
 * @since 01.10.2020
 */

public class Proctor {

    private final DecimalFormat decimalFormat = new DecimalFormat("##.##");

    public void onStart() throws Throwable {
        final File file = new File("images");

        if (!file.exists() && file.mkdirs()) {
            throw new UnsupportedOperationException("Created images file. Put images with name 1 and 2 with format .jpg in folder images");
        }

        final Pair<BufferedImage, BufferedImage> pair = new Pair<>(ImageIO.read(new File("images/1.jpg")), ImageIO.read(new File("images/2.jpg")));
        final Pair<Integer, Integer> width = new Pair<>(pair.getFirst().getWidth(), pair.getSecond().getWidth()),
                height = new Pair<>(pair.getFirst().getHeight(), pair.getSecond().getHeight());

        if (!width.getFirst().equals(width.getSecond()) && !height.getFirst().equals(height.getSecond())) {
            throw new UnsupportedOperationException("Both images should have same dimensions");
        }

        final AtomicLong diff = new AtomicLong();
        IntStream.range(0, height.getFirst()).forEachOrdered(i -> IntStream.range(0, width.getFirst())
                .mapToObj(j -> new Pair<>(new Color(pair.getFirst().getRGB(j, i), true), new Color(pair.getSecond().getRGB(j, i), true)))
                .forEach(color -> diff.getAndAdd((Math.abs(color.getFirst().getRed() - color.getSecond().getRed())
                        + Math.abs(color.getFirst().getGreen() - color.getSecond().getGreen())
                        + Math.abs(color.getFirst().getBlue() - color.getSecond().getBlue())
                )))
        );

        final double average = diff.get() / (width.getFirst() * height.getSecond() * 3.0D);
        final double percentage = (average / 255.0D) * 100.0D;
        System.out.println("Average: " + decimalFormat.format(average));
        System.out.println("Diffrence: " + decimalFormat.format(percentage) + "%");
    }

}