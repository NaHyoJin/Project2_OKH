package studysrc;

import java.util.List;

import studysrc.CalendarDto;

public interface iCalendar {
	
	public List<CalendarDto> getCalendarList(String id, String yyyyMM);
	public boolean writecalendar(CalendarDto cal);
	public List<CalendarDto> getDayList(String id,String rdate);
	public CalendarDto getDay(int seq);
	public boolean deletecalendar(int seq);
	public boolean updatecalendar(String title, String content, String rdate, int seq);
}
