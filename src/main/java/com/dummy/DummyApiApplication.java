package com.dummy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableScheduling
public class DummyApiApplication implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(DummyApiApplication.class);


	@Autowired
	Scrape scrape;

	public static void main(String[] args) {

		SpringApplication.run(DummyApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Scrapping start.....");

		scrape.startScrapping();

		logger.info("Scrapping end.....");
	}

	@Scheduled(fixedRate = 300000)//5 Minute
	public void cronJobSch() throws Exception {

		logger.info("Scrapping start.....");

		scrape.startScrapping();

		logger.info("Scrapping end.....");

	}

}
