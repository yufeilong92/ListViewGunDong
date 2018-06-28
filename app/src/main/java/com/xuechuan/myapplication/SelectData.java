package com.xuechuan.myapplication;

import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication3
 * @Package com.xuechuan.myapplication
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/6/27 17:56
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class SelectData {

 private String prarenid;
 private ArrayList<String> mItemList;
 private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getPrarenid() {
        return prarenid;
    }

    public void setPrarenid(String prarenid) {
        this.prarenid = prarenid;
    }

    public ArrayList<String> getmItemList() {
        return mItemList;
    }

    public void setmItemList(ArrayList<String> mItemList) {
        this.mItemList = mItemList;
    }
}
