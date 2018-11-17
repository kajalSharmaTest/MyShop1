package myshop.com.myshop.network;

import myshop.com.myshop.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 17/11/2018.
 */

public interface ApiInterface {

    static String BASE_URL = "https://bofindevstorage.blob.core.windows.net/categories/";

    @GET("categories.json")
    Call<Product> getProducts();

}
