package codegym.service;

import codegym.model.Student;
import codegym.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepo iStudentRepo;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return iStudentRepo.findAll(pageable);
    }

    @Override
    public Student findById(long id) {
        return iStudentRepo.findById(id).get();
    }

    @Override
    public Student save(Student student) {
        return iStudentRepo.save(student);
    }

    @Override
    public void remove(Student student) {
         iStudentRepo.delete(student);
    }

    @Override
    public Page<Student> findAllByName(String name,Pageable pageable) {
        return iStudentRepo.findAllByNameContaining(name,pageable);
    }
}
