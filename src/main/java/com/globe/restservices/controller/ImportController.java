package com.globe.restservices.controller;

import com.globe.restservices.service.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/import")
public class ImportController {
    Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    private ImportService importService;

    @Async
    public void process(String filename, byte[] bs){
        if(!filename.equals("extract")) {
            importService.excelToMapping(filename, bs);
        } else {
            importService.excelToExtract(bs);
        }

    }

    @PostMapping("/mapping")
    public ResponseEntity<String> mapping(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();

            process(filename, file.getBytes());
            logger.info(Thread.currentThread().getName());

            return new ResponseEntity<>(filename + " was successfully uploaded and being process", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/extract")
    public ResponseEntity<String> extract(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();

            process("extract", file.getBytes());
            logger.info(Thread.currentThread().getName());

            return new ResponseEntity<>(filename + " was successfully uploaded and being process", HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
