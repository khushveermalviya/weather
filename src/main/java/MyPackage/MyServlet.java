package com.example.weatherapplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// API SETUP
		String apiKey = "05ee713f70cf860d4a4ca173f4e95ac9";
		
		// get the city from the form input
		 String city = request.getParameter("city");
		 // Create the URL for the OPENWEATHERMAP API request
		 
//		 String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=New%20Delhi&appid=05ee713f70cf860d4a4ca173f4e95ac9";
		 //API Integration 
		 
		 city = city.replaceAll(" ", "%20"); // agar city name me space ho (e.g., New York)
		 String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=05ee713f70cf860d4a4ca173f4e95ac9";
		 URL url = new URL(apiUrl);
		 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		 connection.setRequestMethod("GET");
		 // reading the data from the network
		 InputStream inputStream = connection.getInputStream();
		 InputStreamReader reader = new InputStreamReader(inputStream);
		 //want to store data in string
		 
		 StringBuilder responseContent = new StringBuilder();
		 
		 // for taking  INPUT  FORM THE READER
		 Scanner scanner = new Scanner(reader);
		 
		 while (scanner.hasNext()) {
			 responseContent.append(scanner.nextLine());
			 }
		 scanner.close();
		// TYPECASTING = parsing the data into JSON 
		 
		 Gson gson = new Gson();
		 JsonObject jsonObject = gson.fromJson(responseContent.toString() , JsonObject.class);
		 System.out.println(jsonObject);
		 
		 long dateTimestamp = jsonObject.get("dt").getAsLong()* 1000;
		 @SuppressWarnings("deprecation")
		 String date = new Date(dateTimestamp).toLocaleString();
		 
		 // temp
		  double tempreatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		  int tempreatureCelsius = (int)(tempreatureKelvin - 273.15);
		  
		  //humidity
		  int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		  //wind speed
		  double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		  //weather condition
		  String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
		  String icon = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").getAsString();

		  //SET THE data as request attributes (for sending to the jsp page)
		  
		  request.setAttribute("date",date);
		  request.setAttribute("city",city);
		  request.setAttribute("tempreature",tempreatureCelsius);
		  request.setAttribute("weathercondition", weatherCondition);
		  request.setAttribute("humidity",humidity);
		  request.setAttribute("windspeed" , windSpeed);
		  request.setAttribute("weatherData", responseContent.toString());
		  request.setAttribute("icon", icon);
		  connection.disconnect();
		  // forward the request to the weather.jsp page for rendering
		  request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
