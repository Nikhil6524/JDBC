import java.sql.*;
import java.util.Scanner;
public class Main {
    private static final String url="jdbc:mysql://localhost:3306/mydb";
    private static final String username="root";
    private static final String password ="Nikhil@2004";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e) {
            System.out.println("MySQL JDBC Driver not found. Please add the driver to your classpath.");
            e.printStackTrace();
        }
        try{
            Connection connection=DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            /* SELECT,UPDATE,INSERT,DELETE EXAMPLE */

            /*String query = "SELECT * FROM STUDENTS";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                System.out.println("ID: " + id + ", Name: " + name + " ");
            }*/  //getting table from database
            /*String query=String.format("INSERT INTO STUDENTS(NAME,AGE,MARKS) VALUES('%s',%o,%d)","Rahul",22,90);
            int resultSet = statement.executeUpdate(query);
            if (resultSet > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }*/ //inserting data into table
            /*String query = "UPDATE STUDENTS SET NAME='Nikhil' WHERE ID=1";
            int resultSet = statement.executeUpdate(query);
            if(resultSet> 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("Data update failed.");
            }*/ //updating data in table
            /*String query = "DELETE FROM STUDENTS WHERE ID=2";
            int resultSet = statement.executeUpdate(query);
            if(resultSet > 0) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Data deletion failed.");
            }*/ //deleting data from table


            /* PREPARED STATEMENT EXAMPLE */

            /*String query = "INSERT INTO STUDENTS(NAME, AGE, MARKS) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Rahul");
            preparedStatement.setInt(2, 22);
            preparedStatement.setInt(3, 90);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Data inserted successfully using PreparedStatement.");
            } else {
                System.out.println("Data insertion failed using PreparedStatement.");
            }*/


            /*BATCH PROCESSING EXAMPLE*/
            while(true){
                Scanner sc=new Scanner(System.in);
                String name= sc.nextLine();
                int age= sc.nextInt();
                double marks= sc.nextDouble();
                String query = String.format(("INSERT INTO STUDENTS(NAME, AGE, MARKS) VALUES('%s', %o, %f)"), name, age, marks);
                statement.addBatch(query);
                char choice = sc.next().charAt(0);
                if(choice == 'n' || choice == 'N') {
                    break;
                }
            }
            int resultSet[] = statement.executeBatch();
            int successCount = 0;
            for (int count : resultSet) {
                if (count >= 0) {
                    successCount++;
                }
            }
            System.out.println(successCount + " records inserted successfully using batch processing.");

        }
        catch (SQLException e) {
            System.out.println("Connection failed. Please check your URL, username, and password.");
            e.printStackTrace();
        }
    }
}