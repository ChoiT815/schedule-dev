package com.example.scheduledev.schedule.repository;

import com.example.scheduledev.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository <Schedule, Long> {

}
