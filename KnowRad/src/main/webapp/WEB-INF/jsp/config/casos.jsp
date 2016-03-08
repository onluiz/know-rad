<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value='/assets/css/chosen.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/assets/css/jquery.dataTables.min.css' />" />

    <!-- JS -->
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.dataTables.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/chosen.jquery.min.js' />"></script>

    <!-- Service -->
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/study/modalidade-service.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/caso-service.js' />"></script>

    <script>
        "use strict";

        var UICasosController = {

            save: function() {

                var casoSaveResponseDTO = {
                    titulo: $("#titulo").val(),
                    laudo: $("#laudo").val(),
                    listIdModalidades: $("#modalidades").val()
                };

                CasoService.save(casoSaveResponseDTO, {

                    done: function(data) {
                        alert("Operação realizada com sucesso.");
                    },

                    err: function(err) {
                        console.log(err);
                    }

                });

            },

            remove: function(idCaso) {
                CasoService.remove(idCaso, {

                    done: function(data) {
                        alert("Operação realizada com sucesso.");
                    },

                    err: function(err) {
                        console.log(err);
                    }

                });
            },

            initChose: function() {
                $(".chosen-select").chosen();
            },

            initTable: function() {
                $("#table").dataTable();
            },

            init: function() {
                this.initTable();
            }

        };

        $(function() {
            UICasosController.init();
        });
    </script>
</head>
<body>
    <div class="row">
        <h3>Casos Cadastrados</h3>
        <table class="table" id="table">
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>titulo</td>
                    <td>ações</td>
                </tr>
                <c:forEach items="${listCasosDTO}" var="casoDTO">
                    <tr>
                        <td>${casoDTO.titulo}</td>
                        <td>
                            <a href="#void" class="btn btn-xs btn-info" title="Editar"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Patologias"><span class="glyphicon glyphicon-book" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Modalidades"><span class="glyphicon glyphicon-queen" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Palavras-chave"><span class="glyphicon glyphicon-education" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-danger" title="Remover"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>