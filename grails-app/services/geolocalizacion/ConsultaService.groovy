package geolocalizacion

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import grails.gorm.transactions.Transactional

@Transactional
class ConsultaService {

    def serviceMethod() {

    }

    def getCoordenadas(direccion){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDBXQOEfp35HT0F87ahIMVZwf58cVEU_Wc")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context,direccion).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        def lat = gson.toJson(results[0].geometry.location.lat).toString()
        def lng = gson.toJson(results[0].geometry.location.lng).toString()

        return [lat: lat, lng: lng]
    }
}
