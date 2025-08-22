package com.myhotel.template.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeightedKpiResult<T, S>(
        @JsonProperty("hotel_name") T hotelName,
        @JsonProperty("weighted_average") S weightedAverage
) {

    public WeightedKpiResult {
        if (hotelName == null) {
            throw new IllegalArgumentException("Hotel name cannot be null");
        }
        if (weightedAverage == null) {
            throw new IllegalArgumentException("weightedAverage cannot be null");
        }
    }

}