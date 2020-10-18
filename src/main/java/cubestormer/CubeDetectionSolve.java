package cubestormer;

import cs.min2phase.Search;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author cuiguiyang
 * @since 2020/10/18 20:18
 */
public class CubeDetectionSolve {

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
        List<ColorAnalysis.ColorFacelet> colorFacelets = new ArrayList<>(64);
        String filenameFmt = "cubeimgs/whole/%s.jpg";

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
        String scrambledCube =
                colorFacelets.stream()
                        .map(ColorAnalysis.ColorFacelet::getFacelets)
                        .collect(Collectors.joining());
        System.out.println("scrambledCube = " + scrambledCube);

        simpleSolve(scrambledCube);
        outputControl(scrambledCube);
        findShorterSolutions(scrambledCube);
        continueSearch(scrambledCube);
    }


    public static ColorAnalysis.ColorFacelet getColorFacelet(BufferedImage bufferedImage, Position position) {
        int rgb = bufferedImage.getRGB(position.getX(), position.getY());
        return ColorAnalysis.getColorFacelet(rgb);
    }

    public static void simpleSolve(String scrambledCube) {
        String result = new Search().solution(scrambledCube, 21, 100000000, 0, 0);
        System.out.println(result);
        // R2 U2 B2 L2 F2 U' L2 R2 B2 R2 D  B2 F  L' F  U2 F' R' D' L2 R'
    }

    public static void outputControl(String scrambledCube) {
        String result = new Search().solution(scrambledCube, 21, 100000000, 0, Search.APPEND_LENGTH);
        System.out.println(result);
        // R2 U2 B2 L2 F2 U' L2 R2 B2 R2 D  B2 F  L' F  U2 F' R' D' L2 R' (21f)

        result = new Search().solution(scrambledCube, 21, 100000000, 0, Search.USE_SEPARATOR | Search.INVERSE_SOLUTION);
        System.out.println(result);
        // R  L2 D  R  F  U2 F' L  F' .  B2 D' R2 B2 R2 L2 U  F2 L2 B2 U2 R2
    }

    public static void findShorterSolutions(String scrambledCube) {
        //Find shorter solutions (try more probes even a solution has already been found)
        //In this example, we try AT LEAST 10000 phase2 probes to find shorter solutions.
        String result = new Search().solution(scrambledCube, 21, 100000000, 10000, 0);
        System.out.println(result);
        // L2 U  D2 R' B  U2 L  F  U  R2 D2 F2 U' L2 U  B  D  R'
    }

    public static void continueSearch(String scrambledCube) {
        //Continue to find shorter solutions
        Search searchObj = new Search();
        String result = searchObj.solution(scrambledCube, 21, 500, 0, 0);
        System.out.println(result);
        // R2 U2 B2 L2 F2 U' L2 R2 B2 R2 D  B2 F  L' F  U2 F' R' D' L2 R'

        result = searchObj.next(500, 0, 0);
        System.out.println(result);
        // D2 L' D' L2 U  R2 F  B  L  B  D' B2 R2 U' R2 U' F2 R2 U' L2

        result = searchObj.next(500, 0, 0);
        System.out.println(result);
        // L' U  B  R2 F' L  F' U2 L  U' B' U2 B  L2 F  U2 R2 L2 B2

        result = searchObj.next(500, 0, 0);
        System.out.println(result);
        // Error 8, no solution is found after 500 phase2 probes. Let's try more probes.

        result = searchObj.next(500, 0, 0);
        System.out.println(result);
        // L2 U  D2 R' B  U2 L  F  U  R2 D2 F2 U' L2 U  B  D  R'
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
