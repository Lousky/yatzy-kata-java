package com.github.lousky.kata.yatzy;

import java.util.List;

/**
 * Represents a roll of 5 dices.
 */
public class Roll {
	
	private List<Integer> diceValueList;
	
	public Roll(int diceValue1, int diceValue2, int diceValue3, int diceValue4, int diceValue5) {
		this.diceValueList = List.of(diceValue1, diceValue2, diceValue3, diceValue4, diceValue5);
	}
	
	public List<Integer> getDiceValueList() {
		return this.diceValueList;
	}
	
}
