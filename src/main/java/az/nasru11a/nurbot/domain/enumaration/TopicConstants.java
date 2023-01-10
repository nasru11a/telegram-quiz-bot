package az.nasru11a.nurbot.domain.enumaration;

public enum TopicConstants {
    PARENT_TOPIC_MARK("PRTTPC"),
    CHILD_TOPIC_MARK("CLDTPC"),
    EMPTY_REPLACEMENT_STRING("");

    private final String text;

    TopicConstants(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
