package com.jyss.yqy.entity;

import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;

public class Page<E>
{
  private long total;
  private List<E> rows = new ArrayList();
  private List<PageFooter> footer = new ArrayList();
  
  public Page() {}
  
  public Page(PageInfo<E> pageInfo)
  {
    this.total = pageInfo.getTotal();
    this.rows = pageInfo.getList();
  }
  
  public Page(PageInfo<E> pageInfo, List<PageFooter> footer) {
    this.total = pageInfo.getTotal();
    this.rows = pageInfo.getList();
    this.footer = footer;
  }
  
  public long getTotal() {
    return this.total;
  }
  
  public List<PageFooter> getFooter() {
    return this.footer;
  }
  
  public void setFooter(List<PageFooter> footer) {
    this.footer = footer;
  }
  
  public void setTotal(long total) {
    this.total = total;
  }
  
  public List<E> getRows() {
    return this.rows;
  }
  
  public void setRows(List<E> rows) {
    this.rows = rows;
  }
}
