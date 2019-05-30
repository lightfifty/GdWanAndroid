package cn.gdhistory.www.wxwanandroid.core.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Description: 历史数据表
 * author: ${user}
 * email:838640006@qq.com
 * time: 2019-05-23 08:28.
 * version: 1.0
 */
@Entity
public class HistoryData {

    @Id(autoincrement = true)
    private Long id;
    private long date;
    private String data;

    @Generated(hash = 1371145256)
    public HistoryData(Long id, long date, String data) {
        this.id = id;
        this.date = date;
        this.data = data;
    }

    @Generated(hash = 422767273)
    public HistoryData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
