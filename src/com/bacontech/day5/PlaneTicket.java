package com.bacontech.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaneTicket {
    public String code;
    public String rowCode;
    public String colCode;
    public long row;
    public long col;

    private static String REGEX = "^([BF]+)([RL]+)$";
    private static Pattern PATTERN = Pattern.compile(REGEX);

    PlaneTicket(String code) {
        this.code = code;
        this.parseCodes(code);
        this.row = Day5.findRow(this.rowCode);
        this.col = Day5.findCol(this.colCode);
    }

    long getSeatId() {
        return ((this.row * 8) + this.col);
    }


    private void parseCodes(String code) {
        Matcher matcher = PATTERN.matcher(code);
        if (matcher.find()) {
            this.rowCode = matcher.group(1);
            this.colCode = matcher.group(2);

        } else {
            this.rowCode = "";
            this.colCode = "";
        }
    }
}
