/*
 * Copyright (C) 2015 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.owtroid.pits.sensors.tempsensors.dao;

import com.owtroid.pits.sensors.Sensor;
import com.owtroid.pits.sensors.SensorValue;
import com.owtroid.pits.sensors.tempsensors.TempSensor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Torgny Johansson <owtroid@gmail.com>
 */
public class SensorReader {
    private static String SENSORS_PATH = "/sys/bus/w1/devices/";
    public List<Sensor> getSensors() {
        File sensorsDir = new File(SENSORS_PATH);
        File[] sensorDirs = sensorsDir.listFiles();
        List<Sensor> sensors = new ArrayList<>();
        
        if (sensorDirs != null) {
            for (File sensorDir : sensorDirs) {
                Sensor sensor = getSensorFromFile(sensorDir);
                if (sensor != null) {
                    sensors.add(sensor);
                }
            }
        }
        return sensors;
    }
    
        private Sensor getSensorFromFile(File sensor) {
        File[] sensorFiles = sensor.listFiles();
        File nameFile = null;
        File valueFile = null;
        for (File sensorFile : sensorFiles) {
            if (sensorFile.getName().equals("w1_slave")) {
                valueFile = sensorFile;
                continue;
            }
            if (sensorFile.getName().equals("name")) {
                nameFile = sensorFile;
            }
        }
        
        Double value = readValueFile(valueFile);
        String name = readNameFile(nameFile);
        
        if (value != null && name != null) {
            return new TempSensor(name, name, "", new SensorValue<>(value, new Date()));
        } else {
            return null;
        }
    }
    
    private Double readValueFile(File valueFile) {
        if (valueFile == null) {
            return null;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(valueFile))) {
            String line;
            // discard first line
            reader.readLine();
            // now read temp info
            line = reader.readLine();
            int idx = line.indexOf("t=");
            String tempStr = line.substring(idx+2, line.length());
            return Double.parseDouble(tempStr) / 1000;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String readNameFile(File nameFile) {
        if (nameFile == null) {
            return null;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String line;
            line = reader.readLine();
            return line.trim();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
