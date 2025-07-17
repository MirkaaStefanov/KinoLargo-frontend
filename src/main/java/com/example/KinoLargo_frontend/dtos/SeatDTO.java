package com.example.KinoLargo_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    public Long id;
    private int row;
    private int number;
    private List<ProjectionDTO> projections;

}
