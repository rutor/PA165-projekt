package cz.muni.fi.pa165.sample;

import cz.muni.fi.pa165.ServicesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

@Configuration
@Import(ServicesContext.class)
@ComponentScan(basePackageClasses = {InsertSampleDataImpl.class})
public class SampleDataContext {

    final static Logger log = LoggerFactory.getLogger(SampleDataContext.class);

    @Inject
    InsertSampleData insertSampleData;

    @PostConstruct
    public void dataLoading() throws IOException {
        log.debug("dataLoading()");
        insertSampleData.insertData();
    }
}