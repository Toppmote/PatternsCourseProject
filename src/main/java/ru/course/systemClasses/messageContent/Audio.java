package ru.course.systemClasses.messageContent;

import ru.course.prototype.CloneableObj;

import java.util.Objects;

/**
 * Класс, реализующий аудиоконтент сообщения
 */
public class Audio implements MessageContent {

    private int audioHashValue;

    public Audio(int audioHashValue) {
        this.audioHashValue = audioHashValue;
    }

    public Audio(Audio audio) {
        this.audioHashValue = audio.getAudioHashValue();
    }

    @Override
    public int getContentHashCode() {
        return this.hashCode();
    }

    @Override
    public CloneableObj clone() {
        return new Audio(this);
    }

    public int getAudioHashValue() {
        return audioHashValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audio audio = (Audio) o;
        return getAudioHashValue() == audio.getAudioHashValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAudioHashValue());
    }

    public void setAudioHashValue(int audioHashValue) {
        this.audioHashValue = audioHashValue;
    }
}
