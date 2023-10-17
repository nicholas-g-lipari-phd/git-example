public class VideoServer extends ServerThread {
    Video vField;
    public VideoServer(){
        super(8888);

        vField = new Video( VideoPlayer.WIDTH, VideoPlayer.HEIGHT, .5 );
        vField.addStringFrames("Testing.Testing. One Two Three. Testing");
    }
    
}
