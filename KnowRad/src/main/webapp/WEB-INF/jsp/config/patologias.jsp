<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value='/assets/css/chosen.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/assets/css/jquery.dataTables.min.css' />" />
    <style>
        .chosen-container {
            width: 100% !important;
        }
    </style>

    <!-- JS -->
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.dataTables.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/jquery.dataTables.bootstrap.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/chosen.jquery.min.js' />"></script>
    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>--%>

    <!-- Service -->
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/patologia-service.js' />"></script>
    <%--<script type="text/javascript" src="<c:url value='/assets/js/knowrad/study/modalidade-service.js' />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/caso-service.js' />"></script>--%>

    <!-- Controller -->
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/controller/config/patologias-controller.js' />"></script>

    <script>
        $(function() {
            UIPatologiasController.init();
        });
    </script>
</head>
<body>
<div class="row">
    <h3>Patologias</h3>

    <a href="#void" class="btn btn-info" onclick="UIPatologiasController.openModal();">Nova</a>

    <br>
    <br>

    <table class="table table-striped table-bordered table-hover" id="table_report">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="11" style="text-align: center;">Nenhum registro encontrado ...</td>
        </tr>
        </tbody>
    </table>
</div>

<!-- Modal de novo Caso -->
<div class="modal fade" id="modal-edicao" tabindex="-1" role="dialog" aria-labelledby="modal-edicao">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span>Cadastrar/Editar Patologias</span></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id-edicao" name="id-edicao" value="0">

                <label for="nome-edicao">Nome:</label>
                <input id="nome-edicao" name="nome-edicao" type="text" class="form-control">

                <br>

                <div id="termos">
                    <label for="novo-termo">Novo Termo:</label>
                    <input id="novo-termo" name="novo-termo" type="text" class="form-control">
                    <a class="btn btn-info" href="#void" onclick="UIPatologiasController.saveTermo();">Salvar Termo</a>

                    <style>
                        #list-termos {
                            /*float:left;*/
                            /*width:1000px;*/
                            overflow-y: auto;
                            height: 200px;
                        }
                    </style>

                    <div id="list-termos">
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="UIPatologiasController.saveOrUpdate();">Salvar</button>
                <button type="button" class="btn btn-default" onclick="UIPatologiasController.closeModal();">Cancelar</button>
            </div>
        </div>
    </div>
</div>
</body>