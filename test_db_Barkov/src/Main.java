import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "/////";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdbBarkov";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        try{
            String sqlInsertUser = "insert into users(first_name, second_name, age)" +
                    "values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
            for (int i = 0; i < 6; i++){
                System.out.println("Введите имя, фамилию и возраст пользователя");
                String firstName = scanner.nextLine();
                String secondName = scanner.nextLine();
                int age = Integer.parseInt(scanner.nextLine());

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, secondName);
                preparedStatement.setInt(3, age);

                preparedStatement.addBatch();
            }

            int[] affectedRows = preparedStatement.executeBatch();

            System.out.println("Было добавлено " + Arrays.toString(affectedRows) + " строк");
        } catch (SQLException e){
            e.printStackTrace();
        }

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from users where first_name LIKE 'Рус%'");

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("first_name") + " " + result.getString("second_name"));
        }

        connection.close();


    }
}
