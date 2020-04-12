package com.snake19870227.stiger.web.restful;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bu HuaYang
 */
public class PageRestResponse<T> extends RestResponse<T> {

    @ApiModelProperty(value = "当前页码", allowableValues = "[1, infinity]")
    private int page;

    @ApiModelProperty(value = "每页条数")
    private int pageSize;

    @ApiModelProperty(value = "查询结果总数")
    private long total;

    @ApiModelProperty(value = "总页数")
    private long totalPage;

    @ApiModelProperty(value = "彩虹分页数组")
    private int[] rainbow;

    public PageRestResponse() {
    }

    public PageRestResponse(String respCode, String respMessage) {
        super(respCode, respMessage);
    }

    public PageRestResponse(String respCode, String respMessage, T data) {
        super(respCode, respMessage, data);
    }

    public static class DefaultPageRestResponse extends PageRestResponse<Object> {

        public DefaultPageRestResponse(String respCode, String respMessage) {
            super(respCode, respMessage);
        }

        public DefaultPageRestResponse(String respCode, String respMessage, Object data) {
            super(respCode, respMessage, data);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public int[] getRainbow() {
        return rainbow;
    }

    public void setRainbow(int[] rainbow) {
        this.rainbow = rainbow;
    }
}
