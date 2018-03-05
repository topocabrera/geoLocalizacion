package geolocalizacion

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import grails.converters.JSON
import groovy.json.JsonSlurper

class ConsultaController {

    ConsultaService consultaService

    def path = ""

    def index() {
        def url = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods')
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()

        def lista = json.parse(connection.getInputStream())

        def listaTicket = new ArrayList()
        def obj
        lista.each{
            if(it.payment_type_id == "ticket"){
                obj = [id:it.id, name: it.name]
                listaTicket.add(obj)
            }
        }

        render(view: "index", model: [listaTicket: listaTicket])
    }



    def consulta() {

        String direccion = params.direccion
        String pago = params.pago
        String radio = params.radio

        def postal = consultaService.getCoordenadas(direccion)

        def lat = postal.lat.toString()
        def lng = postal.lng.toString()


        def url = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods/'+pago+'/agencies?near_to='+lat+','+lng+','+radio+'&sort_by=distance,desc')
        path = url
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()

        def consult = json.parse(connection.getInputStream())


        render (view:"table", model: [consult: consult])

    }


}
