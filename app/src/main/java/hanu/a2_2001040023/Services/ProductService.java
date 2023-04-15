package hanu.a2_2001040023.Services;

import java.util.List;

import hanu.a2_2001040023.Models.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products.json")
    Call<List<Product>> getAllProducts();
}
