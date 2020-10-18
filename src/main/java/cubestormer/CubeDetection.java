package cubestormer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Intellij IDEA.
 *
 * @author 17893
 * @since 2020/10/17 12:44
 */
public class CubeDetection {

    private static String[] order = {"U1", "R1", "F1", "D1", "L1", "B1"};

    private static Position[] positions = {
            new Position(PositionEnum.TOP_LEFT, 500, 500),
            new Position(PositionEnum.TOP_MID, 1500, 500),
            new Position(PositionEnum.TOP_RIGHT, 2500, 500),

            new Position(PositionEnum.MID_LEFT, 500, 1400),
            new Position(PositionEnum.MID_MID, 1500, 1400),
            new Position(PositionEnum.MID_RIGHT, 2500, 1400),

            new Position(PositionEnum.BOTTOM_LEFT, 500, 2320),
            new Position(PositionEnum.BOTTOM_MID, 1500, 2320),
            new Position(PositionEnum.BOTTOM_RIGHT, 2500, 2320),
    };

    public static void main(String[] args) throws Exception {
        String filenameFmt = "cubeimgs/whole/%s.jpg";
        List<ColorAnalysis.ColorFacelet> colorFacelets = new ArrayList<>(64);
        for (String facelet : order) {
            File file = new File(String.format(filenameFmt, facelet));
            BufferedImage bufferedImage = ImageIO.read(file);
            System.out.printf("=============%s=============\n", facelet);
            for (Position position : positions) {
                colorFacelets.add(getColorFacelet(bufferedImage, position));
            }
            // ColorAnalysis.ColorFacelet colorFacelet = getColorFacelet(bufferedImage, positions[4]);
            // System.out.println("colorFacelet = " + colorFacelet);

        }
        // System.out.println("colorFacelet = " + colorFacelets);
        String scrambled = colorFacelets.stream().map(ColorAnalysis.ColorFacelet::getFacelets).collect(Collectors.joining());
        System.out.println("scrambled = " + scrambled);


    }

    public static void test() throws Exception {
        File file = new File("cubeimgs/whole/U1.jpg");
        BufferedImage bufImage = ImageIO.read(file);

        int width = bufImage.getWidth();
        int height = bufImage.getHeight();

        WritableRaster writableRaster = bufImage.getRaster();

        Graphics2D graphics = bufImage.createGraphics();

        System.out.println("width = " + width);
        System.out.println("height = " + height);

        // TODO: 2020/10/18 从中心的向外扫描

        int centreX = width / 2;
        int centreY = height / 2;
        System.out.println("Center ===> " + ColorAnalysis.getColorFacelet(bufImage.getRGB(centreX, centreY)));

        int line1_left1 = bufImage.getRGB(340, 500);
        int line1_left2 = bufImage.getRGB(666, 500);
        int line1_centre1 = bufImage.getRGB(1236, 500);
        int line1_centre2 = bufImage.getRGB(1700, 500);
        int line1_right1 = bufImage.getRGB(2300, 500);
        int line1_right2 = bufImage.getRGB(2600, 500);

        int line2_left1 = bufImage.getRGB(340, 1400);
        int line2_left2 = bufImage.getRGB(666, 1400);
        int line2_centre1 = bufImage.getRGB(1236, 1400);
        int line2_centre2 = bufImage.getRGB(1700, 1400);
        int line2_right1 = bufImage.getRGB(2300, 1400);
        int line2_right2 = bufImage.getRGB(2600, 1400);

        int line3_left1 = bufImage.getRGB(340, 2320);
        int line3_left2 = bufImage.getRGB(666, 2320);
        int line3_centre1 = bufImage.getRGB(1236, 2320);
        int line3_centre2 = bufImage.getRGB(1700, 2320);
        int line3_right1 = bufImage.getRGB(2300, 2320);
        int line3_right2 = bufImage.getRGB(2600, 2320);

        int black = bufImage.getRGB(853, 864);
        System.out.println("black = " + new Color(black));

        System.out.println("line1_left1 = " + ColorAnalysis.getColorFacelet(line1_left1));
        System.out.println("line1_left2 = " + ColorAnalysis.getColorFacelet(line1_left2));
        System.out.println("line1_centre1 = " + ColorAnalysis.getColorFacelet(line1_centre1));
        System.out.println("line1_centre2 = " + ColorAnalysis.getColorFacelet(line1_centre2));
        System.out.println("line1_right1 = " + ColorAnalysis.getColorFacelet(line1_right1));
        System.out.println("line1_right2 = " + ColorAnalysis.getColorFacelet(line1_right2));
        System.out.println("line2_left1 = " + ColorAnalysis.getColorFacelet(line2_left1));
        System.out.println("line2_left2 = " + ColorAnalysis.getColorFacelet(line2_left2));
        System.out.println("line2_centre1 = " + ColorAnalysis.getColorFacelet(line2_centre1));
        System.out.println("line2_centre2 = " + ColorAnalysis.getColorFacelet(line2_centre2));
        System.out.println("line2_right1 = " + ColorAnalysis.getColorFacelet(line2_right1));
        System.out.println("line2_right2 = " + ColorAnalysis.getColorFacelet(line2_right2));
        System.out.println("line3_left1 = " + ColorAnalysis.getColorFacelet(line3_left1));
        System.out.println("line3_left2 = " + ColorAnalysis.getColorFacelet(line3_left2));
        System.out.println("line3_centre1 = " + ColorAnalysis.getColorFacelet(line3_centre1));
        System.out.println("line3_centre2 = " + ColorAnalysis.getColorFacelet(line3_centre2));
        System.out.println("line3_right1 = " + ColorAnalysis.getColorFacelet(line3_right1));
        System.out.println("line3_right2 = " + ColorAnalysis.getColorFacelet(line3_right2));

        System.out.println(ColorAnalysis.getColorFacelet(bufImage.getRGB(562, 530)));
    }

    public static ColorAnalysis.ColorFacelet getColorFacelet(BufferedImage bufferedImage, Position position) {
        int rgb = bufferedImage.getRGB(position.getX(), position.getY());
        return ColorAnalysis.getColorFacelet(rgb);
    }

    @Getter
    @AllArgsConstructor
    static class Position {
        private PositionEnum position;
        private int x;
        private int y;
    }

    enum PositionEnum {
        TOP_LEFT,
        TOP_MID,
        TOP_RIGHT,

        MID_LEFT,
        MID_MID,
        MID_RIGHT,

        BOTTOM_LEFT,
        BOTTOM_MID,
        BOTTOM_RIGHT,

        ;
    }
}
