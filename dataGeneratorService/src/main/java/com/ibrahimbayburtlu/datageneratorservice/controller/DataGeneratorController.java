package com.ibrahimbayburtlu.datageneratorservice.controller;

import com.ibrahimbayburtlu.datageneratorservice.entity.RandomData;
import com.ibrahimbayburtlu.datageneratorservice.service.RandomDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data-generator")
public class DataGeneratorController {

    private final RandomDataService randomDataService;

    private static final Logger logger = LoggerFactory.getLogger(DataGeneratorController.class);

    @Autowired
    public DataGeneratorController(RandomDataService randomDataService) {
        this.randomDataService = randomDataService;
    }

    @RequestMapping("/random-data-generate")
    public String generateData(@RequestParam int dataSize){

        List<RandomData> randomData = randomDataService.createData(dataSize);

        logger.info("Random data created");

        randomDataService.saveData(randomData);

        logger.info("Data saved to database");
        return  dataSize  + " random data were generated and Saved";
    }
}
