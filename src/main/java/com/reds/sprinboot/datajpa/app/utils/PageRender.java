package com.reds.sprinboot.datajpa.app.utils;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    
    private String url;
    
    private Page<T> page;

    private int totalPages;

    private int itemsPerPage;

    private int currentPage;

    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        itemsPerPage = page.getSize();
        totalPages = page.getTotalPages();
        currentPage = page.getNumber() + 1;

        int from, until;
        if(totalPages <= itemsPerPage){
            from = 1;
            until = totalPages;
        } else{
            if(currentPage <= itemsPerPage/2){
                from = 1;
                until = itemsPerPage;
            } else if(currentPage >= totalPages - itemsPerPage/2){
                from = totalPages - itemsPerPage + 1;
                until = itemsPerPage;
            } else{
                from = currentPage - itemsPerPage/2;
                until = itemsPerPage;
            }
        }

        for(int i = 0; i < until; i++){
            pages.add(new PageItem(until + i, currentPage == until + 1));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public void setPages(List<PageItem> pages) {
        this.pages = pages;
    }

    public boolean isFirst (){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }
    
    
}
