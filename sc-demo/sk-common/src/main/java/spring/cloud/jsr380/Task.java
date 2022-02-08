package spring.cloud.jsr380;

import java.time.Duration;

/**
 * @Description TODO
 * @Author junode
 * @Date 2021/1/25
 */
public class Task {
    private String taskName;
    private Duration timeSpent;

    public Task(String taskName,Duration timeSpent) {
        this.taskName = taskName;
        this.timeSpent = timeSpent;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Duration getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Duration timeSpent) {
        this.timeSpent = timeSpent;
    }
}
