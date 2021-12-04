package tech.homebody.homebodyuser.repository.custom;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;
import tech.homebody.homebodyuser.dto.ExerciseHistoryDto;
import tech.homebody.homebodyuser.dto.ExerciseStatDto;
import tech.homebody.homebodyuser.entity.ExerciseHistory;
import tech.homebody.homebodyuser.entity.QExerciseHistory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomExerciseHistoryRepositoryImpl implements CustomExerciseHistoryRepository {
    private final JPAQueryFactory queryFactory;

    public CustomExerciseHistoryRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }


    @Override
    public List<ExerciseHistory> findThisMonthExerciseHistoryByUserId(String userId) {
        QExerciseHistory exerciseHistory = QExerciseHistory.exerciseHistory;
        DateFormat format = new SimpleDateFormat("yyyy/MM");
        String thisMonth = format.format(Calendar.getInstance().getTime());
        //2021/07.eq(thisMonth)
        List<ExerciseHistory> exerciseHistoryList;
        exerciseHistoryList =
                queryFactory.selectFrom(exerciseHistory).where(exerciseHistory.userId.eq(userId),exerciseHistory.startTime.substring(0,7).eq(thisMonth)).fetch();
        return exerciseHistoryList;
    }

    @Override
    public List<ExerciseHistory> findExerciseHistoryByUserIdAndPeriod(String userId, LocalDate start_period, LocalDate end_period) {
        QExerciseHistory exerciseHistory = QExerciseHistory.exerciseHistory;
//        List<ExerciseStatDto> exerciseStatDtoList;
//        StringTemplate formattedStartTime = Expressions.stringTemplate(
//                "DATE_FORMAT(STR_TO_DATE({0},{1}), {1})"
//                , exerciseHistory.startTime
//                , ConstantImpl.create("%Y-%m-%d"));
//        StringTemplate formattedEndTime = Expressions.stringTemplate(
//                "DATE_FORMAT(STR_TO_DATE({0},{1}), {1})"
//                , exerciseHistory.endTime
//                , ConstantImpl.create("%Y-%m-%d"));
//
//        exerciseStatDtoList = queryFactory.select(Projections.constructor(ExerciseStatDto.class,
//                exerciseHistory.idx, exerciseHistory.userId, exerciseHistory.type, exerciseHistory.dayOfRoutine,
//                exerciseHistory.records, exerciseHistory.level, formattedStartTime.as("startTime"),formattedEndTime.as("endTime")))
//                .from(exerciseHistory)
//                .fetch();
        List<ExerciseHistory> exerciseHistories;
        exerciseHistories = queryFactory.selectFrom(exerciseHistory).where(exerciseHistory.userId.eq(userId)).fetch();
        List<ExerciseHistory> exerciseHistoryList = new ArrayList<>();
        exerciseHistories.forEach(exerciseHistory1 -> {
            LocalDate compareDate = LocalDate.parse(exerciseHistory1.getStartTime().substring(0,10), DateTimeFormatter.ISO_DATE);

            if (compareDate.isEqual(start_period) || compareDate.isEqual(end_period) || (compareDate.isBefore(end_period) && compareDate.isAfter(start_period))){
                exerciseHistoryList.add(exerciseHistory1);
            }
        });

        return exerciseHistoryList;
    }
}
