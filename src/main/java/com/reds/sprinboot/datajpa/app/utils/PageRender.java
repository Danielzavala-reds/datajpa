package com.reds.sprinboot.datajpa.app.utils;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    
    private String url;
    private Page<T> page;

    private int totalPages;

    private int itemsPerPage;

    private int currentPage;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;

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

    }
    
    
}
