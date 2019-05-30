package cn.gdhistory.www.wxwanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.gdhistory.www.wxwanandroid.BuildConfig;
import cn.gdhistory.www.wxwanandroid.R;
import cn.gdhistory.www.wxwanandroid.core.dao.DaoMaster;
import cn.gdhistory.www.wxwanandroid.core.dao.DaoSession;
import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Description:模仿quchao的wanandroid 客户端
 * Created by wuxiang on 2019/3/17 13:43.
 * email:838640006@qq.com
 */
public class WxApp extends Application implements HasActivityInjector {


    private static WxApp instance;
    private RefWatcher mRefWatcher;


    static {
        // 设定默认白天模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new DeliveryHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
            }
        });
    }

    private DaoSession mDaoSession;

    public static synchronized WxApp getInstance() {
        return instance;
    }


    public RefWatcher getRefWatcher(Context context) {
        WxApp wxApp = (WxApp) context.getApplicationContext();
        return wxApp.mRefWatcher;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // greendao
        initGreenDao();

        // dagger2


        instance = this;
        // 集成bugly

        // 初始化日志day
        initLogger();

        // 初始化调试工具
        Stetho.initializeWithDefaults(this);

        // 内存泄露检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }

    /**
     * 初始化日志打印
     */
    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy
                    .newBuilder()
                    .tag(getString(R.string.app_name))
                    .build()));
        }

        FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
                .tag(getString(R.string.app_name))
                .build();
        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
    }


}
