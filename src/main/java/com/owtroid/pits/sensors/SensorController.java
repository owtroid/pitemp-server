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

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Torgny Johansson
 */
@RestController
public class SensorController {
    
    @RequestMapping(value="/sensors", method=RequestMethod.GET)
    public List<Sensor> getSensors() {
        List<Sensor> sensors = new ArrayList<>();
        sensors.add(new Sensor() {
            @Override
            public String getId() {
                return "1";
            }

            @Override
            public String getDescription() {
                return "A description";
            }

            @Override
            public String getName() {
                return "1";
            }
        });
        
        return sensors;
    }
    
    @RequestMapping("/sensors/{id}")
    public Sensor getSensor(@PathVariable("id") String id) {
        return new Sensor() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getDescription() {
                return "A description of " + id;
            }

            @Override
            public String getName() {
                return id;
            }
        };
    }
}
