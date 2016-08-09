package com.example.yuanmengzeng.hexagonblock;

/**
 * 公用数据 Created by yuanmengzeng on 2016/6/21.
 */
public class CommonData
{
    public final static int STATE_SCORE_LEVEl = 40000;

    public final static int SECOND_STAGE_SOCRE = 40000;

    public final static int THIRD_STAGE_SOCRE = 80000;

    public final static int LOAD_TIME_DATA_SUCS = 10000;

    public final static int LOAD_TIME_DATA_FAIL = 10001;

    public final static String WeChat_APP_ID = "wx8d3245121978a9b4";

    public final static String WECHAT_SHARE_WEB = "http://120.24.93.248/html/app/index.html";

    public final static String MP3[] = {"http://120.24.93.248/mp3/1.mp3", "http://120.24.93.248/mp3/2.mp3",
            "http://120.24.93.248/mp3/3.mp3", "http://120.24.93.248/mp3/4.mp3", "http://120.24.93.248/mp3/5.mp3",
            "http://120.24.93.248/mp3/6.mp3", "http://120.24.93.248/mp3/7.mp3", "http://120.24.93.248/mp3/8.mp3"};

    public final static int HEAP_BG[] = {R.drawable.heap_bg, R.drawable.eson_bg, R.drawable.rainbow_bg0,
            R.drawable.rainbow_bg1, R.drawable.rainbow_bg2, R.drawable.rainbow_bg3, R.drawable.me_bg,
            R.drawable.bruce_avatar_bg};

    // public final static int HEAP_BG[] =
    // {R.drawable.bruce_bg,R.drawable.bruce_bg0, R.drawable.rainbow_bg2,
    // R.drawable.me_bg,};

    public final static int BLOCK_LEFT = 0;

    public final static int BLOCK_CENTER = 1;

    public final static int BLOCK_RIGHT = 2;

    /**
     * 退出游戏dialog的类型标志
     */
    public final static int TYPE_EXIT = 0x1234;

    /**
     * 游戏失败使用钻石dialog的类型标志
     */
    public final static int TYPE_DIAMOND = 0x1235;

}
