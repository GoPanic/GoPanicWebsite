package controllers;

import models.Panic;
import play.Logger;
import play.Play;
import play.jobs.Every;
import play.jobs.Job;

import java.util.Iterator;

/**
 * @author Michael Ruf
 * @since 2015-11-28
 */
@Every("1s")
@SuppressWarnings("unused")
public class CleanPanicsJob extends Job {

    @Override
    public void doJob() throws Exception {
        super.doJob();

        long timeLimit = Long.parseLong(Play.configuration.getProperty("panic.ttl"));
        Logger.debug("CleanPanicsJob: Start for time limit: " + timeLimit);

        for (Iterator<Panic> iterator = Application.panics.iterator(); iterator.hasNext(); ) {
            Panic panic = iterator.next();
            if (Panic.getPanicTime() - panic.getTime() > timeLimit) {
                iterator.remove();
            }
        }
    }
}
