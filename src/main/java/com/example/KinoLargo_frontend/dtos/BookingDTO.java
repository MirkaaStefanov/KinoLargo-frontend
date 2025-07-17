package com.example.KinoLargo_frontend.dtos;

import com.example.KinoLargo_frontend.dtos.auth.PublicUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    public Long id;

    private SeatDTO seat;

    private ProjectionDTO projection;

    private BookingType bookingType;

    private PublicUserDTO user;

}
