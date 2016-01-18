package introsde.beemindergoals.soap;
import introsde.beemindergoals.model.Goal;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Goals {

    @WebMethod(operationName="getGoals")
    @WebResult(name="goal")
	public ArrayList<Goal> getGoals(@WebParam(name="access_token") String access_token);

	@WebMethod(operationName = "createGoal")
	@WebResult(name = "result")
	public String createGoal(
			@WebParam(name = "access_token") String access_token,
			@WebParam(name = "slug") String slug,
			@WebParam(name = "title") String title,
			@WebParam(name = "goal_type") String goal_type,
			@WebParam(name = "goaldate") String goaldate,
			@WebParam(name = "rate") String rate,
			@WebParam(name = "goalval") String goalval,
			@WebParam(name = "initval") String initval);

	@WebMethod(operationName = "createDatapoint")
	@WebResult(name = "result")
	public String createDatapoint(
			@WebParam(name = "access_token") String access_token,
			@WebParam(name = "slug") String slug,
			@WebParam(name = "timestamp") Long timestamp,
			@WebParam(name = "value") Long value);

}