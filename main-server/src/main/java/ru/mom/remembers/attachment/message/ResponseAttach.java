package ru.mom.remembers.attachment.message;

public class ResponseAttach {

    private String name;
    private String url;
    private String type;
    private Long id;
    private long size;

    public ResponseAttach(String name, String url, String type, Long id, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.id = id;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
