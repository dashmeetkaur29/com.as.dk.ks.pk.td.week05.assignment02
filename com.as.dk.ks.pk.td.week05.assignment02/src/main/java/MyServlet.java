/* Date -12-02-2922
Author - Dashmeet Kaur
Description - Servlet to fetch records from json and process on webpage
*/ 

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONParser jsonP = new  JSONParser();
		
		//Getting username
		String uFname = request.getParameter("userFirstName");
		String uLname = request.getParameter("userLastName");
		
		FileReader read = new FileReader( ("C:\\Users\\Dell\\eclipse-workspace\\com.as.dk.ks.pk.td.week05.assignment02\\Assignment02_jsonFiles\\studentInfo.json"));
		Object obj = null;
		try {
			obj = jsonP.parse(read);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject  studJasonObj =(JSONObject) obj;
		
		//extracting file data
		String fName = (String) studJasonObj.get("firstname");
		String lName = (String) studJasonObj.get("lastname");
		String age = (String) studJasonObj.get("age");
		String phone = (String) studJasonObj.get("phNumber");
		
		//If username is null the default username from json will be displayed.
		if(!uFname.isEmpty() || !uLname.isEmpty())
		{
			fName = uFname;
			lName = uLname;
		}
		
		out.print("<h2 style='color :Brown; margin-top:30px; margin-left:10px;'>Welcome "+fName + "	" + lName +"</h2>");
		out.print("<h3 style='color :orange; margin-top:30px; margin-left:10px;'>Here are your details -  </h3>");
		out.print("<h4 style='color :orange;  margin-left:10px;'>Age : " + age + "<br>Phone Number : " + phone + "</h4>");
	
		
		//Fetching address array and displaying in a table
		out.print("<br><br> <p style='margin-left:10px; font-size:20px;margin-top:-15px;'>Your address are: </p><br><br>");
		out.print("<table style= 'background-color:pink; margin-left:10px; margin-top:-30px;'>"
				+ "<tr><th>  CITY  </th> <th>  STREET  </th>	<th>  STATE   </th></tr>");
		
		JSONArray addressArr = (JSONArray) studJasonObj.get("address");
		for(int i = 0;i<addressArr.size();i++)
		{
			JSONObject address = (JSONObject) addressArr.get(i);
			out.print("<tr><td>" + address.get("city") + "</td> <td>" + address.get("street") + "</td> <td> "+ address.get("state") + "</td></tr>");
		}
		out.print("</table> <br><br><br> ");
		
		
		//Fetching marks array and displaying in a table
		out.print("<br><br> <p style='margin-left:10px; font-size:20px; margin-top:-35px;'>Your Marks are: </p><br><br>");
		out.print("<table style= 'background-color:CornflowerBlue; margin-left:10px; margin-top:-30px;'>"
				+ "<tr><th>  SUBJECT  </th> <th>  MARKS  </th></tr>");
	
		
		JSONArray marksArr = (JSONArray) studJasonObj.get("marks");
		for(int i = 0;i<marksArr.size();i++)
		{
			JSONObject marks = (JSONObject) marksArr.get(i);
			out.print("<tr><td> " + marks.get("subjectName") +"</td> <td>" + marks.get("marks"));
		}
		out.print("</table> <br><br><br> ");
		
		
		//Getting attributes to calculate area
		Double base = (Double) studJasonObj.get("base");
		Double ht = (Double) studJasonObj.get("height");
		Double side = (Double) studJasonObj.get("side");
		
		Double arTriangle =  0.5 * base * ht;
		Double arRect =   side * side;
		
		out.print("<h3 style='color :brown; margin-top:30px; margin-left:10px;'>Area of triangle is : "+ arTriangle + "</h3>");
		out.print("<h3 style='color :brown; margin-top:30px; margin-left:10px;'>Area of rectangle is : "+ arRect + "</h3>");
		
		
	}

}
