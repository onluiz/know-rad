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
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/patologia-service.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/study/modalidade-service.js' />"></script>
    <script type="text/javascript" src="<c:url value='/assets/js/knowrad/patologia/caso-service.js' />"></script>

    <script>
        "use strict";

        var UICasosController = {

            /**
             * SAVES
            **/

            save: function() {

                $("#modal-caso").modal("hide");

                var casoDTO = {
                    titulo: $("#caso-titulo").val(),
                    laudo: $("#caso-laudo").html()
                };

                CasoService.save(casoDTO, {

                    done: function(data) {
                        $("#caso-titulo").val("");
                        $("#caso-laudo").html("");
                        alert("Operação realizada com sucesso.");
                    },

                    err: function(err) {
                        console.log(err);
                    }

                });

            },

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

            savePatologiaCaso: function() {

                var idCaso = $("#id-caso-modal-patologia").val();
                var idPatologia = $("#search-patologias").val();

                CasoService.savePatologiaCaso(idCaso, idPatologia, {
                    done: function(data) {
                        UICasosController.updateTablePatologia(idCaso);
                    },
                    fail: function(err) {
                        console.log(err);
                    }
                });

            },

            /**
             * SEARCH
             **/

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

            initSelectPatologias: function() {

                var slPatologiasChzn = $("#search_patologias_chosen");
                var slPatologiasInput = $(slPatologiasChzn).find(".chosen-search").find("input");
                var idTimeOut = 0;

                $(slPatologiasInput).on('keyup', function(e) {

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

                    $(slPatologiasInput).parent().addClass("chzn-search-netpacs");
                    var searchText = $(this).val();
                    if(searchText.trim().length == 0) return $(slPatologiasInput).parent().removeClass("chzn-search-netpacs");
                    if(idTimeOut > 0) clearTimeout(idTimeOut);

                    idTimeOut = setTimeout(function(){

                        PatologiaService.search(searchText, 100, {

                            done: function(data) {

                                if(searchText != $(slPatologiasInput).val())
                                    return;

                                var list = data;
                                var slPatologia = $("#search-patologias");
                                $(slPatologia).empty();

                                list.forEach(function(patologiaDTO) {
                                    $(slPatologia).append(new Option(patologiaDTO.descricao, patologiaDTO.idPatologia));
                                });

                                if($(slPatologia).html() == "")
                                    $(slPatologia).append(new Option("Nenhum registro filtrado.", 0));

                                $(slPatologia).trigger("chosen:updated");
                                $(slPatologiasInput).val(searchText);
                                $(slPatologia).trigger("change");
                            },

                            fail: function(err) { console.log(err); }

                        });

                    }, 500);

                });

            },

            /**
             * TABLES
             * */

            updateTableModalidade: function(idCaso) {
                var slModalidade = $("#search-modalidades");
                $(slModalidade).empty();
                $(slModalidade).append(new Option("Nenhum registro filtrado.", 0));
                $(slModalidade).trigger("chosen:updated");

                CasoService.findAllModalidadesDTOByidCaso(idCaso, {

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

            updateTablePatologia: function(idCaso) {
                var slPatologia = $("#search-patologias");
                $(slPatologia).empty();
                $(slPatologia).append(new Option("Nenhum registro filtrado.", 0));
                $(slPatologia).trigger("chosen:updated");

                CasoService.findAllPatologiasDTOByidCaso(idCaso, {

                    done: function(data) {
                        var html = "Nenhuma associação encontrada.";

                        if(data.length > 0)
                            data.forEach(function(patologiaDTO) {
                                html += "<tr>";
                                html += "<td>" + patologiaDTO.descricao + "</td>";
                                html += "<td><a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>";
                                html += "</tr>";
                            });

                        $("#id-caso-modal-patologia").val(idCaso);
                        $("#table-patologias-body").html(html);
                        $("#modal-patologia").modal();
                    },

                    fail: function(err) {
                        console.log(err);
                    }

                });
            },

            /**
             * MODAIS
             */

            openModalModalidades: function(idCaso) {
                this.updateTableModalidade(idCaso);
            },

            openModalPatologias: function(idCaso) {
                this.updateTablePatologia(idCaso);
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
                this.initSelectPatologias();
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
                <c:forEach items="${listCasosDTO}" var="casoDTO">
                    <tr>
                        <td>${casoDTO.titulo}</td>
                        <td>
                            <a href="#void" class="btn btn-xs btn-info" title="Editar"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Patologias" onclick="UICasosController.openModalPatologias(${casoDTO.idCaso});"><span class="glyphicon glyphicon-book" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Modalidades" onclick="UICasosController.openModalModalidades(${casoDTO.idCaso});"><span class="glyphicon glyphicon-queen" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-info" title="Palavras-chave"><span class="glyphicon glyphicon-education" aria-hidden="true"></span></a>
                            <a href="#void" class="btn btn-xs btn-danger" title="Remover" onclick="UICasosController.remove(${casoDTO.idCaso});"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
                    <label for="caso-titulo">Título:</label>
                    <input id="caso-titulo" name="caso-titulo" type="text" class="form-control">

                    <br>
                    <br>

                    <label for="caso-laudo">Laudo:</label>
                    <div id="caso-laudo" name="caso-laudo" contenteditable="true" style="height: 410px; border:1px solid black; overflow-y: auto;" class="form-control"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" onclick="UICasosController.save();">Salvar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</body>