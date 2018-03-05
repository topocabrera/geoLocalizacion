<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <style>

    #map {
        height: 100%;
    }

    html, body {

        height: 100%;
        margin: 0;
        padding: 25px;
    }
    </style>
</head>
<body>

    <fieldset align="center">
    <g:form action="consulta" name="consultaForm" resource="${this.consulta}" controller="consulta" method="GET">


        <g:textField name="direccion" placeholder="Direccion">  </g:textField> <br>

        <select name="pago" id="pago" >
            <g:each in="${listaTicket}" var="p">
                <option value="${p.id}">${p.name}</option>
            </g:each>
        </select> <br>

        <g:textField name="radio" placeholder="Radio (mts)"> </g:textField><br>
            <g:submitButton name="Consultar" class="consulta"/>
    </g:form>
    </fieldset>
</body>
</html>