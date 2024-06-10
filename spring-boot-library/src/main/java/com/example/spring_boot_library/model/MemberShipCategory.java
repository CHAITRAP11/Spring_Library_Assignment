package com.example.spring_boot_library.model;

public enum MemberShipCategory {

    SILVER(2, 0),
    GOLD(3, 1),
    PLATINUM(4, 2);

    private final int maxBooks;
    private final int maxMagazines;

    MemberShipCategory(int maxBooks, int maxMagazines) {
        this.maxBooks = maxBooks;
        this.maxMagazines = maxMagazines;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public int getMaxMagazines() {
        return maxMagazines;
    }
}
