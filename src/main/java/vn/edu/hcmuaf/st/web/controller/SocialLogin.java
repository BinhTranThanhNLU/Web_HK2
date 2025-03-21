package vn.edu.hcmuaf.st.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.constant.Iconstant;

import java.io.IOException;
import java.util.Random;

public class SocialLogin {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();
        System.out.println("Google Token Response: " + response); // Debug
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        if (!jobj.has("access_token")) {
            System.out.println("Lỗi: Không tìm thấy access_token trong phản hồi.");
            return null;
        }
        String accessToken = jobj.get("access_token").getAsString();
        return accessToken;
    }

    //    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
//        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
//        String response = Request.Get(link).execute().returnContent().asString();
//
//        // Đọc dữ liệu từ JSON và chuyển thành GoogleAccount
//        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
//
//        // Truyền "name" từ Google API thành "fullName" trong GoogleAccount
//        String fullName = jobj.has("name") ? jobj.get("name").getAsString() : "";
//        String image = jobj.has("picture") ? jobj.get("picture").getAsString() : "";
//        boolean verifiedEmail = jobj.has("verified_email") && jobj.get("verified_email").getAsBoolean();
//
//        // Giả sử bạn lấy idRole từ một nguồn nào đó (có thể là một giá trị mặc định hoặc từ DB)
//        int idRole = 2;  // Ví dụ: Gán giá trị mặc định cho idRole
//
//        // Sinh ngẫu nhiên một username từ email
//        String username = generateRandomUsername(jobj.get("email").getAsString());
//
//        // Sinh mật khẩu ngẫu nhiên
//        String password = generateRandomPassword();
//
//        // Tạo đối tượng GoogleAccount với fullName và idRole
//        GoogleAccount googleAccount = new GoogleAccount(
//                jobj.get("id").getAsString(),
//                jobj.get("email").getAsString(),
//                fullName,  // Gán fullName thay vì name
//                image,
//                verifiedEmail,  // Thêm verifiedEmail nếu cần thiết
//                idRole,  // Thêm idRole
//                username,  // Thêm username ngẫu nhiên
//                password   // Thêm mật khẩu ngẫu nhiên
//        );
//
//        System.out.println("GoogleAccount: " + googleAccount);
//        return googleAccount;
//    }
    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        // Đọc dữ liệu từ JSON và chuyển thành GoogleAccount
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        // Truyền "name" từ Google API thành "fullName" trong GoogleAccount
        String fullName = jobj.has("name") ? jobj.get("name").getAsString() : "";
        String image = jobj.has("picture") ? jobj.get("picture").getAsString() : "";
        boolean verifiedEmail = jobj.has("verified_email") && jobj.get("verified_email").getAsBoolean();

        // Tạo một số điện thoại ngẫu nhiên (giả lập)
        String phoneNumber = generateRandomPhoneNumber();

        // Giả sử bạn lấy idRole từ một nguồn nào đó (có thể là một giá trị mặc định hoặc từ DB)
        int idRole = 2;  // Ví dụ: Gán giá trị mặc định cho idRole

        // Sinh ngẫu nhiên một username từ email (hoặc sử dụng cách khác tùy nhu cầu)
        String username = generateRandomUsername(jobj.get("email").getAsString());
        String password = generateRandomUsername(jobj.get("email").getAsString()); // Ví dụ: Dùng email để sinh password

        // Tạo đối tượng GoogleAccount với phoneNumber đã lấy
        GoogleAccount googleAccount = new GoogleAccount(
                jobj.get("id").getAsString(),
                jobj.get("email").getAsString(),
                fullName,
                image,
                verifiedEmail,
                idRole,
                username,
                password,
                phoneNumber // Truyền phoneNumber vào constructor
        );

        return googleAccount;
    }

    // Hàm tạo số điện thoại ngẫu nhiên
    private static String generateRandomPhoneNumber() {
        // Giả lập một số điện thoại ngẫu nhiên bắt đầu với +84 (mã quốc gia Việt Nam)
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("+84");

        // Thêm 9 chữ số ngẫu nhiên
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10)); // Chọn ngẫu nhiên một chữ số từ 0 đến 9
        }

        return phoneNumber.toString();
    }

    // Phương thức để sinh username ngẫu nhiên từ email
    private static String generateRandomUsername(String email) {
        String usernameBase = email.split("@")[0]; // Lấy phần trước dấu '@'
        String randomSuffix = String.valueOf(System.currentTimeMillis()); // Thêm dấu thời gian ngẫu nhiên để đảm bảo duy nhất
        return usernameBase + randomSuffix; // Kết hợp để tạo username
    }

    // Phương thức để sinh mật khẩu ngẫu nhiên
    private static String generateRandomPassword() {
        // Tạo một mật khẩu ngẫu nhiên dài 8-12 ký tự
        String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {  // Tạo mật khẩu dài 12 ký tự
            int randomIndex = (int) (Math.random() * passwordChars.length());
            password.append(passwordChars.charAt(randomIndex));
        }
        return password.toString();  // Trả về mật khẩu ngẫu nhiên
    }

}
