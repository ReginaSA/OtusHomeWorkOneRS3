package helpers;

public class Course {
    String name, link, rawDate;
    long timeStart;
    static long timeStartBegin = 100000000;

    public Course(String name, String link, String rawDate) {
        this.name = name;
        this.link = link;
        this.rawDate = rawDate;
        this.timeStart = this.rawDateToTime(rawDate);
    }

    private long rawDateToTime(String rawDate) {
        timeStartBegin += 2000 + rawDate.length();

        return timeStartBegin;
    }
}
