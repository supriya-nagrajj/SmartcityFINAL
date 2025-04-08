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
    String img_url;
    String location_url;
    String street_view_url;
    double ratings;

    public Visit(int id, String name, String description, String img_url, String location_url, String street_view_url, double ratings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.location_url = location_url;
        this.street_view_url = street_view_url;
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

//        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/test"; 
        String dbUser = "root"; // Your DB username
        String dbPassword = "root"; // Your DB password

        try {
         
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            String query = "select * from test.visit"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Visit visit = new Visit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("img_url"),
                        rs.getString("location_url"),
                        rs.getString("street_view_url"),
                        rs.getDouble("ratings")
                );
                visits.add(visit);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Visit</title>");
        out.println("<link href=\'https://fonts.googleapis.com/css2?family=Raleway:wght@700&display=swap\' rel=\"stylesheet\">");
        out.println("<script src='https://cdn.tailwindcss.com'></script>");
        out.println("</head>");
        out.println("<body class='font-poppins text-sm text-gray-800 bg-[#F8F7F2] m-0 p-0'>");

        // Navigation bar
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


        // Main content
        out.println("<main class='pt-24 px-6'>");
        out.println("<h1 class='text-6xl text-center text-gray-800 mb-8' style=\"font-family: 'Raleway', serif; font-style: italic; font-weight: normal; letter-spacing: 2px; color: #357f8a; text-transform: uppercase;\">");
        out.println("Hidden Gems of Mumbai</h1>");
        out.println("<div class='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8'>");









        // Loop through the visits list and generate cards
        for (Visit visit : visits) {
            String shortDescription = visit.description.length() > 100
            ? visit.description.substring(0, 100) + "..."
            : visit.description;
            out.println("<div class='bg-blue-25 rounded-md m-4 mt-5 transition-transform duration-300 hover:scale-105 relative overflow-hidden'>");
            out.println("<div class = 'relative w-full h-64'>");
            out.println("<img src='" + visit.img_url + "' alt='" + visit.name + "' class='w-full h-[250px] object-cover rounded-t-md brightness-110'>");
            
            
            out.println("<a href='" + visit.location_url + "' class='absolute bottom-2.5 left-2.5 w-10 h-10 flex items-center justify-center rounded-full shadow-lg cursor-pointer transition-transform duration-300 hover:scale-110 hover:bg-[#ffd700]'>");
            out.println("<img src='images/location.svg' alt='Location Icon' class='w-6 h-6'>");
            out.println("</a>");
           
            
            //out.println("<iframe src='" + visit.street_view_url + "' class='w-full h-26 rounded-lg border-0'></iframe>");
            //out.println("<a href='" + visit.street_view_url + "' class='absolute bottom-2.5 right-2.5 w-10 h-10 flex items-center justify-center rounded-full cursor-pointer transition-transform duration-300 hover:scale-110 hover:bg-[#ffd700]'>");
            out.println("<img src='images/camera.svg' alt='Street View' class='absolute bottom-2.5 right-2.5 w-10 h-10 cursor-pointer rounded-xl shadow-lg transition-transform duration-300 hover:scale-110 hover:bg-[#ffd700]' onclick=\"openStreetView('" + visit.street_view_url + "')\">");

//            out.println("</a>");
           // out.println("</iframe>");
            
            
            
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
        
        out.println("<div id='streetViewModal' class='hidden fixed inset-0 flex items-center justify-center bg-black bg-opacity-50'>");
        out.println("  <div class='bg-white p-4 rounded-lg shadow-lg relative w-[90vw] h-[60vh] flex flex-col items-center justify-center'>");
        out.println("    <img src='images/close.svg' alt='Close' onclick='closeStreetView()' class='absolute top-3 right-3 w-8 h-8 cursor-pointer hover:scale-110 transition-transform'>");
        out.println("    <iframe id='streetViewFrame' class='w-full h-full rounded-lg' frameborder='0'></iframe>");
        out.println("  </div>");
        out.println("</div>");

        
        out.println("<script>");
        out.println("function openStreetView(url) {");
        out.println("    document.getElementById('streetViewFrame').src = url;");
        out.println("    document.getElementById('streetViewModal').classList.remove('hidden');");
        out.println("}");
        out.println("function closeStreetView() {");
        out.println("    document.getElementById('streetViewModal').classList.add('hidden');");
        out.println("    document.getElementById('streetViewFrame').src = '';"); // Reset iframe
        out.println("}");
        out.println("</script>");
//        

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