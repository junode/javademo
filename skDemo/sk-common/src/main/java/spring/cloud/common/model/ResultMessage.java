package spring.cloud.common.model;

/**
 * @description: 响应消息实体类
 * @author: junode
 * @create: 2020-12-20 22:29
 **/
public class ResultMessage {
    private boolean success;
    private String message;

    public ResultMessage() {
    }

    public ResultMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
