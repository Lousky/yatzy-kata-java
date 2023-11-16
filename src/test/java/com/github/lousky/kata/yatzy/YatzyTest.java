package com.github.lousky.kata.yatzy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class YatzyTest {

	@ParameterizedTest
	@CsvSource({"'2,3,4,5,1', 15",
				"'3,3,4,5,1', 16",
				"'0,0,0,0,0', 0",
				"'1,1,1,1,1', 5",
				"'1,1,1,2,2', 7"})
	public void chance_scores_sum_of_all_dice(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.chance(buildRollFromArgument(diceValues)));
	}
	
	@ParameterizedTest
	@CsvSource({"'1,1,1,1,1', 50",
				"'2,2,2,2,2', 50",
				"'3,3,3,3,3', 50",
				"'4,4,4,4,4', 50",
				"'5,5,5,5,5', 50",
				"'6,6,6,6,6', 50",
				"'1,5,5,5,5', 0",
				"'1,1,1,1,5', 0"})
	public void yatzy_scores_50_if_all_dices_are_the_same(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.yatzy(buildRollFromArgument(diceValues)));
	}
	
	@ParameterizedTest
	@CsvSource({"'1,2,3,4,5', 1",
				"'1,2,1,4,5', 2",
				"'6,2,2,4,5', 0",
				"'1,2,1,1,1', 4"})
	public void ones_scores_sum_of_all_one(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.ones(buildRollFromArgument(diceValues)));
	}
    
	@ParameterizedTest
	@CsvSource({"'1,2,3,2,6', 4",
				"'2,2,2,2,2', 10",
				"'1,3,4,5,6', 0"})
	public void twos_scores_sum_of_all_twos(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.twos(buildRollFromArgument(diceValues)));
	}
    
	@ParameterizedTest
	@CsvSource({"'1,2,3,2,3', 6",
				"'2,3,3,3,3', 12",
				"'1,2,4,5,6', 0"})
	public void threes_scores_sum_of_all_threes(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.threes(buildRollFromArgument(diceValues)));
	}
	
	@ParameterizedTest
	@CsvSource({"'4,4,4,5,5', 12",
				"'4,4,5,5,5', 8",
				"'4,5,5,5,5', 4",
				"'3,5,5,5,5', 0"})
	public void fours_scores_sum_of_all_fours(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.fours(buildRollFromArgument(diceValues)));
	}
	
	@ParameterizedTest
	@CsvSource({"'4,4,4,5,5', 10",
				"'4,4,5,5,5', 15",
				"'4,5,5,5,5', 20",
				"'3,1,2,4,6', 0"})
	public void fives_scores_sum_of_all_fives(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.fives(buildRollFromArgument(diceValues)));
	}
	
	@ParameterizedTest
	@CsvSource({"'4,4,4,5,5', 0",
				"'4,4,6,5,5', 6",
				"'6,5,6,6,5', 18",
				"'3,1,2,4,5', 0"})
	public void sixes_scores_sum_of_all_sixes(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.sixes(buildRollFromArgument(diceValues)));
	}
	
	@ParameterizedTest
	@CsvSource({"'3,4,3,5,6', 6",
				"'5,3,3,3,5', 10",
				"'5,3,6,6,5', 12",
				"'3,1,2,1,5', 2",
				"'3,1,2,4,5', 0"})
    public void pair_scores_highest_pair_sum(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.pair(buildRollFromArgument(diceValues)));
    }
	
	@ParameterizedTest
	@CsvSource({"'3,3,5,4,5', 16",
				"'3,3,5,5,5', 16",
				"'2,2,1,2,3', 0"})
    public void two_pair_scores_two_pair_sum(String diceValues, int expectedScore) {
        assertEquals(expectedScore, Yatzy.twoPair(buildRollFromArgument(diceValues)));
    }

    @Test
    public void three_of_a_kind() 
    {
        assertEquals(9, Yatzy.three_of_a_kind(3,3,3,4,5));
        assertEquals(15, Yatzy.three_of_a_kind(5,3,5,4,5));
        assertEquals(9, Yatzy.three_of_a_kind(3,3,3,3,5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, Yatzy.four_of_a_kind(3,3,3,3,5));
        assertEquals(20, Yatzy.four_of_a_kind(5,5,5,4,5));
        assertEquals(9, Yatzy.three_of_a_kind(3,3,3,3,3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(1,2,3,4,5));
        assertEquals(15, Yatzy.smallStraight(2,3,4,5,1));
        assertEquals(0, Yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(6,2,3,4,5));
        assertEquals(20, Yatzy.largeStraight(2,3,4,5,6));
        assertEquals(0, Yatzy.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(6,2,2,2,6));
        assertEquals(0, Yatzy.fullHouse(2,3,4,5,6));
    }
    
	private Roll buildRollFromArgument(String diceValues) {
		int[] diceValuesArray = Arrays.asList(diceValues.split(","))
	    	.stream()
	    	.mapToInt(value -> Integer.parseInt(value))
	    	.toArray();
		return new Roll(diceValuesArray[0],
				 		diceValuesArray[1],
						diceValuesArray[2],
						diceValuesArray[3],
						diceValuesArray[4]);
	}
}
