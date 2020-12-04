package com.bacontech.day4;

import com.bacontech.helpers.BaconFileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
    public static void main (String[] args) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day4", "day4-input.txt");

        Long answer = day4Part1(lines);

    }

    // 1023
    // 214
    // 123 part 2
    public static Long day4Part1(List<String> lines) {
        List<String> eyecolors = List.of("amb","blu","brn","gry","grn","hzl","oth");
        long validPassports = 0L;
        Map<String, String> map = new HashMap<>();
        for (String line : lines) {
            if (line.isBlank()){
                map = new HashMap<>();
            }

            String[] fields = line.split(" ");
            for (String field: fields) {
                if (field.contains(":")) {
                    String key = field.split(":")[0];
                    String value = field.split(":")[1];
                    map.put(key, value);
                }
            }

            if (map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") && map.containsKey("hgt") &&
                map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")) {

                if(Integer.parseInt(map.get("byr")) > 1919 && Integer.parseInt(map.get("byr")) < 2003) {
                    if(Integer.parseInt(map.get("iyr")) >= 2010 && Integer.parseInt(map.get("iyr")) <= 2020) {
                        if(Integer.parseInt(map.get("eyr")) >= 2020 && Integer.parseInt(map.get("eyr")) <= 2030) {
                            if((map.get("hgt").contains("cm") && Integer.parseInt(map.get("hgt").split("cm")[0]) >= 150 && Integer.parseInt(map.get("hgt").split("cm")[0]) <= 193) ||
                                (map.get("hgt").contains("in") && Integer.parseInt(map.get("hgt").split("in")[0]) >= 59 && Integer.parseInt(map.get("hgt").split("in")[0]) <= 76)
                            ) {
                                if(map.get("hcl").matches("#[0-9a-f]{6}")){
                                    if (eyecolors.contains(map.get("ecl"))) {
                                        if (map.get("pid").matches("\\d{9}")) {
                                            validPassports++;
                                            map = new HashMap<>();
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }




            }



        }
        System.out.println(validPassports);
        return validPassports;
    }
}
