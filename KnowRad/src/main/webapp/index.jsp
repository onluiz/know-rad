<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">

<html>
    <head>

        <script src="<c:url value='/assets/js/jquery-2.1.4.min.js' />"></script>

        <script>
            function search() {
                var text = document.getElementById("search-text").value;
                $.ajax({
                    type: "GET",
                    url: '<c:url value="/search/" />',
                    cache: false,
                    data: ({text: text})
                }).done(function (data) {

                    if(data == null || data.length == 0)
                        return $("#resultados").html("<br>Nenhum resultado encontrado.");

                    $("#resultados").html("<br>");

                    var result = "";

                    for(var i = 0; i < data.length; i++) {

                        var item = data[i];

                        result += "<div>";

                        result += "Pk: " + item.pk + "<br>";

                        result += "AN: " + item.accessionNo + "<br>";

                        result += "Paciente: " + item.patName + "<br>";

                        result += "Laudo: " + item.texto + "<br>";

                        result += "<div />";

                        result += "<br>";

                    }

                    $("#resultados").append(result);
                });
            }

            function enterSearchEvt(e) {
                if (e.keyCode == 13) {
                    search();
                }
            }
        </script>
    </head>

    <body>
        <div>
            <label>Últimas pesquisas:</label>
            <div>littleanx123</div>
            <div>JACI LEMOS CORREA^^^^</div>
        </div>

        <br>

        <div>
            <label for="search-text">Digite o que deseja buscar:</label>
            <input id="search-text" onkeypress="enterSearchEvt(event);" style="width: 500px">
            <button onclick="search();">Buscar</button>
        </div>

        <br>

        <label>Resultados:</label>
        <div id="resultados"></div>
    </body>
</html>