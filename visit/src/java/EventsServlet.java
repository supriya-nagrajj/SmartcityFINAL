
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

// Event class to represent an event
class Event {

    int id;
    String title;
    String description;
    String imageUrl;
    String url;

    public Event(int id, String title, String description, String imageUrl, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.url = url;
    }
}

@WebServlet("/events")
public class EventsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ArrayList<Event> events = new ArrayList<>();

        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/test";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
//            

            // Query to fetch all events
            String query = "SELECT * FROM events";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Store each event in the events list
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("url")
                );
                events.add(event);
            }

            conn.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("Number of events fetched: " + events.size());

        // Generate HTML content for the events page
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Events in Mumbai</title>");
        out.println("<script src='https://cdn.tailwindcss.com'></script>");
        out.println("</head>");
        out.println("<body class='bg-gray-100'>");

        out.println("<nav class=\"flex justify-between items-center bg-[#fffee2] text-[#38808d] fixed top-0 w-full z-50 shadow-lg\">");
        out.println("    <img src=\"images/logo_skyline_brightened.jpg\" alt=\"Mumbai Safar Logo\" id=\"logo\" class=\"h-16 sm:h-20 p-0 m-0\" />");
        out.println("    <div class=\"flex space-x-2 sm:space-x-4 mr-4 sm:mr-8\">");
        out.println("      <a href=\"index.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Home</a>");
        out.println("      <a href=\"visit\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Visit</a>");
        out.println("      <a href=\"food.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Food</a>");
        out.println("      <div class=\"relative inline-flex text-left group\">");
        out.println("        <a href=\"#\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Bookings</a>");
        out.println("        <div class=\"absolute left-18 mt-[40px] w-32 sm:w-40 rounded-lg shadow-lg bg-white opacity group-hover:opacity-100 invisible group-hover:visible transition-opacity duration-500\">");
        out.println("          <ul class=\"bg-[#fff9e4] border-none rounded-lg\">");
        out.println("            <li><a href=\"HotelsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\">Hotels</a></li>");
        out.println("            <li><a href=\"TrainsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\">Trains</a></li>");
        out.println("            <li><a href=\"BusServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\">Bus</a></li>");
        out.println("            <li><a href=\"CabsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\">Cabs</a></li>");
        out.println("          </ul>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("      <a href=\"events\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Events</a>");
        out.println("    </div>");
        out.println("  </nav>");

        out.println("<main class='pt-24 px-6'>");
        out.println("<h1 class='text-4xl font-bold text-center text-gray-800 mb-8'>Latest Events in Mumbai</h1>");
        out.println("<div class='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8'>");

        // Loop through the events list and generate cards
        for (Event event : events) {
            out.println("<div class='bg-white rounded-lg shadow-md overflow-hidden transform transition hover:scale-105'>");
            out.println("<img src='" + event.imageUrl + "' alt='" + event.title + "' class='w-full h-48 object-cover'>");
            out.println("<div class='p-4'>");
            out.println("<h5 class='text-lg font-semibold text-gray-800'>" + event.title + "</h5>");
            out.println("<p class='text-gray-600 mt-2'>" + event.description + "</p>");
            out.println("<a href='" + event.url + "' class='mt-4 inline-block bg-blue-500 text-white font-medium py-2 px-4 rounded hover:bg-blue-600 transition' target='_blank'>View Event</a>");
            out.println("</div>");
            out.println("</div>");
        }

        out.println("</div>");
        out.println("</main>");
        out.println("<footer class=\"bg-gray-800 text-white py-8\">");
        out.println("<div class=\"max-w-7xl mx-auto px-6 flex flex-col md:flex-row justify-between items-center\">");
        out.println("<p class=\"text-sm\">&copy; 2025 Smart City - Mumbai Safar</p>");
        out.println("<div class=\"flex space-x-4 mt-4 md:mt-0\">");
        out.println("<!-- <a href=\"#\" class=\"text-blue-400 hover:text-blue-500\">Facebook</a> -->");
        out.println("</div>");
        out.println("</div>");
        out.println("</footer>");
        out.println("</body>");
        out.println("</html>");
    }
}
