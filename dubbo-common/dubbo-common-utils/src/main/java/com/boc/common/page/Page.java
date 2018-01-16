package com.boc.common.page;

import com.boc.common.metatype.DTO;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

public class Page implements Serializable {

    private static final long serialVersionUID = 8010409295674435347L;

    /**
     * 分页：每页显示行数
     */
    public static final String SHOWCOUNT = "rows";

    /**
     * 分页：当前页数,首页为1
     */
    public static final String CURRENTPAGE = "page";
    private int showCount = 10; // 每页显示记录数
    private int totalPage; // 总页数
    private int totalResult; // 总记录数
    private int currentPage = 1; // 当前页
    private int currentResult; // 当前记录起始索引
    private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
    private Map dto;

    public int getTotalPage() {
        if(totalResult <= 0){
        	this.totalResult=0;
            return 0;
        }
        if (totalResult % showCount == 0) {
            totalPage = totalResult / showCount;
        } else {
            totalPage = totalResult / showCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage >= 1 ? currentPage : 1;

        setCurrentResult((this.currentPage - 1) * this.getShowCount());
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {

        this.showCount = showCount;
    }

    public int getCurrentResult() {
        currentResult = (getCurrentPage() - 1) * getShowCount();
        if (currentResult < 0) {
            currentResult = 0;
        }
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public boolean isEntityOrField() {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField) {
        this.entityOrField = entityOrField;
    }

    public Map getDto() {
        return dto;
    }

    public void setDto(Map dto) {
        this.dto = dto;
    }

    /**
     * 在创建分页对象时,必须传入分页参数
     *
     * @param dto
     */
    public void setPageDTO(DTO dto) {
        if(!StringUtils.isEmpty(dto.getAsString(SHOWCOUNT)) && !StringUtils.isEmpty(dto.getAsString(CURRENTPAGE))){
            setShowCount(Integer.parseInt(dto.getAsString(SHOWCOUNT)));
            if (dto.containsKey(CURRENTPAGE)) {
                setCurrentPage(Integer.parseInt(dto.getAsString(CURRENTPAGE)));
            }
        }else if(!StringUtils.isEmpty(dto.getAsString("currentPage")) && !StringUtils.isEmpty(dto.getAsString("showCount"))){
            setShowCount(Integer.parseInt(dto.getAsString("showCount")));
            setCurrentPage(Integer.parseInt(dto.getAsString("currentPage")));
        }
        this.dto = dto;
    }

    /**
     * 设置页大小
     */
    public void setRows(int rows) {
        setShowCount(rows);
    }

    /**
     * 设置当前页
     */
    public void setPage(int page) {
        setCurrentPage(page);
    }

}
