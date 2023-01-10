package az.nasru11a.nurbot.domain.enumaration;

public enum BotConstants {
    START_MESSAGE("Salam. Mən Nur. Sənə suallar verib imtahana hazırlaşmaqda kömək edəcəm"),
    TESTS("Testlər"),
    TESTS_CDATA("TSTS");

    private String text;

    BotConstants(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
