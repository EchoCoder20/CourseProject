package com.example.Course.controller;

import com.example.Course.entities.Course;
import com.example.Course.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseServices courseServices;

    @GetMapping("/")
    public String getAllCourses(Model model){
        List<Course> coursesList=courseServices.getAllCourses();
        model.addAttribute("courses", coursesList); // for form binding
        return "index";
    }
    @GetMapping("/add-course")
    public String addingPage(Model model){
        model.addAttribute("Course", new Course()); // for form binding
        return "home";
    }
    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes){
        course.setCreatedData(new Date());
        course.setActive(true);
        courseServices.saveCourse(course);
        redirectAttributes.addFlashAttribute("message","Course Added Successfully");
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateCourse(Model model,@PathVariable int id) throws Exception {
        List<Course> coursesList=courseServices.getAllCourses();
        for(Course course:coursesList){
            if(course.getSeq()==id){
                model.addAttribute("Course", course);
                return "update";
            }
        }
        throw new Exception("BAD REQUEST");
    }
    @PostMapping("/updateCourse")
    public String updateCourseId(RedirectAttributes redirectAttributes ,@ModelAttribute Course course) throws Exception {
        courseServices.updateCourse(course);
        redirectAttributes.addFlashAttribute("message","Course Updated Successfully");
        return "redirect:/";

    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(RedirectAttributes redirectAttributes ,@PathVariable int id) throws Exception {
        courseServices.deleteCourse(id);
        redirectAttributes.addFlashAttribute("message","Course UnActive Successfully");
        return "redirect:/";
    }
    @GetMapping("/find-course")
    public String findCourse(Model model ,@RequestParam String id) throws Exception {
        System.out.println(id);
        Course foundedCourse=courseServices.findById(id);
        model.addAttribute("courses",foundedCourse);
        return "result";
    }
}
