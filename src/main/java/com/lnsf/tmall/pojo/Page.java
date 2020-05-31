package com.lnsf.tmall.pojo;

public class Page {
    //总条数
    private Integer totalNumber;
    //总页数
    private Integer totalPage;
    //当前页数
    private Integer currentPage;
    //数据库中limit的参数，从第几条开始取
    private Integer dbIndex;
    //数据库中limit的参数，总共取几条
    private Integer dbNumber;
    //每页显示几条
    private Integer pageNumber=8;


    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        this.count();
    }

    public void count(){
    /**
     * 根据总条数计算总页数
     */
        if (this.totalNumber%this.pageNumber>0){
    /**
     * 如果总条数小于每页显示的条数，那么总页数为1
     */
            if (this.totalNumber<this.pageNumber){
                this.totalPage=1;
            }
            else{
                this.totalPage=(this.totalNumber/this.pageNumber)+1;
            }

        }
        else { this.totalPage=this.totalNumber/this.pageNumber; }
    // 设置当前页数
    // 总页数小于当前页数，应将当前页数设置为总页数
        if(this.totalPage < this.currentPage) {
            this.currentPage = this.totalPage;
        }
    // 当前页数小于1设置为1
        if(this.currentPage < 1) {
            this.currentPage = 1;
        }
    /**
     * 设置limit参数
     */
        this.dbIndex = (this.currentPage-1)*this.pageNumber;//从第几条开始查询
        this.dbNumber = this.pageNumber;//每页显示的条数
    }





    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }

    public Integer getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(Integer dbNumber) {
        this.dbNumber = dbNumber;
    }



    public Page() {

    }

    public Page(Integer totalNumber, Integer totalPage, Integer currentPage, Integer dbIndex, Integer dbNumber, Integer pageNumber) {
        this.totalNumber = totalNumber;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.dbIndex = dbIndex;
        this.dbNumber = dbNumber;
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalNumber=" + totalNumber +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", dbIndex=" + dbIndex +
                ", dbNumber=" + dbNumber +
                ", pageNumber=" + pageNumber +
                '}';
    }
}