package codegym.controller;

import codegym.model.Student;
import codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.rmi.CORBA.Stub;

@RestController
@RequestMapping("/api/student")
public class HomeControllerAPI {
    @Autowired
    IStudentService iStudentService;

    @GetMapping("/{name}")
    public ResponseEntity<Page<Student>> findName(@PathVariable String name) {
        return new ResponseEntity<>(iStudentService.findAllByName(name, PageRequest.of(0, 3)), HttpStatus.OK);
    }
}
