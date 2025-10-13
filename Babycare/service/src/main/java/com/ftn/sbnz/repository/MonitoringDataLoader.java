package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.dtos.MonitoringData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MonitoringDataLoader {

    public static List<MonitoringData> loadFromCSV(String filePath) {
        List<MonitoringData> dataList = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream(), StandardCharsets.UTF_8))) {

            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                try {
                    String[] values = line.split(csvSplitBy);

                    if (values.length >= 3) {
                        int heartRate = Integer.parseInt(values[0].trim());
                        int respirationRate = Integer.parseInt(values[1].trim());
                        boolean isOxygen = Boolean.parseBoolean(values[2].trim());

                        if (heartRate >= 100 && heartRate <= 240 &&
                                respirationRate >= 60 && respirationRate <= 100) {

                            MonitoringData data = new MonitoringData(heartRate, respirationRate, isOxygen);
                            dataList.add(data);
                        } else {
                            System.err.println("Nevažeće vrednosti u liniji: " + line);
                        }
                    } else {
                        System.err.println("Nedovoljno kolona u liniji: " + line);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Greška pri parsiranju linije: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Greška pri čitanju fajla: " + filePath);
            e.printStackTrace();
        }

        return dataList;
    }


    public static MonitoringData loadSingleRecord(String filePath, int lineNumber) {
        String line;
        String csvSplitBy = ",";
        int currentLine = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (currentLine == lineNumber) {
                    String[] values = line.split(csvSplitBy);

                    if (values.length >= 3) {
                        int heartRate = Integer.parseInt(values[0].trim());
                        int respirationRate = Integer.parseInt(values[1].trim());
                        boolean isOxygen = Boolean.parseBoolean(values[2].trim());

                        return new MonitoringData(heartRate, respirationRate, isOxygen);
                    }
                }
                currentLine++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

}