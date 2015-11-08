package com.hackathonproject.Routine;

import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;

/**
 * Created by james on 15-11-07.
 */
public class RoutineService {

    private static final String LOGGER = "RoutineServiceLog";

    public Routine getUserRoutine() {
        DateTime dateTime = new DateTime();
        int hour = dateTime.getHourOfDay();

        Select selectQueryBuilder = Select.from(Routine.class)
                .where(Condition.prop("hour").eq(hour));

        List<Routine> routineList = selectQueryBuilder.list();
        Collections.sort(routineList, new Comparator<Routine>() {
            @Override
            public int compare(Routine r1, Routine r2) {
                return r1.getFrequency() - r2.getFrequency();
            }
        });

        // Getting the most routine at certain hour with most frequency.
        return routineList.get(0);
    }

    public List<Routine> getAllUserRoutine() {

        List<Routine> allRoutineList = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            Select selectQueryBuilder = Select.from(Routine.class)
                    .where(Condition.prop("hour").eq(i));
            List<Routine> routineList = selectQueryBuilder.list();
            Collections.sort(routineList, new Comparator<Routine>() {
                @Override
                public int compare(Routine r1, Routine r2) {
                    return r1.getFrequency() - r2.getFrequency();
                }
            });

            if (!routineList.isEmpty()) {
                allRoutineList.add(routineList.get(0)); // Including the one with most frequency.
            }

        }

        return allRoutineList;
    }

    public void insertRandomRoutine() {
        Routine routine = new Routine();
//        routine.setTime(new Date().getTime());
//        routine.setData("RandomData");
        routine.save();
    }

//    public Routine findUserRoutine() {
//        Select selectQueryBuilder = Select.from(Routine.class)
//                .where(Condition.prop("").gt(currentTime.getTime() - offset))
//                .where(Condition.prop("data").gt(currentTime.getTime() + offset));
//    }

    public List<Routine> getDefaultRoutine() {
        return Arrays.asList(new Routine(), new Routine(), new Routine());
    }
}
