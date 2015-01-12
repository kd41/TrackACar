package ee.track.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ProcessorServiceMockImpl extends ProcessorServiceImpl {
    private static Logger logger = Logger.getLogger(ProcessorServiceMockImpl.class);
    private static final String FILE_NAME = "resources/auto24.html";

    public ProcessorServiceMockImpl(String url) {
        super(url);
    }

    @Override
    protected Document getDocument() {
        String html = null;
        try {
            html = FileUtils.readFileToString(new File(FILE_NAME));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return Jsoup.parse(html);
    }

}
