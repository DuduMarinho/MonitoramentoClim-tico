// package server.src;

// import static spark.Spark.*;

// import com.google.gson.Gson;

// public class Main {
//     public static void main(String[] args) {
//         DAOConnect daoConnect = new DAOConnect();
//         Gson gson = new Gson();

//         post("/login", (request, response) -> {
//             User user = gson.fromJson(request.body(), User.class);
//             boolean success = daoConnect.loginUser(user.getEmail(), user.getPassword());
//             return gson.toJson(new ResponseMessage(success, success ? "Login bem-sucedido!" : "Falha no login."));
//         });

//         post("/register", (request, response) -> {
//             User user = gson.fromJson(request.body(), User.class);
//             boolean success = daoConnect.registerUser(user.getEmail(), user.getPassword());
//             return gson.toJson(new ResponseMessage(success, success ? "Registro bem-sucedido!" : "Falha no registro."));
//         });
//     }

//     static class User {
//         private String email;
//         private String password;

//         public String getEmail() {
//             return email;
//         }

//         public String getPassword() {
//             return password;
//         }
//     }

//     static class ResponseMessage {
//         private boolean success;
//         private String message;

//         public ResponseMessage(boolean success, String message) {
//             this.success = success;
//             this.message = message;
//         }

//         public boolean isSuccess() {
//             return success;
//         }

//         public String getMessage() {
//             return message;
//         }
//     }
// }
