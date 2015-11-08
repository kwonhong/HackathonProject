package com.hackathonproject.Routine;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by james on 15-11-07.
 */

@Data @AllArgsConstructor
public class Routine extends SugarRecord<Routine> {
    public Routine() {
        // Default Constructor required for sugarRecord
    }

    private int hour;
    private int entitypTypeID;
    private int entityID;
    private int frequency;
}
