import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VideoPlayer {
    static final int WIDTH = 800;
    static final int HEIGHT = (int)(WIDTH * (9.0 / 16.0));

    JFrame media;
    JTextArea text;

    public VideoPlayer() {
        media = new JFrame("Media");
        media.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mediaTest.add(mp);
        //https://filesamples.com/formats/mpg
        //mp.setMediaLocation( "video.avi" );

        text = new JTextArea();
        text.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 10));
        text.setSize(WIDTH, HEIGHT);
        text.setLineWrap(false);
        text.setEditable(false);
        text.setCursor( new Cursor(Cursor.HAND_CURSOR) );

        //text.setText("* ".repeat(30));
        media.add(text);

        media.setSize(WIDTH, HEIGHT);
        //mediaTest.setResizable(false);
        media.setVisible(true);

    }

    public VideoPlayer(Video video) {

    }

    public void setDefaultText() {
        text.setText(VideoFrame.getAsciiArt("TESTING", WIDTH, HEIGHT).toString());
    }

    public void play(Video video) {
        long frameTimeMillis = Math.round(video.getFrameTime()*1000);

        for(int idx=0; idx<video.getNumFrames(); idx++){
            text.setText( video.getFrame(idx).toString() );
            media.repaint();
            sleep( frameTimeMillis );
            //System.out.println(frameTimeMillis);
        }

    }
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep( milliseconds );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
