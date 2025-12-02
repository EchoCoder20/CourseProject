package com.example.Course.services;

import com.example.Course.entities.Course;
import com.example.Course.repositories.CourseRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServices {
    @Autowired
    CourseRepositories courseRepositories;


    public List<Course> getAllCourses(){
        return courseRepositories.findAll();
    }
    public Course saveCourse(Course courses){
        courses.setCreatedData(new Date());
        courses.setActive(true);
        return courseRepositories.save(courses);

    }
    public Course updateCourse(Course course) throws Exception {
        Course checkIfIdExist=courseRepositories.findById(course.getSeq()).get();
        if(checkIfIdExist !=null){
            course.setUpdatedData(new Date());
            course.setCreatedData(checkIfIdExist.getCreatedData());
            course.setActive(checkIfIdExist.isActive());
            return courseRepositories.save(course);
        }else {
            throw new Exception("BAD REQUEST");
        }

    }
    public void deleteCourse(int id) throws Exception{
        Course checkIfIdExist=courseRepositories.findById(id).get();
        if(checkIfIdExist !=null && checkIfIdExist.isActive()){
            checkIfIdExist.setActive(false);
            checkIfIdExist.setUpdatedData(new Date());
            courseRepositories.save(checkIfIdExist);
        }else {
            throw new Exception("BAD REQUEST");
        }

    }
    public Course findById(String id) throws Exception{
        System.out.println(id);
        List<Course> checkIfIdExist=courseRepositories.findAll();
        if(checkIfIdExist !=null){
            for(Course course:checkIfIdExist){
                if(course.getCourseId().equals(id)) {
                    System.out.println(course);
                    return course;
                }
            }
        }else{
            throw new Exception("BAD REQUEST");
        }
        return null;
    }
}
