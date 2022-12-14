package domain;

public class Director {

    private int id;
    private String name;
    private int age;

    public Director() {

    }

    public Director(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Director(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Director: " + "id: " + id + " " + "name: " + name + " " + "age: " + age + "\n";
    }
}
