package az.nasru11a.quiz.bot.domain.enumaration;

import lombok.Getter;

@Getter
public enum BotConstants {
    START_MESSAGE("Salam. Sənə suallar verib imtahana hazırlaşmaqda kömək edəcəm"),
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
