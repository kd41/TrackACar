package ee.track.program;

import static ee.track.program.DataHelper.getDataChage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ee.track.model.Data;
import ee.track.model.DataChange;

public class TrackTask implements Runnable {
    private static Logger logger = Logger.getLogger(TrackTask.class);

    private Map<String, Data> results = new HashMap<>();
    private Map<String, DataChange> changes = new HashMap<>();

    @Override
    public void run() {

        long start = System.currentTimeMillis();
        logger.info("started");
        List<Data> newResults = new ArrayList<>();
        Collection<String> linesToWrite = new ArrayList<>();
        try {
            // get data from urls
            ProcessorService processor = null;
            for (String url : DataIO.getUrls()) {
                // get search results
                // processor = new ProcessorServiceMockImpl(url);
                processor = new ProcessorServiceImpl(url);
                newResults = processor.getData();

                // skip if nothing found
                if (newResults.isEmpty()) {
                    String log = "Nothing found for url: " + url;
                    logger.info(log);
                    linesToWrite.add(log);
                    break;
                }

                for (Data newData : newResults) {
                    // find new results and changes
                    if (!results.containsKey(newData.getId())) {
                        results.put(newData.getId(), newData);
                        String log = newData.toString();
                        logger.info(log);
                        linesToWrite.add(log);
                    } else {
                        Data oldData = results.get(newData.getId());
                        if (!oldData.equals(newData)) {
                            DataChange change = getDataChage(oldData, newData);
                            results.put(newData.getId(), newData);
                            changes.put(oldData.getId(), change);
                            String log = change.getChangesAsString();
                            logger.info(log);
                            linesToWrite.add(log);
                        }
                    }
                }

                // check for deleted
                List<String> toDelete = new ArrayList<>();
                for (String resultKey : results.keySet()) {
                    boolean found = false;
                    for (Data result : newResults) {
                        if (resultKey.equals(result.getId())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        DataChange change = new DataChange(resultKey);
                        change.addChange("car", "selling", "deleted");
                        toDelete.add(resultKey);
                        String log = change.getChangesAsString();
                        logger.info(log);
                        linesToWrite.add(log);
                    }
                }
                for (String todelete : toDelete) {
                    results.remove(todelete);
                }

                newResults.clear();
                // sleep for few seconds
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            // sabe to file
            DataIO.save(linesToWrite);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        long stop = System.currentTimeMillis();
        logger.info("finished. Time:" + (stop - start) + "ms");
    }

}
