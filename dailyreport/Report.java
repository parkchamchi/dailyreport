package dailyreport;

import dailyreport.Hours;
import dailyreport.Save;

public class Report {
	private String tasks[];

	public Report() {
		tasks = new String[Hours.count];
	}

	public void setTask(Hours hour, String taskOfHour) {
		tasks[hour.ordinal()] = taskOfHour;
		Save.WriteOnFile(this);
	}

	public String getTask(Hours hour) {
		String taskOfHour = tasks[hour.ordinal()];

		if (taskOfHour == null)
			return "";
		else
			return taskOfHour;
	}
}