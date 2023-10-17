import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoFrame {

    private int width, height;
    private String frame;

    public VideoFrame(String f, int w, int h) {
        frame = f;
        width = w;
        height = h;
    }

    //! Citation: https://mkyong.com/java/ascii-art-java-example/
    public static VideoFrame getAsciiArt(String data, int width, int height) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 24));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(data, 10, 20);

        //save this image
        //ImageIO.write(image, "png", new File("/users/mkyong/ascii-art.png"));

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "#");


            if (sb.toString().trim().isEmpty())
                continue;

            //System.out.println(sb);
            sb.append("\n");
        }
        return new VideoFrame( sb.toString(), width, height);
    }

    @Override
    public String toString() {
        return frame;
    }
}


