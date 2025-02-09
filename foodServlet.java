import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

//import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/foodServlet")
public class foodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query to fetch all food places
            String sql = "SELECT * FROM food";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Start building the HTML response
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Food Places</title>");
            out.println("<script src='https://cdn.tailwindcss.com'></script>"); // Load Tailwind CSS
            out.println("</head>");
            out.println("<body class='bg-gray-100 text-gray-800'>");
            out.println(
                    "<nav class=\"flex justify-between items-center bg-[#fffee2] text-[#38808d] fixed top-0 w-full z-50 shadow-lg\">");
            out.println(
                    "<img src=\"images\\logo_skyline_brightened.jpg\" alt=\"Mumbai Safar Logo\" id=\"logo\" class=\"h-16 sm:h-20 p-0 m-0\" />");
            out.println("<div class=\"flex space-x-2 sm:space-x-4 mr-4 sm:mr-8\">");
            out.println(
                    "<a href=\"index.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Home</a>");
            out.println(
                    "<a href=\"visit.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Visit</a>");
            out.println(
                    "<a href=\"foods.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Food</a>");

            out.println(" <div class=\"relative inline-flex text-left group\">");
            out.println(
                    "<a href=\"#\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Bookings<a>");
            out.println(
                    "<div class=\"absolute left-18 mt-[40px] w-32 sm:w-40 rounded-lg shadow-lg bg-white opacity group-hover:opacity-100 invisible group-hover:visible transition-opacity duration-500\">");

            out.println("<ul class=\"bg-[#fff9e4] border-none rounded-lg\">");
            out.println("<li>");
            out.println(
                    "<a href=\"HotelsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\">Hotels</a>");
            out.println("</li>");

            out.println("<li>");
            out.println(
                    "<a href=\"TrainsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\"> Trains </a>");
            out.println("</li>");

            out.println("<li>");
            out.println(
                    "<a href=\"BusServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\"> Bus </a>");
            out.println("</li>");

            out.println("<li>");
            out.println(
                    "<a href=\"CabsServlet\" class=\"block px-2 sm:px-4 py-2 text-xs sm:text-sm font-bold text-[#328c9c] hover:bg-[#f3ebc7] hover:text-[#e25a5a]\"> Cabs </a>");
            out.println("</li>");

            out.println(" </ul>");
            out.println("</div>");
            out.println("</div>");

            out.println(
                    "<a href=\"events.html\"  class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Events</a>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<div class='min-h-screen flex flex-col items-center'>");
            out.println("<h1 class='text-3xl font-bold mt-10 mb-6 text-blue-600 my-30'>Food Places</h1>");
            out.println("<div class='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 px-4 w-full max-w-6xl'>");

            // Iterate over the result set and add cards for each food place
            while (rs.next()) {
                out.println("<div class='bg-white rounded-lg shadow-lg p-6'>");
                out.println("<h2 class='text-xl font-semibold text-gray-800'>" + rs.getString("name") + "</h2>");

                out.println("<img src='" + rs.getString("imageurl") + "' class='w-full h-48 object-cover'>");
                out.println("<p class='text-gray-600 mt-2'>Location: " + rs.getString("description") + "</p>");
                out.println("<p class='text-gray-600 mt-1'>Cuisine: " + rs.getString("address") + "</p>");
                out.println("<p class='text-yellow-500 font-bold mt-2'>Rating: " + rs.getFloat("rating") + " </p>");
                out.println("</div>");
            }

            out.println("</div>");
            out.println("</div>");
            out.println("<footer class=\"bg-gray-800 text-white py-8\">");
            out.println(
                    "<div class=\"max-w-7xl mx-auto px-6 flex flex-col md:flex-row justify-between items-center\">");
            out.println("<p class=\"text-sm\">&copy; 2025 Smart City - Mumbai Safar</p>");
            out.println("<div class=\"flex space-x-4 mt-4 md:mt-0\">");
            out.println("<!-- <a href=\"#\" class=\"text-blue-400 hover:text-blue-500\">Facebook</a> -->");
            out.println("</div>");
            out.println("</div>");
            out.println("</footer>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace(out);
            }
        }
    }
}
