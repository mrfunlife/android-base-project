package combackandroid.mk.group.baseproject.BaseProject.API.Models;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Phone {
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("home")
    private String home;
    @SerializedName("office")
    private String office;
}
