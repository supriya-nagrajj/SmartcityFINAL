import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Visit class to represent a visit/place
class Visit {
    int id;
    String name;
    String description;
    String imgUrl;
    String locationUrl;
    double ratings;

    public Visit(int id, String name, String description, String imgUrl, String locationUrl, double ratings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.locationUrl = locationUrl;
        this.ratings = ratings;
    }
}

@WebServlet("/visit")
public class VisitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ArrayList<Visit> visits = new ArrayList<>();

        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/visit"; // Replace with your actual DB URL
        String dbUser = "root"; // Your DB username
        String dbPassword = "diya123"; // Your DB password

        try {
         
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String query = "select * from visit_table"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Visit visit = new Visit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("img_url"),
                        rs.getString("location_url"),
                        rs.getDouble("ratings")
                );
                visits.add(visit);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Visit</title>");
        out.println("<script src='https://cdn.tailwindcss.com'></script>");
        out.println("</head>");
        out.println("<body class='font-poppins text-sm text-gray-800 bg-blue-50 m-0 p-0'>");

        // Navigation bar
        out.println("<nav class=\"flex justify-between items-center bg-[#ded7be] text-[#46a5b5] fixed top-0 w-full z-50 shadow-lg\">");
        out.println("<img src=\"\\images\\logo+skyline.png\" alt=\"Mumbai Safar Logo\" id=\"logo\" class=\"h-20 p-0 m-0\" />");
        out.println("<div class=\"flex space-x-4 mr-8\">");
        out.println("<a href=\"index.html\" class=\"text-[#328c9c] text-lg font-bold px-3 py-2 rounded transition hover:bg-[#d8cfb0]\">Home</a>");
        out.println("<a href=\"visit.html\" class=\"text-[#328c9c] text-lg font-bold px-3 py-2 rounded transition hover:bg-[#d8cfb0]\">Visit</a>");
        out.println("<a href=\"food.html\" class=\"text-[#328c9c] text-lg font-bold px-3 py-2 rounded transition hover:bg-[#d8cfb0]\">Food</a>");
        out.println("<a href=\"bookings.html\" class=\"text-[#328c9c] text-lg font-bold px-3 py-2 rounded transition hover:bg-[#d8cfb0]\">Bookings</a>");
        out.println("<a href=\"events\" class=\"text-[#328c9c] text-lg font-bold px-3 py-2 rounded transition hover:bg-[#d8cfb0]\">Events</a>");
        
        out.println("</div>");
        out.println("</nav>");

        // Main content
        out.println("<main class='pt-24 px-6'>");
        out.println("<h1 class='text-4xl font-bold text-center text-gray-800 mb-8'>Places to Visit in Mumbai</h1>");
        out.println("<div class='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8'>");

        // Loop through the visits list and generate cards
        for (Visit visit : visits) {
            String shortDescription = visit.description.length() > 100
            ? visit.description.substring(0, 100) + "..."
            : visit.description;
            out.println("<div class='bg-blue-50 rounded-md m-4 mt-5 flex-[0_1_calc(33.333%-30px)] max-w-[calc(33.333%-30px)] w-[250px] sm:flex-[0_1_calc(30%-20px)] sm:max-w-[calc(30%-20px)] md:flex-[0_1_calc(30%-20px)] md:max-w-[calc(30%-20px)] transition-transform duration-300 hover:scale-105 relative overflow-hidden'>");
            out.println("<div class = 'relative w-full h-64' >");
            out.println("<img src='" + visit.imgUrl + "' alt='" + visit.name + "' class='w-full h-[250px] object-cover rounded-t-md brightness-110'>");
            out.println("<div class ='absolute top-2.5 right-2.5 w-12 h-12 flex items-center justify-center rounded-full cursor-pointer transition-transform duration-300 hover:scale-110 hover:bg-[#ffd700]'>");
            out.println("<img src='images/bookmark.svg' alt='bookmark' class='w-6 h-6'>");
            out.println("</div>");
            out.println("<a href='" + visit.locationUrl + "' class='absolute bottom-2.5 left-2.5 w-10 h-10 flex items-center justify-center rounded-full shadow-lg cursor-pointer transition-transform duration-300 hover:scale-110 hover:bg-[#ffd700]'>");
            out.println("<img src='images/location.svg' alt='Location Icon' class='w-6 h-6'>");
            out.println("</a>");
            out.println("</div>");
            out.println("<div class='p-2 space-y-2'>");
            out.println("<h3 class='text-lg font-bold text-gray-800'>" + visit.name + "</h3>");
            out.println("<details class='group'>");
            out.println("<summary class='text-base cursor-pointer text-gray-600 list-none'>");
            out.println("<span class='line-clamp-3'>"+shortDescription+"</span>");
            out.println("<span class='text-blue-500 ml-1 font-semibold cursor-pointer group-open:hidden'>Read More</span>");
            out.println("</summary>");
            out.println("<p class='text-base text-gray-600 mt-2'>"+visit.description+ "</p>");
            out.println("</details>");
            out.println("<div class='flex items-center space-x-2'>");
            out.println("<div class='w-24 h-4 bg-gray-300 rounded-full'>");
            out.println("<div class='h-full bg-yellow-400 rounded-full' style='width: " + (visit.ratings * 20) + "%'></div>");
            out.println("</div>");
            out.println("<span class='text-sm font-bold text-gray-700'>"+visit.ratings+"</span>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
        }

        out.println("</div>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }
}