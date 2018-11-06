package combackandroid.mk.group.baseproject.BaseProject.API.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDemo {
    @SerializedName("contacts")
    private List<Contact> contacts = null;
}
