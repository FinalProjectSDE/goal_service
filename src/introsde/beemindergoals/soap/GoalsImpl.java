package introsde.beemindergoals.soap;
import introsde.beemindergoals.model.Goal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebService(endpointInterface = "introsde.beemindergoals.soap.Goals",
	serviceName="GoalsService")
public class GoalsImpl implements Goals {
	
	@Override
	public ArrayList<Goal> getGoals(String access_token) {
		ArrayList<Goal> gl = new ArrayList<Goal>();

		JSONArray result = send_http_get_request("https://www.beeminder.com/api/v1/users/me/goals.json?access_token="
				+ access_token);

		final int n = result.length();
		for (int i = 0; i < n; ++i) {
			final JSONObject goal = result.getJSONObject(i);
			if(goal.has("slug")) System.out.println(goal.getString("slug"));
			if(goal.has("title")) System.out.println(goal.getString("title"));
			if(goal.has("goal_type")) System.out.println(goal.getString("goal_type"));
			if(goal.has("graph_url")) System.out.println(goal.getString("graph_url"));
			if(goal.has("panic")) System.out.println(goal.getLong("panic"));
			if(goal.has("losedate")) System.out.println(goal.getLong("losedate"));
			if(goal.has("goaldate")) System.out.println(goal.getLong("goaldate"));
			if(goal.has("goalval")) System.out.println(goal.getLong("goalval"));
			//if(goal.has("rate")) System.out.println(goal.getLong("rate"));
			if(goal.has("updated_at")) System.out.println(goal.getLong("updated_at"));
			System.out.println("==============================================================");
			Goal g = new Goal();
			g.setSlug(goal.getString("slug"));
			g.setTitle(goal.getString("title"));
			g.setGoal_type(goal.getString("goal_type"));
			g.setGraph_url(goal.getString("graph_url"));
			g.setPanic(goal.getLong("panic"));
			g.setLosedate(goal.getLong("losedate"));
			g.setGoaldate(goal.getLong("goaldate"));
			//g.setRate(goal.getLong("rate"));
			g.setUpdated_at(goal.getLong("updated_at"));
			gl.add(g);
		}

		System.out.println(result);
		return gl;
	}

	@Override
	public String createGoal(String access_token, String slug, String title,
			String goal_type, String goaldate, String rate, String goalval, String initval) {

		title = title.replaceAll(" ", "+");
		slug = slug.replaceAll(" ", "+");
		System.out.println(slug + " " + title + " " + goal_type + " "
				+ goaldate + " " + rate + " " + goalval + " " + access_token
				+ " " + initval);

		String result = send_http_post_request("https://www.beeminder.com/api/v1/users/me/goals.json?access_token="
				+ access_token + "&slug=" + slug + "&title=" + title + "&goal_type=" + goal_type + "&goaldate="+ goaldate
				+ "&goalval=" + goalval + "&rate=" + rate + "&initval=" + initval);

		return result;
	}
	
	@Override
	public String createDatapoint(String access_token, String slug, Long timestamp, Long value) {

		slug = slug.replaceAll(" ", "+");

		String result = send_http_post_request("https://www.beeminder.com/api/v1/users/me/goals/"+slug+"/datapoints.json?access_token="
				+ access_token + "&timestamp=" + timestamp + "&value=" + value);
		return result;
	}

	private static JSONArray send_http_get_request(String url) {
		URL obj;
		JSONArray d = null;
		try {
			obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			con.connect();
			InputStream is = null;
			try {
				is = con.getInputStream();
			} catch (Exception e) {
				is = con.getErrorStream();
			}

			if (is != null)
				d = parseJSON(is);
			con.disconnect();

			int responseCode = con.getResponseCode();
			String message = con.getResponseMessage();

			System.out.println("=> Result: " + message);
			System.out.println("=> HTTP Status: " + responseCode);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return d;
	}

	private static String send_http_post_request(String url) {
		URL obj;
		String message = null;
		try {
			obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");

			con.setDoOutput(false);

			con.connect();
			InputStream is = null;
			try {
				is = con.getInputStream();
			} catch (Exception e) {
				is = con.getErrorStream();
			}

			// con.disconnect();

			int responseCode = con.getResponseCode();
			message = con.getResponseMessage();

			System.out.println("=> Result: " + message);
			System.out.println("=> HTTP Status: " + responseCode);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return message;
	}

	private static JSONArray parseJSON(InputStream stream) throws IOException,
			JSONException {
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				stream, "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null)
			responseStrBuilder.append(inputStr);

		return new JSONArray(responseStrBuilder.toString());
	}

}
