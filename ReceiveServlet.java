import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Hotels {
    int id;
    String places;
    String img_url;
    String link;

    public Hotels(int id, String places, String img_url, String link) {
        this.id = id;
        this.places = places;
        this.img_url = img_url;
        this.link = link;
    }
}

@WebServlet("/hotels")
public class ReceiveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
           ArrayList<Hotels> hotel = new ArrayList<>();
           
                   // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/test";
        String dbUser = "root";
        String dbPassword = "root";
        
         try {
            // Load the MySQL driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);          

            // Query to fetch all events
            String query = "SELECT * FROM hotels";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            
              // Store each event in the hotels list
            while (rs.next()) {
                Hotels hotels = new Hotels(
                        rs.getInt("id"),
                        rs.getString("places"),
                        rs.getString("img_url"),
                        rs.getString("link")
                );
                hotel.add(hotels);
            }

            conn.close();
        } 
        catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
                e.printStackTrace();
        } 
         
        System.out.println("Connected to the database. Number of hotels: " + hotel.size());

        // Generate HTML content for the hotels page
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");            
            out.println(" <script src='https://cdn.tailwindcss.com'></script>");
            out.println("</head>");
            
            out.println("<body class=\'overflow-y-scroll overflow-x-hidden bg-gradient-to-r from-pink-400 to-blue-500\'>");
           
            out.println("<nav class=\'bg-gray-300  flex flex-wrap items-center px-2 text-base text-gray-700 space-x-40 space-y-reverse md:py-0 md:flex md:items-center md:justify-between h-20'>");
            out.println("<img src=\"\\images\\logo1.png\" alt=\"Mumbai Safar Logo\" class=\"w-36  -ml-15 rounded float-start cursor-pointer\">");
            out.println("<ul class=\"md:flex md:items-center\">");
            out.println(" <li>");  
            out.println(" <a href=\"newindex.html\" class=\"ml-20 px-2 py-2 bg-gradient-to-r from-purple-400 to-blue-100 hover:from-pink-400 hover:to-orange-300 rounded-3xl duration-300 font-['Poppins'] text-lg mr-5 hover:border-t-[20px] top-14  shadow-xl border-yellow-200\">Home</a>");
            out.println("</li>"); 
            out.println(" <div class=\"relative inline-block text-left group\">");
            out.println("<a href=\"\" class=\"ml-20 px-2 py-2 bg-gradient-to-r from-purple-400 to-blue-100 hover:from-pink-400 hover:to-orange-300 rounded-3xl duration-300 font-['Poppins'] text-lg mr-5 hover:border-t-[20px] top-14  shadow-xl border-yellow-200\">Bookings</a>");  
            out.println("<div class=\"absolute left-24 mt-0  w-40 rounded-lg shadow-lg bg-white opacity-0 group-hover:opacity-100 invisible group-hover:visible transition-opacity duration-1000\">");
            out.println("<ul class=\"bg-orange-100 overflow-visible z-10 absolute border-none mt-[5px] w-[150px] rounded-lg\">");  
            out.println("<li>");
            out.println("<a href=\"hotels.html\" class=\"block px-4 py-2 text-sm  hover:bg-gray-100  hover:text-gray-900\">Hotels</a>");  
            out.println("</li>");
            out.println("  <li>");  
            out.println("<a href=\"transportation.html\" class=\"block px-4 py-2 text-sm  hover:bg-gray-100  hover:text-gray-900\"> Transportation </a>");
            out.println(" </li>");  
            out.println(" </ul>");
            out.println("</div>");  
            out.println("</div>");
            out.println(" <li>");  
            out.println("<a href=\"visit.html\" class=\"ml-20 px-2 py-2 bg-gradient-to-r from-purple-400 to-blue-100 hover:from-pink-400 hover:to-orange-300 rounded-3xl duration-300 font-['Poppins'] text-lg mr-5 hover:border-t-[20px] top-14  shadow-xl border-yellow-200\">Visit</a>");
            out.println("</li>");  
            out.println(" <li>");
            out.println("<a href=\"food.html\" class=\"ml-20 px-2 py-2 bg-gradient-to-r  from-purple-400 to-blue-100 hover:from-pink-400 hover:to-orange-300 rounded-3xl duration-300 font-['Poppins'] text-lg mr-5 hover:border-t-[20px] top-14  shadow-xl border-yellow-200\">Foods</a>");  
            out.println("</li>");
            out.println("<li>");  
            out.println("<a href=\"events.html\" class=\"ml-20 px-2 py-2 bg-gradient-to-r from-purple-400 to-blue-100 hover:from-pink-400 hover:to-orange-300 rounded-3xl duration-300 font-['Poppins'] text-lg mr-5 hover:border-t-[20px] top-14  shadow-xl border-yellow-200\">Events</a>");
            out.println("</li>");  
            out.println("</ul>");
            out.println("</nav>");  
            
            
            for(Hotels hotels : hotel){
                out.println("<div class=\"relative z-50   p-5\">");
                out.println("<div class=\"bg-gradient-to-r from-yellow-500 to-white backdrop-blur-sm border-2 border-black h-[380px] w-[360px] p-5 mt-[60px] ml-7 rounded-2xl relative\">");  
                out.println(" <img src='" + hotels.img_url + "' class=\" h-[200px] w-[250px] top-[50px] left-[55px] absolute rounded-lg hover:scale-110 transition-all\">");
                out.println("<p class=\"relative top-[250px] right-[-100px] font-bold\"> '" + hotels.places + "'</p>");  
                out.println("<button class=\"relative top-[260px] right-[-100px] p-3 m-1 rounded-lg bg-slate-500 hover:bg-gradient-to-r from-orange-500 to-pink-500 text-white transition-all duration-400\"><a href= '" + hotels.link + "' target=\"_blank\">Book Now</a></button>");
                out.println("</div> ");  
                
            }   
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            
            
    }
}

