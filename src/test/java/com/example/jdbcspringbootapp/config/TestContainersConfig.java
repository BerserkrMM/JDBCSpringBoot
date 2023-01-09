package com.example.jdbcspringbootapp.config;

import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class TestContainersConfig {
    public static class CustomMSSQLContainer extends MSSQLServerContainer<CustomMSSQLContainer> {

        private static final String IMAGE_VERSION = "mcr.microsoft.com/mssql/server:2017-latest";

        private static CustomMSSQLContainer container;

        public CustomMSSQLContainer() {
            super(IMAGE_VERSION);
        }

        public static CustomMSSQLContainer getInstance() {
            if (container == null) {
                container = new CustomMSSQLContainer()
                        .acceptLicense()
                        .withInitScript("resources/sql/init/create_schema.sql")
                        .waitingFor(Wait.forListeningPort());
            }
            return container;
        }

        @Override
        public void start() {
            super.start();
            System.setProperty("spring.datasource.driver-class-name", container.getDriverClassName());
            System.setProperty("spring.datasource.url", container.getJdbcUrl());
            System.setProperty("spring.datasource.username", container.getUsername());
            System.setProperty("spring.datasource.password", container.getPassword());
        }

        @Override
        public void stop() {
            //do nothing, JVM handles shut down
        }

    }
}