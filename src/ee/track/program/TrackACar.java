package ee.track.program;

import java.io.IOException;

public class TrackACar {

    public static void main(String[] args) throws IOException {
        TrackScheduler scheduler = new TrackScheduler(900);
        scheduler.start();
    }

}
