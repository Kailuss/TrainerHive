package oton.trainerhive.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kailuss
 */
public class Workout {

    private int Id;
    private String ForDate;
    Date WorkoutDate = new Date();
    private String Comments;
    private int UserId;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public String getForDateFormatted() {
	SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatOut = new SimpleDateFormat("EEEE',' dd MMM yyyy", new Locale("es", "ES"));

	try {
	    WorkoutDate = formatIn.parse(this.ForDate);
	} catch (ParseException ex) {
	    Logger.getLogger(Workout.class.getName()).log(Level.SEVERE, null, ex);
	}
	return formatOut.format(WorkoutDate);
    }
    
    public String getForDate() {
	return ForDate;
    }

    public void setForDate(String ForDate) {
	this.ForDate = ForDate;
    }

    public String getComments() {
	return Comments;
    }

    public void setComments(String Comments) {
	this.Comments = Comments;
    }

    public int getUserId() {
	return UserId;
    }

    public void setUserId(int UserId) {
	this.UserId = UserId;
    }
}
