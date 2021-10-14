package com.example.characterproject;

public class Item {

    String contents;
    String uri;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Item(String contents, String uri) {
        this.contents = contents;
        this.uri =uri;
    }
}
