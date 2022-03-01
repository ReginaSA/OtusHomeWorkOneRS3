package helpers;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Course {
    String name;
    String link;
    String rawDate;
    LocalDate timeStartBegin;

    public LocalDate getTimeStartBegin() {
        return timeStartBegin;
    }

    public void setTimeStartBegin(LocalDate timeStartBegin) {
        this.timeStartBegin = timeStartBegin;
    }

    public Course(String name, String link, String rawDate){
        this.name = name;
        this.link = link;
        this.rawDate = rawDate;
        this.timeStartBegin = this.rawDateToTime(rawDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRawDate() {
        return rawDate;
    }

    public void setRawDate(String rawDate) {
        this.rawDate = rawDate;
    }

    private LocalDate convertDateToFormat(String rawDate) {

        int year = 2022;
        String newRawDate = rawDate.replace("С ", "");
        Locale russian = new Locale("ru");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM", russian);
        LocalDate localDate = MonthDay.parse(newRawDate, formatter).atYear(year);

        return localDate;
    }

    private LocalDate rawDateToTime(String rawDate) {
        LocalDate timeStartBegin = convertDateToFormat(rawDate);

        return timeStartBegin;
    }
}
