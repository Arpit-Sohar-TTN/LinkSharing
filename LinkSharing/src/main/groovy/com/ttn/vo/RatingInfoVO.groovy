package com.ttn.vo

/**
 * Created by arpit on 7/5/17.
 */
class RatingInfoVO {
    Integer totalVotes
    Float averageScore
    Integer totalScore


    @Override
    public String toString() {
        return "RatingInfoVO{" +
                "totalVotes=" + totalVotes +
                ", averageScore=" + averageScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
