package az.nasru11a.nurbot.domain.enumaration;

import lombok.Getter;

public enum NavigationConstants {
    NAVIGATION_BUTTON("NVGBTN"),
    NAVIGATION_BUTTON_PREVIOUS("<"),
    NAVIGATION_BUTTON_NEXT(">"),
    NAVIGATION_BUTTON_MAIN("Əsas mövzular");

    private String text;

    NavigationConstants(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
