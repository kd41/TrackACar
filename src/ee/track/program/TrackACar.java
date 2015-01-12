package ee.track.program;

import java.io.IOException;

import ee.track.view.TrackView;

public class TrackACar {

    private TrackContext context;
    private TrackView view;

    public static void main(String[] args) throws IOException {
        // init program
        TrackACar program = new TrackACar();
        // init context
        program.context = new TrackContext();
        // init view
        program.view = new TrackView(program.context);
        // show window
        program.view.show(true);
    }

}
