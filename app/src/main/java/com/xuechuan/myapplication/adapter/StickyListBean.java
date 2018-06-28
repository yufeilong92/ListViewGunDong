package com.xuechuan.myapplication.adapter;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication3
 * @Package com.xuechuan.myapplication.adapter
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/6/27 17:05
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class StickyListBean {
    public int section;
    public String YM;
    public String content;
    public StickyListBean(int section,String YM,String content){
        this.section = section;
        this.YM = YM;
        this.content = content;
    }
}
