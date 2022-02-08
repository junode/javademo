package spring.cloud.common.model;

import java.io.Serializable;

/**
 * @Description 用户信息实体类
 * @Author junode
 * @Date 2020/12/27
 */
public class UserInfo implements Serializable {
    private Long id;
    private String userName;
    private String note;

    public UserInfo(Long id,String userName,String note){
        this.id = id;
        this.userName = userName;
        this.note = note;
    }

    public UserInfo(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
