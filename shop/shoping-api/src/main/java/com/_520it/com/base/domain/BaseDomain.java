package com._520it.com.base.domain;

import java.io.Serializable;

public class BaseDomain implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
