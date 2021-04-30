package io.hari.democonnectors;

import io.hari.democonnectors.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoConnectorsApplication implements CommandLineRunner {
	final AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(DemoConnectorsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("appConfig = " + appConfig);
		final AppConfig.Database source = appConfig.getSource();
		System.out.println("source = " + source);
		final AppConfig.Schema schema = source.getSchema();
		System.out.println("schema = " + schema);
		final List<String> columns = schema.getTableColumns();
		System.out.println("columns = " + columns);
		final AppConfig.RemoteMySQL remotemySQL = appConfig.getRemotemySQL();
		System.out.println("remotemySQL = " + remotemySQL);
	}
}
