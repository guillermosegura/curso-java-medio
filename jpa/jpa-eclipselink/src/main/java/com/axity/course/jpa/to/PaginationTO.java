package com.axity.course.jpa.to;

public class PaginationTO
{
  protected int page = 0;
  protected int size = 20;
  protected boolean paginated;
  public int getPage()
  {
    return page;
  }

  public void setPage( int page )
  {
    this.page = page;
  }

  public int getSize()
  {
    return size;
  }

  public void setSize( int size )
  {
    this.size = size;
  }

  public boolean isPaginated()
  {
    return paginated;
  }

  public void setPaginated( boolean paginated )
  {
    this.paginated = paginated;
  }

}
