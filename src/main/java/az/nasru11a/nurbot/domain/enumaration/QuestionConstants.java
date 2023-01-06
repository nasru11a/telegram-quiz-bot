package az.nasru11a.nurbot.domain.enumaration;

public enum QuestionConstants {
    START_CDATA("QTN"),
    START_QUIZ("Başla"),
    END("Bitir"),
    END_CDATA("ECD"),
    NEXT_QUESTION(">"),
    NEXT_CDATA("NCD"),
    PREVIOUS_QUESTION("<"),
    PREVIOUS_CDATA("PCD");
    private String text;

    QuestionConstants(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
