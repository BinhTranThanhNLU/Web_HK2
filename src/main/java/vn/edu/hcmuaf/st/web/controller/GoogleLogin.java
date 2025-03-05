package vn.edu.hcmuaf.st.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.constant.Iconstant;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;

import java.io.IOException;


public class GoogleLogin {
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

    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {

        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        String response = Request.Get(link).execute().returnContent().asString();

        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);
        System.out.println("GoogleAccount: " + googlePojo);
        return googlePojo;

    }
}
