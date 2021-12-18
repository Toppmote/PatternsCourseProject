package ru.course.systemClasses.messageContent;

import ru.course.prototype.CloneableObj;

import java.util.Objects;

/**
 * Класс, реализующий видеоконтент сообщения
 */
public class Video implements MessageContent {

    private String title;

    private int videoHashValue;

    public Video(int videoHashValue) {
        this.videoHashValue = videoHashValue;
    }

    public Video(Video video) {
        this.title = video.getTitle();
        this.videoHashValue = video.getVideoHashValue();
    }

    @Override
    public int getContentHashCode() {
        return this.hashCode();
    }

    @Override
    public CloneableObj clone() {
        return new Video(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVideoHashValue() {
        return videoHashValue;
    }

    public void setVideoHashValue(int videoHashValue) {
        this.videoHashValue = videoHashValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return getVideoHashValue() == video.getVideoHashValue() && Objects.equals(getTitle(), video.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getVideoHashValue());
    }
}
