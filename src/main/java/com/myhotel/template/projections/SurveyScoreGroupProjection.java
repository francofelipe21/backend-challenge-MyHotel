package com.myhotel.template.projections;

import java.util.Optional;

public interface SurveyScoreGroupProjection {

    Long DEFAULT_VAL = 1L;

    String getHotelName();
    Long getHotelId();
    Long getScore();
    Long getScoreCount();

    default double weightedScore() {
        Long score = Optional.ofNullable(getScore()).orElse(DEFAULT_VAL);
        Long count = Optional.ofNullable(getScoreCount()).orElse(DEFAULT_VAL);
        return score * count;
    }
}
