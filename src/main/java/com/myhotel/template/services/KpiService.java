package com.myhotel.template.services;

import com.myhotel.template.models.WeightedKpiResult;
import com.myhotel.template.projections.SurveyScoreGroupProjection;
import com.myhotel.template.repositories.SurveyResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class KpiService {

    private final SurveyResultRepository repository;

    public KpiService(SurveyResultRepository repository){
        this.repository = repository;
    }

    public List<WeightedKpiResult<String, Double>> getWeightedAverageScore() {
        List<SurveyScoreGroupProjection> groupedScores = repository.findGroupedScoreData();
        Map<Long, List<SurveyScoreGroupProjection>> groupedByHotel = groupByHotel(groupedScores);

        return groupedByHotel.values().stream()
                .map(projectionList -> calculateWeightedAverage(
                        projectionList.stream().map(SurveyScoreGroupProjection::getHotelName).findAny().get(),
                        projectionList))
                .toList();
    }

    private Map<Long, List<SurveyScoreGroupProjection>> groupByHotel(List<SurveyScoreGroupProjection> groupedScores) {
        return groupedScores.stream()
                .collect(Collectors.groupingBy(SurveyScoreGroupProjection::getHotelId));
    }

    /**
     * Calcula el promedio ponderado de las puntuaciones para un hotel dado, utilizando la fórmula:
     *
     * <pre>
     *     weightedAverage = (Σ (score * count)) / Σ count
     * </pre>
     *
     * Donde:
     * - `score` es la puntuación dada por los huéspedes.
     * - `count` es la cantidad de veces que se dio esa puntuación.
     *
     * Este método suma los productos de cada puntuación por su frecuencia (numerador) y los divide
     * por la suma total de frecuencias (denominador).
     *
     * @param hotelName nombre del hotel
     * @param projectionList lista de proyecciones con score y cantidad para ese hotel
     * @return un DTO con el hotel y su promedio ponderado
     */
    private WeightedKpiResult<String, Double> calculateWeightedAverage(String hotelName, List<SurveyScoreGroupProjection> projectionList) {

        double numerator = projectionList.stream()
                .mapToDouble(SurveyScoreGroupProjection::weightedScore)
                .sum();

        double denominator = projectionList.stream()
                .map(row -> Optional.ofNullable(row.getScoreCount()).orElse(SurveyScoreGroupProjection.DEFAULT_VAL))
                .mapToDouble(Long::doubleValue)
                .sum();

        double weightedAvg = denominator == 0 ? 0.0 : numerator / denominator;

        return new WeightedKpiResult<>(hotelName, weightedAvg);
    }
}
