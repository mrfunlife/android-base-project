package combackandroid.mk.group.baseproject.BaseProject.API.Models;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Contact {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("gender")
    private String gender;
//    @SerializedName("phone")
//    private Phone phone;
}
