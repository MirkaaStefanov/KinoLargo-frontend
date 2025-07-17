package com.example.KinoLargo_frontend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieType {
    D2("2D"),
    D3("3D");

    private final String name;

}
