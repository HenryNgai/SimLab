package com.SimLab.controller;

import com.SimLab.model.dao.Course;
import com.SimLab.model.dao.Repository.UserCourseAssociationRepository;
import com.SimLab.model.dao.User;
import com.SimLab.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SimLabController {

    @Autowired
    private UserCourseAssociationRepository userCourseAssociationRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }


    @RequestMapping(value="/student/index", method = RequestMethod.GET)
    public ModelAndView studentHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("Course", new Course());
        modelAndView.addObject("Email", user.getEmail());
        modelAndView.addObject("Name", user.getName());
        modelAndView.setViewName("/student/index");
        List<User> instructors = userService.findAllInstructors();
        for(User u: instructors){
            System.out.println(u.getName());
        }
        return modelAndView;
    }


    @RequestMapping(value="/instructor/index", method = RequestMethod.GET)
    public ModelAndView instructorHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("Email", user.getEmail());
        modelAndView.addObject("UserId", user.getId());
        modelAndView.addObject("Name", user.getName());
        modelAndView.setViewName("/instructor/index");
        return modelAndView;
    }



    @RequestMapping(value = "/MakeCourse", method = RequestMethod.POST)
    public ModelAndView createNewCourse(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }



    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @ResponseBody
    @GetMapping("/loadCourses")
    public List<String> loadCourse(@RequestParam String userid ){
        List<Course> userCourses = userCourseAssociationRepository.loadUserCourses(Integer.parseInt(userid));
        List<String> userCoursesName = new ArrayList<String>();
        for (int x = 0; x< userCourses.size(); x++){
            userCoursesName.add(userCourses.get(x).getCourseName());
        }
        return userCoursesName;
    }


}
