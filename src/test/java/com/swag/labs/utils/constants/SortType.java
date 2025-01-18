package com.swag.labs.utils.constants;

public enum SortType {
    A_TO_Z("az"),
    Z_TO_A("za"),
    LOW_TO_HIGH("lohi"),
    HIGH_TO_LOW("hilo");

    public final String text;

    private SortType(String text) {
        this.text = text;
    }
}
