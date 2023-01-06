import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mongodb.AggregationOutput;


@WebServlet("/Inventory")
public class Inventory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Inventory</a></h2>"
                + "<div class='entry'>");

                pw.print("<h3>All Products</h3>");
                pw.print("<table class=\"table table-striped\">");
                pw.print("<thead><tr><th scope=\"col\">Product name</th><th scope=\"col\">Product price</th><th scope=\"col\">Quantity</th></tr></thead>");
                pw.print("<tbody>");
                ArrayList<Product> products = MySqlDataStoreUtilities.selectProductsForInventory();
                for(Product product : products) {
                    pw.print("<tr><th scope=\"row\">" + product.getName() + "</th><td>" + product.getPrice() +"</td><td>" + product.getAvailable() + "</td></tr>");
                }
                pw.print("</tbody>");
                pw.print("</table>");
            
        pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        
        pw.print("<br><br><h3>Products on sale</h3>");
        ArrayList<Product> productsOnSale = new ArrayList<>();
        for(Product product : products) {
            if(product.getOnSale()) {
                productsOnSale.add(product);
            }
        }
        pw.print("<table class=\"table table-striped\">");
        pw.print("<thead><tr><th scope=\"col\">Name</th><th scope=\"col\">Price</th></tr></thead>");
        pw.print("<tbody>");
        
        for(Product product : productsOnSale) {
            pw.print("<tr><th scope=\"row\">" + product.getName() + "</th><td>" + product.getPrice() +"</td></tr>");
        }
        pw.print("</tbody>");
        pw.print("</table>");

        pw.print("<br><br><h3>Products on Manufacturer Rebate</h3>");
        ArrayList<Product> productsOnManufacturerRebate = new ArrayList<>();
        for(Product product : products) {
            if(product.getManufacturerRebate()) {
                productsOnManufacturerRebate.add(product);
            }
        }
        pw.print("<table class=\"table table-striped\">");
        pw.print("<thead><tr><th scope=\"col\">Name</th><th scope=\"col\">Price</th></tr></thead>");
        pw.print("<tbody>");
        
        for(Product product : productsOnManufacturerRebate) {
            pw.print("<tr><th scope=\"row\">" + product.getName() + "</th><td>" + product.getPrice() +"</td></tr>");
        }
        pw.print("</tbody>");
        pw.print("</table>");
        pw.println("</div></div></div>");
        
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='Inventory.js'></script>");
        utility.printHtml("Footer.html");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ArrayList<Review> reviews = MongoDBDataStoreUtilities.selectReviewForChart();
            // ArrayList<Review> topReviewsPerCity = getTop3InEveryCity(reviews);
            
            // String reviewJson = new Gson().toJson(topReviewsPerCity);

            ArrayList<Product> products = MySqlDataStoreUtilities.selectProductsForInventory();
            String reviewJson = new Gson().toJson(products);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {
            
        }

    }

}
