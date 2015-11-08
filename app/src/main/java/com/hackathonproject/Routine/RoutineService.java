package com.hackathonproject.Routine;

import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by james on 15-11-07.
 */
public class RoutineService {

    private static final String LOGGER = "RoutineServiceLog";

    public Routine getUserRoutine() {
        long offset = 300000; // 5 minutes in miliseconds.
        Date currentTime = new Date();

        Select selectQueryBuilder = Select.from(Routine.class)
                .where(Condition.prop("data").gt(currentTime.getTime() - offset))
                .where(Condition.prop("data").gt(currentTime.getTime() + offset));

        List<Routine> routineList = selectQueryBuilder.list();
        for (Routine routine: routineList) {
            Log.i(LOGGER, routine.toString());
        }

        return routineList.get(0);
    }

    public void insertRandomRoutine() {
        Routine routine = new Routine();
        routine.setTime(new Date().getTime());
//        routine.setData("RandomData");
        routine.save();
    }

    public void findUserRoutine() {
//        Select selectQueryBuilder = Select.from(Routine.class)
//                .where(Condition.prop("data").gt(currentTime.getTime() - offset))
//                .where(Condition.prop("data").gt(currentTime.getTime() + offset));
    }

    public List<Routine> getDefaultRoutine() {
        return Arrays.asList(new Routine(), new Routine(), new Routine());
    }
}
