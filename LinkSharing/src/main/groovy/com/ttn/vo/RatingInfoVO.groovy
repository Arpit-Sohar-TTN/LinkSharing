package com.ttn.vo

/**
 * Created by mayank on 7/5/17.
 */
class RatingInfoVO {
	Integer totalVotes
	Float averageScore
	Integer totalScore


	@Override
	String toString() {
		return "RatingInfoVO{" +
				"totalVotes=" + totalVotes +
				", averageScore=" + averageScore +
				", totalScore=" + totalScore +
				'}'
	}
}
