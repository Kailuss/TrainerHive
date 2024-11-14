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
public class Entrenamiento {

    private int Id;
    private String ForDate;
    Date d = new Date();
    private String Comments;
    private int UserId;

    public int getId() {
	return Id;
    }

    public void setId(int Id) {
	this.Id = Id;
    }

    public String getForDateFormatted() {
	SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfOut = new SimpleDateFormat("EEEE',' dd MMM yyyy", new Locale("es", "ES"));

	try {
	    d = sdfIn.parse(this.ForDate);
	} catch (ParseException ex) {
	    Logger.getLogger(Entrenamiento.class.getName()).log(Level.SEVERE, null, ex);
	}
	return sdfOut.format(d);
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
