package ee.track.program;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TrackScheduler {
    private final ScheduledExecutorService scheduler;
    private long period;

    /**
     * @param period in seconds
     */
    public TrackScheduler(long period) {
        scheduler = Executors.newScheduledThreadPool(1, new TrackThreadFactory());
        this.period = period;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(new TrackTask(), 3, period, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

    private static class TrackThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "TrackTask");
        }
    }

}
