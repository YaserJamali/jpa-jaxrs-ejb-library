package tamin.library.model.service.BL;

import com.google.gson.Gson;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class Utils<T> {





    public String listWrap(List<T> list) {
        StringBuilder builder = new StringBuilder();
        for (T t : list) {
            builder.append(t).append("\n");
        }
        return new Gson().toJson(builder);
    }


}
