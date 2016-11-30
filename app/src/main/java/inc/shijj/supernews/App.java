package inc.shijj.supernews;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by shijj on 2016/11/29.
 */

public class App extends Application {
    /**
     * 全局Context，方便引用
     */
    public static App application;
    /**
     * 初始化SP&EDIT
     */
    public static SharedPreferences SP;
    public static SharedPreferences.Editor EDIT;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //初始化通用的SP&EDIT
        SP = getSharedPreferences("config", MODE_PRIVATE);
        EDIT = SP.edit();
    }

}
