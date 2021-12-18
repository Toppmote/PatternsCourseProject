package ru.course.systemClasses.messageContent;

import ru.course.prototype.CloneableObj;

import java.util.Objects;

/**
 * Класс, реализующий фотоконтент сообщения
 */
public class Photo implements MessageContent {

    private int PhotoValue;

    public Photo(int photoValue) {
        PhotoValue = photoValue;
    }

    public Photo(Photo photo) {
        this.PhotoValue = photo.getPhotoValue();
    }

    @Override
    public int getContentHashCode() {
        return this.hashCode();
    }

    @Override
    public CloneableObj clone() {
        return new Photo(this);
    }

    public int getPhotoValue() {
        return PhotoValue;
    }

    public void setPhotoValue(int photoValue) {
        PhotoValue = photoValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return getPhotoValue() == photo.getPhotoValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhotoValue());
    }
}
