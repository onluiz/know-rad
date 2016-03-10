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
    <script type="text/javascript" src="<c:url value='/assets/js/chosen.jquery.min.js' />"></script>

    <!-- Service -->
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/study/modalidade-service.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/caso-service.js' />"></script>

    <script>
        "use strict";

        var UICasosController = {

            saveCasoModalidade: function() {

                var idCaso = $("#id-caso-modal-modalidade").val();
                var idModalidade = $("#search-modalidades").val();

                CasoService.saveCasoModalidade(idCaso, idModalidade, {
                    done: function(data) {
                        UICasosController.updateTableModalidade(idCaso);
                    },
                    fail: function(err) {
                        console.log(err);
                    }
                });

            },

            initSelectModalidades: function() {

                var slModalidadesChzn = $("#search_modalidades_chosen");
                var slModalidadesInput = $(slModalidadesChzn).find(".chosen-search").find("input");
                var idTimeOut = 0;

                $(slModalidadesInput).on('keyup', function(e) {

                    /**
                     * 13 SE FOR TECLA ENTER NAO FAZ BUSCA
                     * 16 SE FOR TECLA SHIFT NAO FAZ BUSCA
                     * 17 SE FOR TECLA CTRL NAO FAZ BUSCA
                     * 37 SE FOR SETA PARA ESQUERDA NAO FAZ BUSCA
                     * 38 SE FOR SETA PARA CIMA NAO FAZ BUSCA
                     * 39 SE FOR SETA PARA DIREITA NAO FAZ BUSCA
                     * 40 SE FOR SETA PARA BAIXO NAO FAZ BUSCA
                     */
                    if (e.which == 13 || e.which == 16 || e.which == 17  || e.which == 37 ||
                            e.which == 38 || e.which == 39 || e.which == 40)
                        return;

                    $(slModalidadesInput).parent().addClass("chzn-search-netpacs");
                    var searchText = $(this).val();
                    if(searchText.trim().length == 0) return $(slModalidadesInput).parent().removeClass("chzn-search-netpacs");
                    if(idTimeOut > 0) clearTimeout(idTimeOut);

                    idTimeOut = setTimeout(function(){

                        ModalidadeService.search(searchText, 100, {

                            done: function(data) {

                                if(searchText != $(slModalidadesInput).val())
                                    return;

                                var list = data;
                                var slModalidade = $("#search-modalidades");
                                $(slModalidade).empty();

                                list.forEach(function(modalidadeDTO) {
                                    $(slModalidade).append(new Option(modalidadeDTO.modalidade, modalidadeDTO.idModalidade));
                                });

                                if($(slModalidade).html() == "")
                                    $(slModalidade).append(new Option("Nenhum registro filtrado.", 0));

                                $(slModalidade).trigger("chosen:updated");
                                $(slModalidadesInput).val(searchText);
                                $(slModalidade).trigger("change");
                            },

                            fail: function(err) { console.log(err); }

                        });

                    }, 500);

                });

            },

            updateTableModalidade: function(idCaso) {
                var slModalidade = $("#search-modalidades");
                $(slModalidade).empty();
                $(slModalidade).append(new Option("Nenhum registro filtrado.", 0));
                $(slModalidade).trigger("chosen:updated");

                CasoService.findAllByidCaso(idCaso, {

                    done: function(data) {
                        var html = "Nenhuma associação encontrada.";

                        if(data.length > 0)
                            data.forEach(function(modalidadeDTO) {
                                html += "<tr>";
                                html += "<td>" + modalidadeDTO.modalidade + "</td>";
                                html += "<td><a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>";
                                html += "</tr>";
                            });

                        $("#id-caso-modal-modalidade").val(idCaso);
                        $("#table-modalidades-body").html(html);
                        $("#modal-modalidade").modal();
                    },

                    fail: function(err) {
                        console.log(err);
                    }

                });
            },

            editModalidades: function(idCaso) {
                this.updateTableModalidade(idCaso);

                //$("#id-caso-modal-modalidade").val(idCaso);
                //$("#table-modalidades-body").html(html);
                //$("#modal-modalidade").modal();
            },

            save: function() {

                $("#modal-caso").modal("hide");

                var casoDTO = {
                    titulo: $("#caso-titulo").val(),
                    laudo: $("#caso-laudo").html()
                };

                CasoService.save(casoDTO, {

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
                this.initChose();
                this.initSelectModalidades();
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

        <a href="#void" class="btn btn-info" onclick="$('#modal-caso').modal();">Novo Caso</a>

        <br>
        <br>

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
                            <a href="#void" class="btn btn-xs btn-info" title="Modalidades" onclick="UICasosController.editModalidades(${casoDTO.idCaso});"><span class="glyphicon glyphicon-queen" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Palavras-chave"><span class="glyphicon glyphicon-education" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-danger" title="Remover"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                        </td>
                    </tr>
                </c:forEach>
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

    <!-- Modal de novo Caso -->
    <div class="modal fade" id="modal-caso" tabindex="-1" role="dialog" aria-labelledby="modal-caso">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span>Novo Caso</span></h4>
                </div>
                <div class="modal-body">
                    <label for="caso-titulo">Título</label>
                    <input id="caso-titulo" name="caso-titulo" type="text">

                    <br>
                    <br>

                    <label for="caso-laudo">Laudo</label>
                    <div id="caso-laudo" name="caso-laudo" contenteditable="true" style="height: 410px; border:1px solid black;"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" onclick="UICasosController.save();">Salvar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</body>