package com.example.KinoLargo_frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kino-exchange", url = "${backend.base-url}/exchange")
public interface ExchangeClient {

    @GetMapping("/try")
    Double exchangeEuroToTRY();

}
