package controllers;

import models.Panic;
import play.Logger;
import play.Play;
import play.jobs.Every;
import play.jobs.Job;

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

        for (Panic panic : Application.panics) {
            if (Panic.getPanicTime() - panic.getTime() > timeLimit) {
                Application.panics.remove(panic);
            }
        }
    }
}
