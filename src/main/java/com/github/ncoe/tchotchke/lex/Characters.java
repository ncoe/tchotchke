/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.function.CharacterPredicate;

/**
 * Constants for  working with characters
 */
public final class Characters {
    public static final int CP_NUL = 0x00;
    public static final int CP_BS = 0x08;
    public static final int CP_HT = 0x09;
    public static final int CP_LF = 0x0a;
    public static final int CP_VT = 0x0b;
    public static final int CP_FF = 0x0c;
    public static final int CP_CR = 0x0d;
    public static final int CP_ESC = 0x1b;
    public static final int CP_FS = 0x1c;
    public static final int CP_GS = 0x1d;
    public static final int CP_RS = 0x1e;
    public static final int CP_US = 0x1f;
    public static final int CP_DQ = 0x22;
    public static final int CP_QT = 0x27;
    public static final int CP_BACKSLASH = 0x5c;
    public static final int CP_DEL = 0x7f;

    public static final CharacterPredicate NUL = of(CP_NUL);
    public static final CharacterPredicate BS = of(CP_BS);
    public static final CharacterPredicate HT = of(CP_HT); //t
    public static final CharacterPredicate LF = of(CP_LF); //n
    public static final CharacterPredicate VT = of(CP_VT);
    public static final CharacterPredicate FF = of(CP_FF); //f
    public static final CharacterPredicate CR = of(CP_CR); //r
    public static final CharacterPredicate ESC = of(CP_ESC);
    public static final CharacterPredicate FS = of(CP_FS);
    public static final CharacterPredicate GS = of(CP_GS);
    public static final CharacterPredicate RS = of(CP_RS);
    public static final CharacterPredicate US = of(CP_US);
    public static final CharacterPredicate DQ = of(CP_DQ);
    public static final CharacterPredicate QT = of(CP_QT);
    public static final CharacterPredicate BACKSLASH = of(CP_BACKSLASH);
    public static final CharacterPredicate DEL = of(CP_DEL);

    public static final CharacterPredicate ASCII = inclusive(CP_NUL, CP_DEL);
    public static final CharacterPredicate CONTROL = inclusive(CP_NUL, CP_US);

    public static final CharacterPredicate SPACE = of(' ');
    public static final CharacterPredicate DIGIT = inclusive('0', '9');
    public static final CharacterPredicate LOWER = inclusive('a', 'z');
    public static final CharacterPredicate UPPER = inclusive('A', 'Z');

    private Characters() {
        //empty
    }

    /**
     * Create a predicate to match a specific character.
     *
     * @param value the value to match
     * @return the predicate
     */
    public static CharacterPredicate of(char value) {
        return of((int) value);
    }

    /**
     * Create a predicate to match a specific code point.
     *
     * @param value the value to match
     * @return the predicate
     */
    public static CharacterPredicate of(int value) {
        return ch -> ch == value;
    }

    /**
     * Create a predicate to matcha range of characters.
     *
     * @param beg the beginning code point
     * @param end the ending code point
     * @return the predicate
     */
    public static CharacterPredicate inclusive(char beg, char end) {
        return inclusive(beg, (int) end);
    }

    /**
     * Create a predicate to matcha range of code points.
     *
     * @param beg the beginning code point
     * @param end the ending code point
     * @return the predicate
     */
    public static CharacterPredicate inclusive(int beg, int end) {
        return ch -> beg <= ch && ch <= end;
    }

    /**
     * Create a predicate that matches all characters
     *
     * @return the predicate
     */
    public static CharacterPredicate any() {
        return _ -> true;
    }
}
