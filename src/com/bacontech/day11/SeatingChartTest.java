package com.bacontech.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SeatingChartTest {

    @ParameterizedTest
    @CsvSource({
        "0,0,3",
        "1,1,8",
        "2,2,3",
        "0,1,5",
        "2,1,5",
    })
    void testSeatedAroundMe_fullSeating (int row, int col, int expectedAroundMe) {
        SeatingChart seatingChart = new SeatingChart(3, 3);
        seatingChart.addSeatingRow(0, "###");
        seatingChart.addSeatingRow(1, "###");
        seatingChart.addSeatingRow(2, "###");

        assertEquals(expectedAroundMe, seatingChart.numberOfPeopleSeatedAroundMe(row, col));
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,0",
        "1,1,0",
        "2,2,0",
        "0,1,0",
    })
    void testSeatedAroundMe_empty (int row, int col, int expectedAroundMe) {
        SeatingChart seatingChart = new SeatingChart(3, 3);
        seatingChart.addSeatingRow(0, "LLL");
        seatingChart.addSeatingRow(1, "LLL");
        seatingChart.addSeatingRow(2, "LLL");

        assertEquals(expectedAroundMe, seatingChart.numberOfPeopleSeatedAroundMe(row, col));
    }


    @ParameterizedTest
    @CsvSource({
        "3,3,0",
        "6,6,2",
        "6,0,2",
        "0,6,2",
        "0,0,2",
    })
    void testSeatsSeen_1 (int row, int col, int expectedSeen) {
        SeatingChart seatingChart = new SeatingChart(7, 7);

        seatingChart.addSeatingRow(0,".##.##.");
        seatingChart.addSeatingRow(1,"#.#.#.#");
        seatingChart.addSeatingRow(2,"##...##");
        seatingChart.addSeatingRow(3,".......");
        seatingChart.addSeatingRow(4,"##...##");
        seatingChart.addSeatingRow(5,"#.#.#.#");
        seatingChart.addSeatingRow(6,".##.##.");


        assertEquals(expectedSeen, seatingChart.numberOfSeatsCanSee(row, col));
    }
}
