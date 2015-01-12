package ee.track.program;

public class TrackContext {
    private TrackScheduler scheduler;
    private long checkInterval = 900; //default 15 min
    private boolean started;

    public void start() {
        scheduler = new TrackScheduler(checkInterval);
        scheduler.start();
        started = true;
    }

    public void stop() {
        scheduler.stop();
        started = false;
        scheduler = null;
    }

    /** in seconds */
    public void setCheckInterval(long checkInterval) {
        this.checkInterval = checkInterval;
    }

    public boolean isStarted() {
        return started;
    }

}
