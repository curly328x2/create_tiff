package com.tool.TiffCreater;

import com.tool.TiffCreater.service.CreateTiffService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * tiff変換処理の実行クラス
 */
@SpringBootApplication
public class TiffCreaterApplication implements CommandLineRunner {

	private final CreateTiffService createTiffService;

	public TiffCreaterApplication(CreateTiffService createTiffService) {
		this.createTiffService = createTiffService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TiffCreaterApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		createTiffService.convertPdfToTiff();
	}

}
