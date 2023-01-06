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


@WebServlet("/DataVisualization")
public class DataVisualization extends HttpServlet {

    static DBCollection myReviews;

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
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
                + "<div class='entry'>");

                pw.print("<h3>All Orders</h3>");
                pw.print("<table class=\"table table-striped\">");
                pw.print("<thead><tr><th scope=\"col\">Product name</th><th scope=\"col\">Product price</th><th scope=\"col\">Quantity</th></tr></thead>");
                pw.print("<tbody>");
                ArrayList<OrderPayment> orders = MySqlDataStoreUtilities.selectOrderForMap();
                for(OrderPayment order : orders) {
                    pw.print("<tr><th scope=\"row\">" + order.getOrderName() + "</th><td>" + order.getOrderPrice() +"</td><td>" + order.getQuantity() + "</td></tr>");
                }
                pw.print("</tbody>");
                pw.print("</table>");
            
        pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        
        pw.print("<br><br><h3>All Orders By Date</h3>");
        HashMap<Date, Integer> countOfOrders = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for(OrderPayment order : orders) {
            Date dateOrderedShort = new Date();
            try {
                dateOrderedShort = formatter.parse(formatter.format(order.getDateOrdered()));
            } catch(Exception e) {
                e.printStackTrace();
            }
            if(countOfOrders.containsKey(dateOrderedShort)) {
                countOfOrders.put(dateOrderedShort, countOfOrders.get(dateOrderedShort)+1);
            } else {
                countOfOrders.put(dateOrderedShort, 1);
            }
        }
        pw.print("<table class=\"table table-striped\">");
        pw.print("<thead><tr><th scope=\"col\">Date</th><th scope=\"col\">Products Sold</th></tr></thead>");
        pw.print("<tbody>");
        
        for(Date eachDate : countOfOrders.keySet()) {
            pw.print("<tr><th scope=\"row\">" + formatter.format(eachDate) + "</th><td>" + countOfOrders.get(eachDate) +"</td></tr>");
        }
        pw.print("</tbody>");
        pw.print("</table>");
        pw.println("</div></div></div>");
        
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
        utility.printHtml("Footer.html");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ArrayList<Review> reviews = MongoDBDataStoreUtilities.selectReviewForChart();
            // ArrayList<Review> topReviewsPerCity = getTop3InEveryCity(reviews);
            
            // String reviewJson = new Gson().toJson(topReviewsPerCity);

            ArrayList<OrderPayment> orders = MySqlDataStoreUtilities.selectOrderForMap();
            String reviewJson = new Gson().toJson(orders);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {
            
        }

    }

    //This method takes all the reviews and returns top 3 review in every city;
    private static ArrayList<Review> getTop3InEveryCity(ArrayList<Review> reviewList){

        //sorting the list in ascending order;
        Collections.sort(reviewList, new Comparator<Review>(){
            public int compare(Review r1, Review r2){
                return Integer.parseInt(r2.getReviewRating()) - Integer.parseInt(r1.getReviewRating());
            }
        });

       HashMap<String,Review> reviewMap = new HashMap<>();

       //Get list of cities in all reviews;
       ArrayList<String> zipCodeList = new ArrayList<>();
       for(Review review:reviewList){
            String zipCode = review.getStoreZip();
            if(!(zipCodeList.contains(zipCode))){
                zipCodeList.add(zipCode);
            }
       } 

       //get top 3 reviews for every city;
       ArrayList<Review> top3Reviews = new ArrayList<>();
       for(String zipCode:zipCodeList){
            ArrayList<Review> top3ReviewsCity = new ArrayList<>();
            for(Review review:reviewList){
                if(review.getStoreZip().equals(zipCode) && top3ReviewsCity.size()<3){
                    top3ReviewsCity.add(review);
                }
            }
            top3Reviews.addAll(top3ReviewsCity); 
       }

        return top3Reviews;
    }

}
