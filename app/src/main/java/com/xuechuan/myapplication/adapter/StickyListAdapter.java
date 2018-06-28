package com.xuechuan.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.xuechuan.myapplication.R;
import com.xuechuan.myapplication.SelectData;
import com.xuechuan.myapplication.stickyListHeaders.StickyListHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication3
 * @Package com.xuechuan.myapplication.adapter
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/6/27 16:36
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {
    private Context mContext;
    private List mData;
    private LayoutInflater mInflater;
    ArrayList<SelectData> selectData;

    public StickyListAdapter(Context mContext, List mData, ArrayList<SelectData> selectData) {
        this.mContext = mContext;
        this.mData = mData;
        this.selectData = selectData;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;

        if (convertView == null) {
            convertView = mInflater
                    .inflate(R.layout.item_sticky, parent, false);
            mHolder = new ViewHolder();
            mHolder.tvTime = (TextView) convertView.findViewById(R.id.item);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        StickyListBean info = ((StickyListBean) getItem(position));
        mHolder.tvTime.setText(info.content);
        if (selectData != null) {
            SelectData data = selectData.get(position);
            if (data.isShow()) {
                mHolder.tvTime.setVisibility(View.VISIBLE);
            } else {
                mHolder.tvTime.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position < getCount())
            return ((StickyListBean) getItem(position)).section;
        return 0;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        final HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.item_sticky_header,
                    parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        final String headerText = ((StickyListBean) getItem(position)).YM;
        holder.text.setText(headerText);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < selectData.size(); i++) {
                    SelectData data = selectData.get(i);
                    if (data.getPrarenid() != null && data.getPrarenid().equals(headerText)) {
                        if (data.isShow()) {
                            data.setShow(false);
                        } else {
                            data.setShow(true);
                        }
                        notifyDataSetChanged();
                    }
                }
            }

        });
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if (position < getCount()) {
            return ((StickyListBean) getItem(position)).section;
        }
        return 0;
    }

    static class HeaderViewHolder {
        TextView text;
    }

    public static class ViewHolder {
        private TextView tvTime;
    }
}
