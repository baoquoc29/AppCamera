package com.example.appcamera;

import android.net.Uri;

public class Camera {
    private String description;
    private String content;
    private Uri imageSoucre;

    public Camera(String description, String content, Uri imageSoucre) {
        this.description = description;
        this.content = content;
        this.imageSoucre = imageSoucre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getImageSoucre() {
        return imageSoucre;
    }

    public void setImageSoucre(Uri imageSoucre) {
        this.imageSoucre = imageSoucre;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", imageSoucre=" + imageSoucre +
                '}';
    }
}
