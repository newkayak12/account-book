package com.server.base.controller;

import com.server.base.service.DealLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/dealLog")
@Slf4j
@RequiredArgsConstructor
public class DealLogController {
    private final DealLogService dealLogService;
}
