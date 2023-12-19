/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.webstudent;

/**
 *
 * @author tanka
 */
public class studentMark {
    
    private int id;
    private int stud_Id;
    private String title;
    private String makr_let;
    private int makr;

    public studentMark(int id, int stud_Id,String title, String makr_let,int makr) {
        this.id = id;
        this.stud_Id = stud_Id;
        this.title = title;
        this.makr_let = makr_let;
        this.makr = makr;
    }

    // Геттери і сеттери тут

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return stud_Id;
    }

    public String getTitle() {
        return title;
    }

    public int getMakr() {
        return makr;
    }

    public String getMakr_let() {
        return makr_let;
    }
}

