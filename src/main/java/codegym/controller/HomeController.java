package codegym.controller;

import codegym.model.ClassRoom;
import codegym.model.Student;
import codegym.repository.IClassRoomRepo;
import codegym.service.IClassRoomService;
import codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    IStudentService iStudentService;

    @Autowired
    IClassRoomService iClassRoomService;

    @ModelAttribute("classRooms")
    public ArrayList<ClassRoom> classRoomArrayList() {
        return iClassRoomService.findAll();
    }

    @GetMapping("/home")
    public ModelAndView showHome(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("page", iStudentService.findAll(PageRequest.of(page, 3, Sort.by("name"))));
//        String URL = "http://localhost:8080/api/students";
//        RestTemplate restTemplate = new RestTemplate();
//        Page<Student> result = restTemplate.getForObject(URL,Page<Student>.class);
//        modelAndView.addObject("page",result);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView findName(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("page", iStudentService.findAllByName(name,PageRequest.of(0, 3, Sort.by("name"))));
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam MultipartFile fileUpload,@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }

        String nameFile = fileUpload.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileUpload.getBytes(), new File("/Users/johntoan98gmail.com/Desktop/project/Module 4/BT_OT/src/main/webapp/fileSatatic/img/" + nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        student.setAvatar("/i/" + nameFile);

        String URL = "http://localhost:8080/api/students";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Student> requestBody = new HttpEntity<>(student,headers);

//        iStudentService.save(student);

        String student1 = restTemplate.postForObject(URL,requestBody,String.class);

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }
}
