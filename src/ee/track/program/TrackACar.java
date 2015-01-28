package ee.track.program;

import java.io.IOException;

import ee.track.view.TrackView;

public class TrackACar {

    private TrackView view;

    public static void main(String[] args) throws IOException {
        // init program
        TrackACar program = new TrackACar();
        // init view
        program.view = new TrackView();
        // show window
        program.view.show(true);
    }

}
