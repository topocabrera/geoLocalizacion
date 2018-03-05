<!DOCTYPE html>
<html>
<head>
    %{--<meta name="layout" content="main" />--}%

    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application.css"/>
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">

    <style>

    #map {
        height: 80%;
    }

    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
    </style>

</head>
<body>
    <table>
    <tr>
        <th> Descripcion</th>
        <th>Distancia </th>
    </tr>
        <g:each in="${consult.results}" var="p">
            <tr>
                <td>${p.distance}</td>
                <td>
                    ${p.description}
                    <button type="button" name="${p.description}" class="details">+</button>
                </td>
            </tr>
        </g:each>
    </table>


<div id="map"></div>

<script>

        $(".details").on("click", function () {
            var name = $(this).attr("name");
            <g:each in="${consult.results}" var="p">
            if (("${p.description}") == (name)) {
                $.alert({
                    title: "<h1>Agencia: </h1>${p.description}",
                    content: "<dl> <dt>Direccion:</dt> <dd> ${p.address.address_line}</dd>" +
                    "<dt>Ciudad:</dt> ${p.address.city}<br>" +
                    "<strong>Pais:</strong> ${p.address.country}<br>" +
                    "<strong>Provincia:</strong> ${p.address.state}<br>" +
                    "<strong>Codigo Postal:</strong> ${p.address.zip_code}<br>",
                });
            }
            </g:each>
        });

</script>

<script>

    var arrayResultados = [];
    <g:each in = "${consult.results}">
    var s= "${it.address.location}";
    console.log(s);
    var arrayS = s.split(",");
    arrayResultados.push({lat: parseFloat(arrayS[0]), lng: parseFloat(arrayS[1])});
    </g:each>

    var centro = arrayResultados[0]

    var markers = [];
    var map;

    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: centro
        });
        for (i in arrayResultados) {
            addMarker(arrayResultados[i])
        }
    }

    function addMarker(position) {
        window.setTimeout(function() {
            markers.push(new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            }));
        });
    }

</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD1ZUDttXsM1IfOHKpSeQgElavORd64Wu8&callback=initMap">
</script>

</body>



</html>