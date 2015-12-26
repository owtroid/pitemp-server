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
package com.owtroid.pits.sensors;

import java.util.Date;

/**
 *
 * @author Torgny Johansson <owtroid@gmail.com>
 * @param <T>
 */
public class SensorValue<T> {
    
    private T value;
    private Date timestamp;
    
    public SensorValue(T value, Date timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
    
    public T getValue() {
        return value;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
