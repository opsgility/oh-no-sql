package com.sample.contosojobs.ContosoJobsApplication.Entity;
import java.util.Date;
//import org.joda.time.DateTime;

import microsoft.sql.DateTimeOffset;

public class Rating {

	
    public int NumberOfStars;


    public String Heading;


    public String Body;


   // public DateTime Date;
    public Date Date;

	public int getNumberOfStars() {
		return NumberOfStars;
	}


	public void setNumberOfStars(int numberOfStars) {
		NumberOfStars = numberOfStars;
	}


	public String getHeading() {
		return Heading;
	}


	public void setHeading(String heading) {
		Heading = heading;
	}


	public String getBody() {
		return Body;
	}


	public void setBody(String body) {
		Body = body;
	}


	public Date getDate() {
		return Date;
	}


	public void setDate(Date date) {
		Date = date;
	}


	@Override
	public String toString() {
		return "Rating [NumberOfStars=" + NumberOfStars + ", Heading=" + Heading + ", Body=" + Body + ", Date=" + Date
				+ "]";
	}
    
    
}
