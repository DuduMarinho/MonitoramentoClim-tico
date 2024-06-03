package server.src;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOConnect {
    private WeatherService weatherService;

    public DAOConnect() {
        this.weatherService = new WeatherService();
    }
    public Connection dbConnect() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/skyweather_db?user=root&password=2811";
            conn = DriverManager.getConnection(url);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void getValues() {
        Connection conn = dbConnect();
        if (conn != null) {
            String query = "SELECT * FROM user_table";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("user_id");
                    String password = rs.getString("user_password");

                    System.out.println("ID: " + id + ", Nome: " + password);
                }
                rs.close();
            }  
            catch (SQLException e) {
                System.out.println("Erro ao executar a consulta: " + e.getMessage());
            } 
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
    public boolean registerUser(String email, String password) {
        Connection conn = dbConnect();
        if (conn != null) {
            String query = "INSERT INTO user_table (user_password) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                int rowsInserted = stmt.executeUpdate();

                return rowsInserted > 0;
            }  
            catch (SQLException e) {
                System.out.println("Erro ao registrar usuário: " + e.getMessage());
                return false;
            }           
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
        return false;
    }
    public boolean loginUser(String email, String password) {
        Connection conn = dbConnect();
        if (conn != null) {
            String query = "SELECT * FROM user_table WHERE user_email = ? AND user_password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                return rs.next();
            }            
            catch (SQLException e) {
                System.out.println("Erro ao verificar login: " + e.getMessage());
                return false;
            }           
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
        return false;
    }
    public String getRealTimeWeather(String location) {
        try {
            return weatherService.getWeatherData(location);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error fetching real-time weather data.";
        }
    }
    public String getWeatherHistory(String location, String date1, String date2) {
        try {
            return weatherService.getWeatherHistory(location, date1, date2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error fetching weather history data.";
        }
    }
    public static void main(String[] args) {
        DAOConnect daoConnect = new DAOConnect();
        daoConnect.getValues();

        // Teste da Funcionalidade 1 (O bglh em tempo real)
        String realTimeWeather = daoConnect.getRealTimeWeather("New York");
        System.out.println("Real-time Weather: " + realTimeWeather);

        // Teste da funcionalidade 2 (Consulta do histórico)
        String weatherHistory = daoConnect.getWeatherHistory("New York", "2023-06-01", "2023-06-02");
        System.out.println("Weather History: " + weatherHistory);

        // Teste de registro e login
        boolean registered = daoConnect.registerUser("test@example.com", "password123");
        if (registered) {
            System.out.println("Usuário registrado com sucesso!");
        } else {
            System.out.println("Falha ao registrar usuário.");
        }
        boolean loggedIn = daoConnect.loginUser("test@example.com", "password123");
        if (loggedIn) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Falha no login.");
        }
    }
}
