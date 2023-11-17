package com.github.lousky.kata.yatzy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

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

	@ParameterizedTest
    @CsvSource({"'3,3,3,4,5', 9",
				"'5,3,5,4,5', 15",
				"'3,3,3,3,5', 9",
				"'3,3,3,3,3', 9",
				"'3,3,1,2,2', 0"})
    public void three_of_a_kind_scores_sum_of_three_same_value(String diceValues, int expectedScore) {
		assertEquals(expectedScore, Yatzy.threeOfAKind(buildRollFromArgument(diceValues)));
    }

    @ParameterizedTest
    @CsvSource({"'3,3,3,3,5', 12",
				"'5,5,5,4,5', 20",
				"'2,2,2,2,2', 8"})
    public void four_of_a_kind_scores_sum_of_four_same_value(String diceValues, int expectedScore) {
    	assertEquals(expectedScore, Yatzy.fourOfAKind(buildRollFromArgument(diceValues)));
    }

    @ParameterizedTest
    @CsvSource({"'1,2,3,4,5', 15",
				"'2,3,4,5,1', 15",
				"'3,2,1,5,4', 15",
				"'1,2,2,4,5', 0",
				"'2,3,4,5,6', 0"})
	public void small_straight_scores_15_if_include_digit_one_to_five(String diceValues, int expectedScore) {
    	assertEquals(expectedScore, Yatzy.smallStraight(buildRollFromArgument(diceValues)));
	}

    @ParameterizedTest
    @CsvSource({"'6,2,3,4,5', 20",
				"'2,3,4,5,6', 20",
				"'1,2,2,4,5', 0",
				"'1,2,3,4,5', 0"})
    public void large_straight_scores_20_if_include_digit_two_to_six(String diceValues, int expectedScore) {
    	assertEquals(expectedScore, Yatzy.largeStraight(buildRollFromArgument(diceValues)));
    }

    @ParameterizedTest
    @CsvSource({"'6,2,2,2,6', 18",
    			"'1,2,1,2,1', 7",
				"'2,3,4,5,6', 0",
				"'2,2,3,4,5', 0",
				"'3,3,3,4,5', 0",
				"'3,3,3,3,5', 0"})
    public void full_house_scores_sum_of_two_of_a_kind_and_three_of_a_kind(String diceValues, int expectedScore) {
        assertEquals(expectedScore, Yatzy.fullHouse(buildRollFromArgument(diceValues)));
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
