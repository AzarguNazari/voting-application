package de.bamberg.voting.Model;

import org.springframework.web.multipart.MultipartFile;

public class Greeting {
    private long id;
    private String content;
    private String students;

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

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }
}
