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
package com.owtroid.pits.sensors.tempsensors;

import com.owtroid.pits.sensors.Sensor;
import com.owtroid.pits.sensors.SensorValue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Torgny Johansson <owtroid@gmail.com>
 */
public class TempSensor implements Sensor<Double> {

    private List<SensorValue<Double>> values;
    private String id, description, name;
    
    public TempSensor(String id, String name, String description, SensorValue<Double> value) {
        this.id = id;
        this.name = name;
        this.description = description;
        values = new ArrayList<>();
        values.add(value);
    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SensorValue<Double> getLastValue() {
        return values.get(values.size()-1);
    }

    /**
     * @param value the value to set
     */
    @Override
    public void addValue(SensorValue<Double> value) {
        values.add(value);
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<SensorValue<Double>> getValues() {
        return values;
    }
    
}
