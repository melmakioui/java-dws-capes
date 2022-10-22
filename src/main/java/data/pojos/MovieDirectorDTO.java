package data.pojos;

public class MovieDirectorDTO {

    private int id;
    private String title;
    private int year;
    private String genre;
    private int duration;
    private String director;
    private int age;

    public MovieDirectorDTO(int id, String title, int year, String genre, int duration, String director, int age) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Movie: " + "id: " + id + " title: " + title + " year: " + year + " genre " + genre + " duration: " + duration +  " age: " + age + "\n";
    }
}
