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

class Cabs {
    int id;
    String places;
    String img_url;
    String link;

    public Cabs(int id, String places, String img_url, String link) {
        this.id = id;
        this.places = places;
        this.img_url = img_url;
        this.link = link;
    }
}

@WebServlet("/CabsServlet")
public class CabsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
           ArrayList<Cabs> cab = new ArrayList<>();
           
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
            String query = "SELECT * FROM cabs";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            
              // Store each event in the hotels list
            while (rs.next()) {
                Cabs cabs = new Cabs(
                        rs.getInt("id"),
                        rs.getString("places"),
                        rs.getString("img_url"),
                        rs.getString("link")
                );
                cab.add(cabs);
            }

            conn.close();
        } 
        catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
//                e.printStackTrace();
        } 
         
        System.out.println("Connected to the database. Number of cabs: " + cab.size());

        // Generate HTML content for the hotels page
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");            
            out.println(" <script src='https://cdn.tailwindcss.com'></script>");
            out.println("</head>");
            
            //body
            out.println("<body class=\"overflow-y-scroll overflow-x-hidden \">");
            
            //Bg-image
            out.println("<div class=\"fixed top-0 left-0 w-full h-full overflow-hidden\">");
            out.println("<img src=\"images\\background.avif\" alt=\"Background-image\" class=\"w-full h-full object-cover absolute top-0 left-0 filter blur-sm\">");
            out.println("</div>");
            
            
            //navbar
            out.println("<nav class=\"flex justify-between items-center bg-[#fffee2] text-[#38808d] fixed top-0 w-full z-50 shadow-lg\">");
            out.println("<img src=\"images\\logo_skyline_brightened.jpg\" alt=\"Mumbai Safar Logo\" id=\"logo\" class=\"h-16 sm:h-20 p-0 m-0\" />");
            out.println("<div class=\"flex space-x-2 sm:space-x-4 mr-4 sm:mr-8\">");
            out.println("<a href=\"index.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Home</a>");  
            out.println("<a href=\"visit.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Visit</a>");
            out.println("<a href=\"foods.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Food</a>"); 
            
            out.println(" <div class=\"relative inline-flex text-left group\">");
            out.println("<a href=\"#\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Bookings<a>");  
            out.println("<div class=\"absolute left-18 mt-[40px] w-32 sm:w-40 rounded-lg shadow-lg bg-white opacity group-hover:opacity-100 invisible group-hover:visible transition-opacity duration-500\">");
            
            out.println("<ul class=\"bg-[#fff9e4] border-none rounded-lg\">");  
            out.println("<li>");
            out.println("<a href=\"HotelsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\">Hotels</a>");  
            out.println("</li>");
            
            out.println("<li>");  
            out.println("<a href=\"TrainsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\"> Trains </a>");
            out.println("</li>"); 
            
            out.println("<li>");
            out.println("<a href=\"BusServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\"> Bus </a>");
            out.println("</li>");
            
            out.println("<li>");
            out.println("<a href=\"CabsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\"> Cabs </a>");
            out.println("</li>");
            
            out.println(" </ul>");
            out.println("</div>");  
            out.println("</div>");
            
            out.println("<a href=\"events.html\"  class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Events</a>");
            out.println("</div>");  
            out.println("</nav>");  
            
            out.println("<h2 class=\"text-xl sm:text-2xl md:text-3xl font-bold text-center text-[#328c9c] relative z-10 mt-20 sm:mt-32 mx-4 sm:mx-12 bg-[#fff9e3] rounded-2xl\">Cabs</h2>");
            
            out.println("<div class=\"grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 sm:gap-8 p-4 sm:p-8 mt-10\">");
            
            for(Cabs cabs : cab){ 
                out.println("<div class=\"bg-gradient-to-r from-teal-700 to-white backdrop-blur-sm border-2 border-black h-[380px] w-[370px] p-5 rounded-2xl relative  ml-5\">");  
                out.println("<img src='" + cabs.img_url + "' class=\"h-[200px] w-[250px] top-[50px] left-[55px] absolute rounded-lg hover:scale-110 transition-all\">");
                out.println(" <h3 class=\"relative top-[250px] left-2 text-lg font-bold text-center w-[300px] h-[30px] overflow-hidden text-ellipsis whitespace-nowrap\"> " + cabs.places + "</h3>");
                out.println("<button class=\"relative top-[260px] left-[90px] p-2 m-2 ml-5 rounded-lg bg-slate-500 hover:bg-gradient-to-r  from-orange-300  to-pink-500 text-white transition-all duration-400\"><a href= '" + cabs.link + "' target=\"_blank\">Book Now</a></button>");
                out.println("</div> ");  
                
            }   
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
    }
}


