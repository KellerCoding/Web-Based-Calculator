package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Input {
    public long id;
    public String content;

    public Input(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
