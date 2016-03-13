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

    <!-- Service -->
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/patologia-service.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/study/modalidade-service.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/caso-service.js' />"></script>

    <!-- Controller -->
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/controller/config/casos-controller.js' />"></script>

    <script>
        $(function() {
            UICasosController.init();
        });
    </script>
</head>
<body>
    <div class="row">
        <h3>Casos Cadastrados</h3>

        <a href="#void" class="btn btn-info" onclick="UICasosController.openModalCaso();">Novo Caso</a>

        <br>
        <br>

        <table class="table table-striped table-bordered table-hover" id="table_report">
            <thead>
                <tr>
                    <th>Título</th>
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


    <!-- Modal das Modalidades -->
    <div class="modal fade" id="modal-modalidade" tabindex="-1" role="dialog" aria-labelledby="modal-modalidade">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span>Modalidades</span></h4>
                </div>
                <div class="modal-body">
                    <input id="id-caso-modal-modalidade" name="id-caso-modal-modalidade" type="hidden">
                    Adicionar Modalidades:
                    <select id="search-modalidades" name="search-modalidades" class="chzn-select chosen-select" style="width: 100px;">
                        <option value="0">Nenhum registro filtrado.</option>
                    </select>

                    <br>
                    <br>

                    <a href="#void" class="btn btn-info" onclick="UICasosController.saveCasoModalidade();">Adicionar</a>

                    <br>
                    <br>

                    <table class="table">
                        <thead>
                        <tr>
                            <th>Título</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody id="table-modalidades-body">
                            <tr>
                                <td>CT</td>
                                <td>
                                    <a href="#void" class="btn btn-xs btn-danger" title="Remover"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal das Patologias -->
    <div class="modal fade" id="modal-patologia" tabindex="-1" role="dialog" aria-labelledby="modal-patologia">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span>Patologias</span></h4>
                </div>
                <div class="modal-body">
                    <input id="id-caso-modal-patologia" name="id-caso-modal-patologia" type="hidden">
                    Adicionar Modalidades:
                    <select id="search-patologias" name="search-patologias" class="chzn-select chosen-select" style="width: 100px;">
                        <option value="0">Nenhum registro filtrado.</option>
                    </select>

                    <br>
                    <br>

                    <a href="#void" class="btn btn-info" onclick="UICasosController.savePatologiaCaso();">Adicionar</a>

                    <br>
                    <br>

                    <table class="table">
                        <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody id="table-patologias-body">
                        <tr>
                            <td>CT</td>
                            <td>
                                <a href="#void" class="btn btn-xs btn-danger" title="Remover"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de novo Caso -->
    <div class="modal fade" id="modal-caso" tabindex="-1" role="dialog" aria-labelledby="modal-caso">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span>Novo Caso</span></h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="id-caso-edicao" name="id-caso-edicao">

                    <label for="caso-titulo">Título:</label>
                    <input id="caso-titulo" name="caso-titulo" type="text" class="form-control">

                    <br>
                    <br>

                    <label for="caso-laudo">Laudo:</label>
                    <div id="caso-laudo" name="caso-laudo" contenteditable="true" style="height: 350px; border:1px solid black; overflow-y: auto;" class="form-control"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" onclick="UICasosController.saveOrUpdate();">Salvar</button>
                    <button type="button" class="btn btn-default" onclick="UICasosController.closeModalCaso();">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</body>