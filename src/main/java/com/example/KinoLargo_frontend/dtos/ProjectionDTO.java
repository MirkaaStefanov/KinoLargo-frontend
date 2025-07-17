package com.example.KinoLargo_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectionDTO {

    private UUID id;

    private Date date;

    private List<SeatDTO> seats;

    private MovieDTO movie;

}
