package com.example.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SampleJobService {

    @Autowired
    MailService mailService;

    Logger logger = LoggerFactory.getLogger(getClass());

    public void executeSampleJob() {
        logger.info("Job running on " + new Date());
        //mailService.sendMessageWithZippedAttachment("davidefi91@hotmail.it","Test applicativo", "Testo dell'email di prova con allegato Zip, ciao Juannnn");
    }
}
