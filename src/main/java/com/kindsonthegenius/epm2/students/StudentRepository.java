package com.kindsonthegenius.epm2.students;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Profile("students")
public interface StudentRepository extends JpaRepository<Student, Integer> {


}
