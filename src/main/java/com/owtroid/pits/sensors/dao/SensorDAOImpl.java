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

package com.owtroid.pits.sensors.dao;

import com.owtroid.pits.sensors.Sensor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Torgny Johansson
 */
public class SensorDAOImpl implements SensorDAO {
    @Override
    public List<Sensor> getSensors() {
        File sensorsDir = new File("/sys/bus/w1/devices/");
        File[] sensorFiles = sensorsDir.listFiles();
        List<Sensor> sensors = new ArrayList<>();
        /* TODO: get all sensors and values */
        return sensors;
    }

    @Override
    public Sensor getSensor(String name) {
        /* TODO: get sensor by name */
        return null;
    }
}
