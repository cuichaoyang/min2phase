package cubestormer;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.awt.*;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author cuiguiyang
 * @since 2020/10/17 23:18
 */
public class ColorAnalysis {

    public static void main(String[] args) {
        Range<Integer> integerRange = Range.closed(1, 10);
        boolean contains = integerRange.contains(11);
        System.out.println("contains = " + contains);
    }

    public static ColorFacelet getColorFacelet(int rgb) {
        return ColorRGB.getColorFacelet(new Color(rgb));
    }


    @Getter
    @AllArgsConstructor
    enum ColorRGB {
        black(Range.closed(0, 30), Range.closed(0, 30), Range.closed(0, 30), ""),
        green(Range.closed(0, 65), Range.closed(100, 230), Range.closed(30, 180), "F"),
        blue(Range.closed(0, 75), Range.closed(25, 135), Range.closed(90, 215), "B"),
        red(Range.closed(130, 255), Range.closed(5, 60), Range.closed(5, 80), "R"),
        yellow(Range.closed(150, 240), Range.closed(150, 230), Range.closed(0, 60), "D"),
        orange(Range.closed(160, 255), Range.closed(70, 150), Range.closed(3, 80), "L"),
        white(Range.closed(175, 255), Range.closed(150, 255), Range.closed(130, 255), "U"),
        ;

        private Range<Integer> rangeR;
        private Range<Integer> rangeG;
        private Range<Integer> rangeB;
        private String facelets;

        private static ColorFacelet defaultColorFacelet =
                ColorFacelet.builder().color(black.name()).facelets(black.facelets).build();

        public static ColorFacelet getColorFacelet(Color color) {
            int r = color.getRed(), g = color.getGreen(), b = color.getBlue();
            ColorFacelet colorFacelet = defaultColorFacelet;
            for (ColorRGB colorRGB : values()) {
                Range<Integer> rangeR = colorRGB.getRangeR();
                Range<Integer> rangeG = colorRGB.getRangeG();
                Range<Integer> rangeB = colorRGB.getRangeB();
                if (rangeR.contains(r) && rangeG.contains(g) && rangeB.contains(b)) {
                    colorFacelet = ColorFacelet.builder().color(colorRGB.name()).facelets(colorRGB.facelets).build();
                }
            }
            System.out.printf("r = %3d, g = %3d, b = %3d ===> %s\n", r, g, b, colorFacelet.toString());
            return colorFacelet;
        }
    }

    @Data
    @Builder
    static class ColorFacelet {
        private String color;
        private String facelets;
    }
}

