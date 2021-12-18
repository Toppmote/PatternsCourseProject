package ru.course.systemClasses.messageContent;



import ru.course.prototype.CloneableObj;

import java.util.Objects;

/**
 * Класс, реализующий текст сообщения
 */
public class Text implements MessageContent {

    private String textValue;

    public Text(String textValue) {
        this.textValue = textValue;
    }

    public Text(Text text) {
        this.textValue = text.getTextValue();
    }

    @Override
    public int getContentHashCode() {
        return 0;
    }

    @Override
    public CloneableObj clone() {
        return new Text(this);
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(getTextValue(), text.getTextValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTextValue());
    }
}
