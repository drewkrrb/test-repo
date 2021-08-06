package com.globe.restservices.controller;

import com.globe.restservices.model.MappingView;
import com.globe.restservices.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ViewController {

    @Autowired
    ViewService viewService;

    @GetMapping("mapping")
    public ResponseEntity<List<MappingView>> getAllMapping(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<MappingView> list = viewService.getAllMapping(pageNo, pageSize, sortBy);
        System.out.println(list);

        return new ResponseEntity<List<MappingView>>(list, HttpStatus.OK);
    }
}
