package geolocalizacion

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import grails.testing.web.controllers.ControllerUnitTest
import org.junit.Test
import spock.lang.Specification

class ConsultaControllerSpec extends Specification implements ControllerUnitTest<ConsultaController> {
    ConsultaService consultaService

    void testConsulta(){
        setup:
        String direccion = "Lima 78, Córdoba"
        String pago = "pagofacil"
        String radio = "500"

        def postal = consultaService.getCoordenadas(direccion)

        def lat = "-31.4129393"
        def lng = "-64.18163699999999"

        when:
        request.method = 'GET'
        controller.consulta()

        then:
        view == '/consulta/index'
    }

    void testOk(){
        when:
        request.method = 'GET'
        controller.index()

        then:
        response.status == 200

    }

   /* @Test
    public void testSimpleGeocode() throws Exception {
        try (GeoApiContext sc = new GeoApiContext(simpleGeocodeResponse)) {
            GeocodingResult[] results = GeocodingApi.newRequest(sc.context).address("Sydney").await();
            checkSydneyResult(results);

            sc.assertParamValue("Sydney", "address");
        }
    }*/
}
