package com.example.entity;

/**
 * Created by neal on 26/03/2017.
 * Hibernate对父类成员变量不起作用，即不会对应到相应的表中
 */
public class BaseEntity implements java.io.Serializable{
    Long ctime = 0L;
    Long mtime = 0L;

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }
}
