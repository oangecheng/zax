package com.ustc.orange.zax.common;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Author: orange910617@gmail.com
 * date:2019-11-09
 * remark:
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {

  private List<T> mList;

  public BaseRecyclerAdapter() {
    mList = new ArrayList<>();
  }

  public List<T> getList() {
    return mList;
  }

  public void setList(List<T> list) {
    if (list == null) {
      throw new IllegalArgumentException("list can't be null");
    }
    mList.clear();
    mList.addAll(list);
  }

  public T getItem(int position) {
    return mList.get(position);
  }

  @Override
  public int getItemCount() {
    return mList.size();
  }
}
