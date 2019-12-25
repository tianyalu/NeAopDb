package com.sty.ne.aopdb;

/**
 * Created by tian on 2019/12/25.
 */

public interface DBOperation {
    void insert();

    void delete();

    void update();

    //数据备份
    void save();
}
