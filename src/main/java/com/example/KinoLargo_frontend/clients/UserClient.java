package com.example.KinoLargo_frontend.clients;

import com.example.KinoLargo_frontend.dtos.auth.PublicUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kino-users", url = "${backend.base-url}/users")
public interface UserClient {

    @GetMapping("/me")
    PublicUserDTO getMe(@RequestHeader("Authorization") String auth);

}
