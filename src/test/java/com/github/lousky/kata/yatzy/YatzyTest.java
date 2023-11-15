package com.github.lousky.kata.yatzy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class YatzyTest {

	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForChance")
	public void chance_scores_sum_of_all_dice(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.chance(roll));
	}
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForChance() {
	    return Stream.of(
	    		Arguments.of(new Roll(2,3,4,5,1), 15),
				Arguments.of(new Roll(3,3,4,5,1), 16),
				Arguments.of(new Roll(0,0,0,0,0), 0),
				Arguments.of(new Roll(1,1,1,1,1), 5),
				Arguments.of(new Roll(1,1,1,2,2), 7)
	    );
	}

	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForYatzi") 
	public void yatzy_scores_50_if_all_dices_are_the_same(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.yatzy(roll));
	}
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForYatzi() {
		return Stream.of(
				Arguments.of(new Roll(1,1,1,1,1), 50),
				Arguments.of(new Roll(2,2,2,2,2), 50),
				Arguments.of(new Roll(3,3,3,3,3), 50),
				Arguments.of(new Roll(4,4,4,4,4), 50),
				Arguments.of(new Roll(5,5,5,5,5), 50),
				Arguments.of(new Roll(6,6,6,6,6), 50),
				Arguments.of(new Roll(1,5,5,5,5), 0),
				Arguments.of(new Roll(1,1,1,1,5), 0)
		);
	}

	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForOnes") 
	public void ones_scores_sum_of_all_one(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.ones(roll));
	}
    
	private static Stream<Arguments> provideRollsAndExpectedScoresForOnes() {
		return Stream.of(
				Arguments.of(new Roll(1,2,3,4,5), 1),
				Arguments.of(new Roll(1,2,1,4,5), 2),
				Arguments.of(new Roll(6,2,2,4,5), 0),
				Arguments.of(new Roll(1,2,1,1,1), 4)
		);
	}

	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForTwos") 
	public void twos_scores_sum_of_all_twos(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.twos(roll));
	}
    
	private static Stream<Arguments> provideRollsAndExpectedScoresForTwos() {
		return Stream.of(
				Arguments.of(new Roll(1,2,3,2,6), 4),
				Arguments.of(new Roll(2,2,2,2,2), 10),
				Arguments.of(new Roll(1,3,4,5,6), 0)
		);
	}

	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForThrees")
	public void thress_scores_sum_of_all_threes(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.threes(roll));
	}
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForThrees() {
		return Stream.of(
				Arguments.of(new Roll(1,2,3,2,3), 6),
				Arguments.of(new Roll(2,3,3,3,3), 12),
				Arguments.of(new Roll(1,2,4,5,6), 0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForFours")
	public void fours_scores_sum_of_all_fours(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.fours(roll));
	}
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForFours() {
		return Stream.of(
				Arguments.of(new Roll(4,4,4,5,5), 12),
				Arguments.of(new Roll(4,4,5,5,5), 8),
				Arguments.of(new Roll(4,5,5,5,5), 4),
				Arguments.of(new Roll(3,5,5,5,5), 0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForFives")
	public void fives_scores_sum_of_all_fives(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.fives(roll));
	}
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForFives() {
		return Stream.of(
				Arguments.of(new Roll(4,4,4,5,5), 10),
				Arguments.of(new Roll(4,4,5,5,5), 15),
				Arguments.of(new Roll(4,5,5,5,5), 20),
				Arguments.of(new Roll(3,1,2,4,6), 0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForSixes")
	public void sixes_scores_sum_of_all_sixes(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.sixes(roll));
	}
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForSixes() {
		return Stream.of(
				Arguments.of(new Roll(4,4,4,5,5), 0),
				Arguments.of(new Roll(4,4,6,5,5), 6),
				Arguments.of(new Roll(6,5,6,6,5), 18),
				Arguments.of(new Roll(3,1,2,4,5), 0)
		);
	}

	@ParameterizedTest
	@MethodSource("provideRollsAndExpectedScoresForPair")
    public void pair_scores_highest_pair_sum(Roll roll, int expectedScore) {
		assertEquals(expectedScore, Yatzy.pair(roll));
    }
	
	private static Stream<Arguments> provideRollsAndExpectedScoresForPair() {
		return Stream.of(
				Arguments.of(new Roll(3,4,3,5,6), 6),
				Arguments.of(new Roll(5,3,3,3,5), 10),
				Arguments.of(new Roll(5,3,6,6,5), 12),
				Arguments.of(new Roll(3,1,2,1,5), 2),
				Arguments.of(new Roll(3,1,2,4,5), 0)
		);
	}

    @Test
    public void two_Pair() {
        assertEquals(16, Yatzy.two_pair(3,3,5,4,5));
        assertEquals(16, Yatzy.two_pair(3,3,5,5,5));
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
}
