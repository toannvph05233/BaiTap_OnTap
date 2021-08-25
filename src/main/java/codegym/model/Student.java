package codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 10,max = 20,message = "min 10 max 20")
    private String name;

    @Pattern(regexp = "^[\\w]{10,}$", message = "min 10")
    private String address;
    private Date birthDay;
    private String avatar;
    private String email;

    @Pattern(regexp = "^0[0-9]{9}$",message = "vd: 0123456789")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "class_Id")
    ClassRoom classRoom;

    public Student() {
    }

    public Student(String name, String address, Date birthDay, String email, String phone, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDay = birthDay;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.classRoom = classRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
