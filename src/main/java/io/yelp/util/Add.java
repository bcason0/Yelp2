package io.yelp.util;

import java.sql.*;

public class Add {

    public static void addValue(String name, String mobile_URL, String phone, String rating, String reviewCount,
                                String is_close, String snippet_text,
                                String image_url, String rating_img_url_large) throws ClassNotFoundException, SQLException
    {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yelpdb?autoReconnect=true&useSSL=false","root","Eagles1181!");

        try {
            Statement statement = con.createStatement();
            String query = "select * from business order by business_id desc limit 1";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("business_id");
            }


            PreparedStatement update = con.prepareStatement
                    ("insert into business values(?,?,?,?,?,?,?,?,?,?)");
            update.setInt(1, (id + 1));
            update.setString(2, is_close);
            update.setString(3, mobile_URL);
            update.setString(4, name);
            update.setString(5, phone);
            update.setString(6, rating);
            update.setString(7, reviewCount);
            update.setString(8, image_url);
            update.setString(9, snippet_text);
            update.setString(10, rating_img_url_large);
            update.executeUpdate();
        }catch(Exception e){}
    }
}
