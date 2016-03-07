<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value='/assets/css/chosen.min.css' />" />

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

                console.log(casoSaveResponseDTO);

                CasoService.save(casoSaveResponseDTO, {

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

            init: function() {
                this.initChose();
            }

        };

        $(function() {
            UICasosController.init();
        });
    </script>
</head>
<body>
    <div class="row">
        <div class="col-md-6">
            <h3>Casos Cadastrados</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>Título</th>
                        <th>Ações</th>
                    </tr>
                </thead>
            </table>
        </div>
        <div class="col-md-6">
            <input type="hidden" id="id-caso-edicao" name="id-caso-edicao">

            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" class="form-control">

            <br>

            <label for="modalidades">Modalidades:</label>

            <br>

            <select id="modalidades" name="modalidades"
                    data-placeholder="Seleciona as modalidades do caso..."
                    style="width:350px;" multiple class="chosen-select">
                <c:forEach items="${listModalidadesDTO}" var="modalidadeDTO">
                    <option value="${modalidadeDTO.idModalidade}">${modalidadeDTO.modalidade}</option>
                </c:forEach>
            </select>

            <br>
            <br>

            <label for="laudo">Laudo:</label>
            <textarea id="laudo" name="laudo" class="form-control" rows="15"></textarea>

            <br>

            <a class="btn btn-success" onclick="UICasosController.save();">Salvar</a>
            <a class="btn btn-info">Limpar</a>
        </div>
    </div>

</body>