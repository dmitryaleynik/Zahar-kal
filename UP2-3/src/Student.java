public class Student implements Comparable<Student>{
    private Integer course;
    private Integer group;
    private String surname;
    public Student(int course, int group, String surname) {
        this.course = course;
        this.group = group;
        this.surname = surname;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Course: " + course +
                ", group: " + group +
                ", surname: " + surname+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (course != null ? !course.equals(student.course) : student.course != null) return false;
        if (group != null ? !group.equals(student.group) : student.group != null) return false;
        return surname != null ? surname.equals(student.surname) : student.surname == null;

    }

    @Override
    public int compareTo(Student o) {
        if (!o.group.equals(group)) return o.group.compareTo(group);
        if (!o.course.equals(course)) return o.course.compareTo(course);
        return o.surname.compareTo(surname);
    }
}
