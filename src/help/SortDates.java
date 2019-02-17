package help;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortDates {

    public static void main(String[] args) {
        String string;
        LocalDate date;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Scanner keyboard = new Scanner(System.in);
        List<LocalDate> dates = new ArrayList<>();

        for (int i = 1; i < 6 ; i++) {
            System.out.print("Enter date "+ i + " in (MM/dd/yyy): ");
            string = keyboard.nextLine();
            date = LocalDate.parse(string, f);
            dates.add(date);
        }

        Collections.sort(dates);

        for (LocalDate d: dates ) {
            System.out.println(d);
        }

    }
}
