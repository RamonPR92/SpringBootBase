package com.ramon.jpa.app.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPages;
    private int size;
    private int actualPage;
    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.actualPage = page.getNumber();
        paginas = new ArrayList<>();

        int from, to;
        if (totalPages <= size) {
            from = 1;
            to = totalPages;
        }else {
            if(actualPage <= size / 2){
                from = 1;
                to = size;
            }else if(actualPage >= totalPages - size / 2 ){
                from = totalPages - size + 1;
                to = size;
            }else{
                from = actualPage - size / 2;
                to = size;
            }
        }

        for (int i =0; i < to; i++){
            paginas.add(new PageItem(from + i, actualPage == from + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return  page.hasNext();
    }

    public boolean isHasPrevious(){ return page.hasPrevious();}
}
