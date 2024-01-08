package com.kindsonthegenius.epm2.courses;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Profile("courses")
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
