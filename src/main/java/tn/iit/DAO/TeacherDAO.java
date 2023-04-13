package tn.iit.DAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tn.iit.model.Teacher;


public class TeacherDAO {
    private Connection conn;

    public TeacherDAO(Connection conn) {
        this.conn = conn;
    }

    // Add a new teacher
    public boolean addTeacher(Teacher teacher) {
        String query = "INSERT INTO teachers (first_name, last_name, email, institution, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, teacher.getFirst_name());
            statement.setString(2, teacher.getLast_name());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getInstitution() );
            statement.setLong(5, teacher.getPhone());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a teacher by ID
    public boolean deleteTeacher(int id) {
        String query = "DELETE FROM teachers WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update a teacher by ID
    public boolean updateTeacher(int id, Teacher teacher) {
        String query = "UPDATE teachers SET first_name = ?, last_name = ?, email = ?, institution = ?, phone = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, teacher.getFirst_name());
            statement.setString(2, teacher.getLast_name());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getInstitution() );
            statement.setLong(5, teacher.getPhone());
            statement.setInt(6, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // List all teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String institution = resultSet.getString("institution");
                int phone = Integer.parseInt(resultSet.getString("phone")); 
                Teacher teacher = new Teacher(id, first_name, last_name, email, institution,phone);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
    
    // Get teacher by id
    public Teacher getTeacherById(int id) {
        String query = "SELECT * FROM teachers WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String institution = resultSet.getString("institution");
                int phone = Integer.parseInt(resultSet.getString("phone")); 
                Teacher teacher = new Teacher(id, first_name, last_name, email, institution,phone);
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Import teachers from a CSV file
    public boolean importTeachers(String filePath) {
        String query = "INSERT INTO teachers (name, email, phone) VALUES (?, ?, ?)";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             PreparedStatement statement = conn.prepareStatement(query)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String email = data[1].trim();
                String phone = data[2].trim();
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.addBatch();
            }
            int[] rowsInserted = statement.executeBatch();
            for (int row : rowsInserted) {
                if (row == Statement.EXECUTE_FAILED) {
                    return false;
                }
            }
            return true;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}