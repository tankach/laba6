package sumdu.edu.ua.webstudent;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String group;
    private String faculty;
   /* private List<studentMark> mark;*/

    public Student(int id,String name, String surname, String email, String group, String faculty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.group = group;
        this.faculty = faculty;
    }

    // Геттери і сеттери тут

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getFaculty() {
        return faculty;
    }
    
  
}
