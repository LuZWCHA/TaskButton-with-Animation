package com.com.sky.downloader.greendao;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by 陆正威 on 2017/4/16.
 */
public class DBManager {
    private final static String dbName = "scarf_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    public static void initialize(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
    }

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        daoMaster=new DaoMaster(openHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        //打印日志
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;
    }
    /**
     * 获取单例引用
     *
     * @param
     * @return
     */
    public static DBManager getInstance() {
        return mInstance;
    }
    /**
     * 获取session对象
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
