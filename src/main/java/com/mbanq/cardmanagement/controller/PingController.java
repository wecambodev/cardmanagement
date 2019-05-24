package com.mbanq.cardmanagement.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Ping", position = 1)
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity index(HttpServletRequest request ) {

        HashMap response = new HashMap();
        response.put("message", "Api healthy is good");
        response.put("version_code", "v1");
        response.put("version_name", "1.0.1");
        response.put("ip_address", request.getRemoteAddr());
        response.put("status_code", 200);

        return  new ResponseEntity(response, HttpStatus.OK);

    }
}
