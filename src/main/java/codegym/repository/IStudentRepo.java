package codegym.repository;

import codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface IStudentRepo extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAll(Pageable pageable);
    Page<Student> findAllByNameContaining(String name,Pageable pageable);
}
