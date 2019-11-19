package com.SimLab.model.dao.Repository;

import com.SimLab.model.dao.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT x FROM Course x WHERE x.courseId=:courseId")
    Course findById(int courseId);

    @Query("SELECT x.courseId FROM Course x WHERE x.courseName = :courseName")
    public int getCourseId(@Param("courseName") String courseName);
}
