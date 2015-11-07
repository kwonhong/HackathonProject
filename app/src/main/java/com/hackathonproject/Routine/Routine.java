package com.hackathonproject.Routine;

import com.orm.SugarRecord;

import lombok.Data;

/**
 * Created by james on 15-11-07.
 */

@Data
public class Routine extends SugarRecord<Routine> {
    public Routine() {
        // Default Constructor required for sugarRecord
    }

    private long time;
    private String data;
}
