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

    public static final CharacterPredicate NUL = CharacterPredicate.of(CP_NUL);
    public static final CharacterPredicate BS = CharacterPredicate.of(CP_BS);
    public static final CharacterPredicate HT = CharacterPredicate.of(CP_HT); //t
    public static final CharacterPredicate LF = CharacterPredicate.of(CP_LF); //n
    public static final CharacterPredicate VT = CharacterPredicate.of(CP_VT);
    public static final CharacterPredicate FF = CharacterPredicate.of(CP_FF); //f
    public static final CharacterPredicate CR = CharacterPredicate.of(CP_CR); //r
    public static final CharacterPredicate ESC = CharacterPredicate.of(CP_ESC);
    public static final CharacterPredicate FS = CharacterPredicate.of(CP_FS);
    public static final CharacterPredicate GS = CharacterPredicate.of(CP_GS);
    public static final CharacterPredicate RS = CharacterPredicate.of(CP_RS);
    public static final CharacterPredicate US = CharacterPredicate.of(CP_US);
    public static final CharacterPredicate DQ = CharacterPredicate.of(CP_DQ);
    public static final CharacterPredicate QT = CharacterPredicate.of(CP_QT);
    public static final CharacterPredicate BACKSLASH = CharacterPredicate.of(CP_BACKSLASH);
    public static final CharacterPredicate DEL = CharacterPredicate.of(CP_DEL);

    public static final CharacterPredicate ASCII = CharacterPredicate.inclusive(CP_NUL, CP_DEL);
    public static final CharacterPredicate CONTROL = CharacterPredicate.inclusive(CP_NUL, CP_US);

    public static final CharacterPredicate SPACE = CharacterPredicate.of(' ');
    public static final CharacterPredicate DIGIT = CharacterPredicate.inclusive('0', '9');
    public static final CharacterPredicate LOWER = CharacterPredicate.inclusive('a', 'z');
    public static final CharacterPredicate UPPER = CharacterPredicate.inclusive('A', 'Z');

    private Characters() {
        //empty
    }
}
