package cn.gdhistory.www.wxwanandroid.core.db;

import java.util.List;

import cn.gdhistory.www.wxwanandroid.core.dao.HistoryData;

/**
 * Description:
 * @author: ${user}
 * @email:838640006@qq.com
 * @time: 2019-05-23 08:39.
 * version: 1.0
 */
public interface DbHelper {
    /**
     * 添加历史数据
     * @param data
     * @return
     */
    List<HistoryData> addHistoryData(String data);

    /**
     * 清除历史数据
     */
    void clearHistoryData();

    /**
     * 加载所有历史数据
     * @return
     */
    List<HistoryData> loadAllHistoryData();
}
