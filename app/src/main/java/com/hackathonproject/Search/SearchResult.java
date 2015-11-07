package com.hackathonproject.Search;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by james on 15-11-07.
 */
@Data @AllArgsConstructor
public class SearchResult {
    private String name;
    private double distance;
}
