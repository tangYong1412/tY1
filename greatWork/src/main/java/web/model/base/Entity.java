package main.java.web.model.base;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final long serVersionUID = 1L;

    //返回表名
    public abstract String getTableName();
}
