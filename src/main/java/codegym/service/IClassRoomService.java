package codegym.service;

import codegym.model.ClassRoom;
import codegym.model.Student;

import java.util.ArrayList;

public interface IClassRoomService {
    public ClassRoom findById(long id);

    public ArrayList<ClassRoom> findAll();

}
