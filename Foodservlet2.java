
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Food class
class Food {

    int id;
    String type;
    String name;
    String place;
    String address;
    String description;
    String imageurl;
    String imageurl2;
    String imageurl3;
    double rating;

    public Food(int id, String name, String place, String type, String address, String imageurl, String imageurl2, String imageurl3, double rating, String description) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.type = type;
        this.address = address;
        this.imageurl = imageurl;
        this.imageurl2 = imageurl2;
        this.imageurl3 = imageurl3;
        this.rating = rating;
        this.description = description;
    }
}

@WebServlet("/Foodservlet2")
public class Foodservlet2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String selectedplace = request.getParameter("place");
        ArrayList<Food> foodList = new ArrayList<>();

        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/test";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            // Load MySQL driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            // Secure SQL Query using PreparedStatement
            String query = "SELECT * FROM food WHERE place = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, selectedplace);
            ResultSet rs = pstmt.executeQuery();

            // Retrieve data
            while (rs.next()) {
                Food food = new Food(
                        rs.getInt("id"), // Ensure this matches the actual column name
                        rs.getString("name"),
                        rs.getString("place"),
                        rs.getString("type"),
                        rs.getString("address"),
                        rs.getString("imageurl"),
                        rs.getString("imageurl2"),
                        rs.getString("imageurl3"),
                        rs.getDouble("rating"),
                        rs.getString("description")
                );
                foodList.add(food);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // HTML Response
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Food Places</title>");
        out.println("<script src='https://cdn.tailwindcss.com'></script>");
        out.println("</head>");
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
            "<a href=\"food.html\" class=\"text-[#328c9c] text-sm sm:text-lg font-bold px-2 sm:px-3 py-1 sm:py-2 rounded transition hover:bg-[#f6ecc9]\">Food</a>");

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
        out.println("<body class='bg-gray-100 text-gray-800 p-6'>");
        out.println("<h1 class='text-3xl font-bold text-center mb-6 my-16'>Food Places in " + selectedplace + "</h1>");

        // Display food places
        out.println("<div class='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6'>");
        for (Food food : foodList) {
            out.println("<div class='bg-white p-4 rounded-lg shadow-lg'>");
            out.println("<img src='" + food.imageurl + "' alt='" + food.name + "' class='w-full h-48 object-cover rounded-md'>");
            out.println("<h3 class='text-lg font-bold mt-2'>" + food.name + "</h3>");
            out.println("<p class='text-gray-600 text-sm'>" + food.description + "</p>");
            out.println("<p class='text-gray-600 text-sm'>Type: " + food.type + "</p>");
            out.println("<p class='text-gray-600 text-sm'>Address: " + food.address + "</p>");
            out.println("<p class='text-yellow-500 font-bold mt-2'>Rating " + food.rating + "</p>");
            out.println("</div>");
        }
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
    }
}
