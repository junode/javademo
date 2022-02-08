package spring.cloud.jsr380;

import java.time.Duration;

/**
 * @Description JSR380 Validation
 * @Author junode
 * @Date 2021/1/25
 */
public class ValidationDemo01 {
    public static void main(String[] args) {
        Task task = new Task("junode", Duration.ZERO);
        System.out.println(task.getTaskName());

    }
}
