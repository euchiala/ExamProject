package tn.iit.DAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tn.iit.model.Teacher;
import tn.iit.model.Authorization;

public class AuthorizationDAO {


    private Connection conn;

    public AuthorizationDAO(Connection conn) {
        this.conn = conn;
    }

    // Add a new Authorization
    public boolean addAuthorization(Authorization authorization) {
        String query = "INSERT INTO authorizations (teacher_id, authorization_date, authorized_hours) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, authorization.getTeacher().getId());
            statement.setDate(2, new java.sql.Date(authorization.getAuthorization_date().getTime()));
            statement.setInt(3, authorization.getAuthorized_hours());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // List all Authorizations
    public List<Authorization> getAllAuthorizations() {
        List<Authorization> authorizations = new ArrayList<>();
        String query = "SELECT a.*,t.first_name, t.last_name, t.email, t.institution, t.phone\r\n"
        		+ "FROM authorizations AS a\r\n"
        		+ "INNER JOIN teachers AS t ON a.teacher_id = t.id";
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int teacher_id = resultSet.getInt("teacher_id");
                Date authorization_date	= resultSet.getDate("authorization_date");
                int authorized_hours = resultSet.getInt("authorized_hours");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String institution = resultSet.getString("institution");
                int phone = Integer.parseInt(resultSet.getString("phone"));
                Teacher teacher = new Teacher(teacher_id, first_name, last_name, email, institution,phone);
                Authorization authorization = new Authorization(id, teacher, authorization_date, authorized_hours);

                authorizations.add(authorization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorizations;
    }
    
    // Get authorization by id
    public Authorization getAuthorizationsById(int id) {
        String query = "SELECT a.*,t.first_name, t.last_name, t.email, t.institution, t.phone FROM authorizations a JOIN teachers t ON a.teacher_id = t.id WHERE a.id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               int teacher_id = resultSet.getInt("teacher_id");
               Date authorization_date	= resultSet.getDate("authorization_date");
               int authorized_hours = resultSet.getInt("authorized_hours");
               Authorization authorization = new Authorization(teacher_id, authorization_date, authorized_hours);
                return authorization;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());        }
        return null;
    }
}