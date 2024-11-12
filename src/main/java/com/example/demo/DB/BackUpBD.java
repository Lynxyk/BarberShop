package com.example.demo;

import java.io.IOException;


public class BackUpBD {
    public void backupPostgre() {
        String user = "postgres";
        String password = "12345";
        String backupPath = "C:/testBackUpBd/backup.sql";

        try {
            // Создание процесса для выполнения команды pg_dump
            ProcessBuilder pb = new ProcessBuilder(
                    "C:/Program Files/PostgreSQL/15/bin/pg_dump",
                    "-h",
                    "localhost",
                    "-U",
                    user,
                    "-f",
                    backupPath,
                    "BarberShop"
            );

            // Установка переменных окружения для команды pg_dump
            pb.environment().put("PGPASSWORD", password);

            // Запуск процесса
            Process process = pb.start();

            // Ожидание завершения процесса резервного копирования
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Резервная копия успешно создана.");
            } else {
                System.out.println("Ошибка при создании резервной копии.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}