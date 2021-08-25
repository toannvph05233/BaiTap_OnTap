package codegym.controller;

import codegym.model.ClassRoom;
import codegym.model.Student;
import codegym.repository.IClassRoomRepo;
import codegym.service.IClassRoomService;
import codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
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
        iStudentService.save(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }
}
