import java.util.ArrayList;

public class Video {
    private ArrayList<VideoFrame> frames;
    private int width, height;
    private double frameRate;

    public Video(int w, int h, double fr) {
        width = w;
        height = h;
        frameRate = (fr > 0 ? fr : 1);

        frames = new ArrayList<>();
    }

    public void addFrame(VideoFrame f) {
        frames.add(f);
    }
    public void addFrame(String t) {
        frames.add(VideoFrame.getAsciiArt(t, this.width, this.height));
    }

    public void addStringFrames(String s) {
        String [] sentences = s.strip().split("\\.");
        for(String sen : sentences) {
            String[] words = sen.strip().split(" ");
            for(String w : words) {
                addFrame(w.strip());
            }

            addFrame("");
        }

        //remove last empty frame
        if( frames.get(frames.size()-1).toString().strip().length() == 0)
            frames.remove(frames.size()-1);
    }

    public double getFrameRate() {
        return frameRate;
    }
    public double getFrameTime() {
        return 1.0 / frameRate;
    }

    public VideoFrame getFrame(int index) {
        if(index >= 0 && index < frames.size())
            return frames.get(index);

        return new VideoFrame("NO FRAME", width, height);
    }

    public int getNumFrames() {
        return frames.size();
    }
}
