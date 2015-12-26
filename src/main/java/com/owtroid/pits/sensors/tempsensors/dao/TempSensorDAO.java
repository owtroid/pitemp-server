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
import com.owtroid.pits.sensors.dao.SensorDAO;
import com.owtroid.pits.sensors.tempsensors.TempSensor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.stereotype.Component;

/**
 *
 * @author Torgny Johansson <owtroid@gmail.com>
 */
@Component
public class TempSensorDAO implements SensorDAO {
    private Map<String, Sensor> sensors;
    
    public TempSensorDAO() {
        sensors = new HashMap<>();
        setupSensorReader();
    }
    
    @Override
    public List<Sensor> getSensors() {
        return new ArrayList(sensors.values());
    }

    @Override
    public Sensor getSensor(String id) {
        return sensors.get(id);
    }
    
    private void setupSensorReader() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getSensorReadings();
            }
        }, 0, 60000);
    }
    
    private void getSensorReadings() {
        SensorReader sensorReader = new SensorReader();
        for (Sensor sensor : sensorReader.getSensors()) {
            if (sensors.containsKey(sensor.getId())) {
                sensors.get(sensor.getId()).addValue(sensor.getLastValue());
            } else {
                sensors.put(sensor.getId(), sensor);
            }
        }
    }
}
