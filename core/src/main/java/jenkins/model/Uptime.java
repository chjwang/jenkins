package jenkins.model;

import hudson.Extension;
import hudson.init.InitMilestone;
import hudson.init.Initializer;

/**
 * Keeps track of the uptime of Jenkins.
 *
 * @author Kohsuke Kawaguchi
 * @since 1.538
 */
@Extension
public class Uptime {
    private long startTime;

    /**
     * Timestamp in which Jenkins became fully up and running.
     */
    public long getStartTime() {
        return startTime;
    }

    public long getUptime() {
        return System.currentTimeMillis()-startTime;
    }

    @Initializer(after=InitMilestone.JOB_LOADED)
    public static void init() {
        Jenkins.getInstance().getExtensionList(Uptime.class).get(0).startTime = System.currentTimeMillis();
    }
}
