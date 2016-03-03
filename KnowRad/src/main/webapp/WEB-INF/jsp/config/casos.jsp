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

    <script>
        "use strict";

        var UICasosController = {

            initModalidades: function() {
                ModalidadeService.findAllDTO({
                    done: function(data) {
                        var listDTO = data;
                        var modalidadesElement = $("#modalidades");

                        listDTO.forEach(function(dto) {
                            $(modalidadesElement).append(new Option(dto.modalidade, dto.idModalidade));
                        });
                    },

                    fail: function(err) {
                        console.log(err);
                    }
                });
            },

            initChose: function() {
                $(".chosen-select").chosen();
            },

            init: function() {
                this.initModalidades();
                this.initChose();
            }

        };

        $(function() {
            UICasosController.init();
        });
    </script>
</head>
<body>

    <h1>Cadastro/Lista de Casos</h1>

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
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" class="form-control">

            <br>

            <label for="modalidades">Modalidades:</label>
            <select id="modalidades" name="modalidades"
                    data-placeholder="Seleciona as modalidades do caso..."
                    style="width:350px;" multiple class="chosen-select">
            </select>

            <br>

            <label for="laudo">Laudo:</label>
            <textarea id="laudo" name="laudo" class="form-control" rows="18"></textarea>

            <br>

            <a class="btn btn-success">Salvar</a>
            <a class="btn btn-info">Limpar</a>
        </div>
    </div>

</body>