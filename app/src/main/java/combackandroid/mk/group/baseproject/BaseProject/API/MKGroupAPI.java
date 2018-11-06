package combackandroid.mk.group.baseproject.BaseProject.API;

import java.util.List;
import java.util.Observable;


import combackandroid.mk.group.baseproject.BaseProject.API.Models.ResponseDemo;
import retrofit2.http.GET;

public interface MKGroupAPI {
    @GET("contacts/")
    rx.Observable<ResponseDemo> fetchDataContact();
}
