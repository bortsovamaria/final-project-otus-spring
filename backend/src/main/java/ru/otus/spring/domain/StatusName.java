package ru.otus.spring.domain;

public enum StatusName {
    ANALYZE(1),
    IN_PROGRESS(2),
    CODE_REVIEW(3),
    TEST(4),
    PRODUCTION(5);

    private final long value;

    StatusName(final long value) {
        this.value = value;
    }

    public static StatusName getValue(long value) {
        for (StatusName e : StatusName.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }
}
