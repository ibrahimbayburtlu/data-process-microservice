package com.ibrahimbayburtlu.datageneratorservice.service;

import com.ibrahimbayburtlu.datageneratorservice.entity.RandomData;
import com.ibrahimbayburtlu.datageneratorservice.repository.RandomDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RandomDataService {
    private static final Logger logger = LoggerFactory.getLogger(RandomDataService.class);

    private final RandomDataRepository randomDataRepository;

    public RandomDataService(RandomDataRepository randomDataRepository) {
        this.randomDataRepository = randomDataRepository;
    }

    public List<RandomData> createData(int dataSize){

        List<RandomData> randomDataList = new ArrayList<>();
        for(int i = 0; i < dataSize; i++){

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            int randomNumber = (int) (Math.random() * 100);
            String dataToHash = timestamp.toString() + randomNumber;
            logger.info(dataToHash);
            String md5Hash = calculateMD5Hash(dataToHash);

            logger.info(md5Hash);
            if (md5Hash == null) {
                md5Hash = "00";
            }

            String lastTwoCharacters = md5Hash.substring(md5Hash.length() - 2);
            String data = "Timestamp" + timestamp.toString() + ", Random Integer: " + randomNumber + ",Last 2 Characters: " + lastTwoCharacters;
            logger.info(data);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RandomData randomData = new RandomData(timestamp,randomNumber,lastTwoCharacters,md5Hash,data);
            randomDataList.add(randomData);
        }
        return randomDataList;
    }

    private String calculateMD5Hash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(data.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, messageDigest);
            StringBuilder hash = new StringBuilder(number.toString(16));

            while (hash.length() < 32) {
                hash.insert(0, "0");
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void saveData(List<RandomData> randomDataList) {
        randomDataRepository.saveAll(randomDataList);
    }
}
