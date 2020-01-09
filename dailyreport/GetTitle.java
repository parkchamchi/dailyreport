package dailyreport;

import java.time.*;

public class GetTitle {
	static String get() {
		LocalDate date = LocalDate.now();

		int year = date.getYear();
		int month = date.getMonth().getValue();
		int day = date.getDayOfMonth();

		DayOfWeek dayOfWeek = date.getDayOfWeek();
		String dayOfWeekString;
		switch (dayOfWeek) {
		case MONDAY:
			dayOfWeekString = "월";
			break;
		case TUESDAY:
			dayOfWeekString = "화";
			break;
		case WEDNESDAY:
			dayOfWeekString = "수";
			break;
		case THURSDAY:
			dayOfWeekString = "목";
			break;
		case FRIDAY:
			dayOfWeekString = "금";
			break;
		case SATURDAY:
			dayOfWeekString = "토";
			break;
		case SUNDAY:
			dayOfWeekString = "일";
			break;
		default:
			dayOfWeekString = "E";
			break;
		}

		String title = "[" + year + "." + month + "." + day + "." + dayOfWeekString + "]";
		return title;
	}
}