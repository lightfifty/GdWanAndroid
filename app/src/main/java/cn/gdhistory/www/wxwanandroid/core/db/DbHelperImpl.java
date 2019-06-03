package cn.gdhistory.www.wxwanandroid.core.db;

import java.util.List;

import cn.gdhistory.www.wxwanandroid.app.WxApp;
import cn.gdhistory.www.wxwanandroid.core.dao.DaoSession;
import cn.gdhistory.www.wxwanandroid.core.dao.HistoryData;
import cn.gdhistory.www.wxwanandroid.core.dao.HistoryDataDao;

/**
 * Description:
 *
 * @author: ${user}
 * @email:838640006@qq.com
 * @time: 2019-05-23 08:43.
 * version: 1.0
 */
public class DbHelperImpl implements DbHelper {
    private static final int HISTORY_LIST_SIZE = 50;
    private DaoSession mDaoSession;
    private List<HistoryData> mHistoryDataList;
    private String mData;
    private HistoryData mHistoryData;

    public DbHelperImpl() {
        mDaoSession = WxApp.getInstance().getDaoSession();
    }

    @Override
    public List<HistoryData> addHistoryData(String data) {
        this.mData = data;
        getHistoryDataList();
        createHistoryData();


        return null;
    }

    @Override
    public void clearHistoryData() {

    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return null;
    }

    /**
     * 从数据库加载数据
     */
    private void getHistoryDataList() {
        mHistoryDataList = getHistoryDataDao().loadAll();
    }

    private HistoryDataDao getHistoryDataDao() {
        return mDaoSession.getHistoryDataDao();
    }

    /**
     *
     */
    private void createHistoryData() {
        mHistoryData = new HistoryData();
        mHistoryData.setDate(System.currentTimeMillis());
        mHistoryData.setData(mData);
    }
}
