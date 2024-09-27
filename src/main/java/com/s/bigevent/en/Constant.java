package com.s.bigevent.en;

public enum Constant {
    LIKE("like::"),
    INTEREST("interest::"),
    FANS("fans::");

    final String v;

    Constant(String s) {
        this.v = s;
    }

    public String value() {
        return v;
    }
}
