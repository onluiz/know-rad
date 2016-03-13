"use strict";

var UICasosController = {

    /**
     * DATATABLE
     **/

    initCasosDataTable: function() {

        var table_list = $('#table_report').dataTable( {
            "bServerSide":true,
            "sAjaxSource": "listCasoAjax",
            "fnServerData": function ( sSource, aoData, fnCallback ) {

                $.getJSON( sSource, aoData, function (json) {
                    fnCallback(json);
                });

            },
            "aoColumns": [
                {
                    "mDataProp": "titulo", "bSortable": false
                },

                {
                    "mDataProp": "",
                    "sDefaultContent": "",
                    "bSortable": false,
                    "fnRender": function (oObj, sVal) {
                        var html = "";
                        html += "<div class=\"btn-group\">";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Editar\" onclick=\"UICasosController.edit('" + oObj.aData.idCaso + "');\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Patologias\" onclick=\"UICasosController.openModalPatologias('" + oObj.aData.idCaso + "');\"><span class=\"glyphicon glyphicon-book\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Modalidades\" onclick=\"UICasosController.openModalModalidades('" + oObj.aData.idCaso + "');\"><span class=\"glyphicon glyphicon-queen\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\" onclick=\"UICasosController.remove('" + oObj.aData.idCaso + "');\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a>";
                        html += "</div>"
                        return html;
                    }
                }

            ]

        });

        $("#table_report_filter input").unbind();
        $("#table_report_filter input").bind('keyup', function(e) {
            if(e.keyCode == 13) {
                table_list.fnFilter(this.value);
            }
        });

    },

    /**
     * EDITS
     **/

    edit: function(idCaso) {

        CasoService.findDTOById(idCaso, {

            done: function(data) {
                var casoDTO = data;
                $("#id-caso-edicao").val(casoDTO.idCaso);
                $("#caso-titulo").val(casoDTO.titulo);
                $("#caso-laudo").html(casoDTO.laudo);
                $("#modal-caso").modal();
            },

            fail: function(err) {
                console.log(err);
            }

        });

    },

    /**
     * SAVES
     **/

    saveOrUpdate: function() {

        $("#modal-caso").modal("hide");

        var casoDTO = {
            idCaso: $("#id-caso-edicao").val(),
            titulo: $("#caso-titulo").val(),
            laudo: $("#caso-laudo").html()
        };

        var done = function() {
            $("#id-caso-edicao").val("");
            $("#caso-titulo").val("");
            $("#caso-laudo").html("");
            UICasosController.updateTableCasos();
            alert("Operação realizada com sucesso.");
        }

        if(casoDTO.idCaso > 0) {
            CasoService.update(casoDTO, {

                done: function(data) {
                    done();
                },

                err: function(err) {
                    console.log(err);
                }

            });

            return;
        }

        CasoService.save(casoDTO, {

            done: function(data) {
                done();
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

    updateChosenModalidade: function() {
        var slModalidade = $("#search-modalidades");
        $(slModalidade).empty();
        $(slModalidade).append(new Option("Nenhum registro filtrado.", 0));
        $(slModalidade).trigger("chosen:updated");
    },

    updateChosenPatologia: function() {
        var slPatologia = $("#search-patologias");
        $(slPatologia).empty();
        $(slPatologia).append(new Option("Nenhum registro filtrado.", 0));
        $(slPatologia).trigger("chosen:updated");
    },

    /**
     * TABLES
     * */

    updateTableCasos: function() {
        $('#table_report').dataTable().fnDraw(false);
    },

    updateTableModalidade: function(idCaso) {

        CasoService.findAllModalidadesDTOByidCaso(idCaso, {

            done: function(data) {
                var html = "Nenhuma associação encontrada.";

                if(data.length > 0)
                    data.forEach(function(modalidadeDTO) {
                        html += "<tr>";
                        html += "<td>" + modalidadeDTO.modalidade + "</td>";
                        html += "<td><a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\" onclick=\"UICasosController.removeCasoModalidade('" + idCaso + "', '" + modalidadeDTO.idModalidade + "');\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>";
                        html += "</tr>";
                    });

                $("#id-caso-modal-modalidade").val(idCaso);
                $("#table-modalidades-body").html(html);
            },

            fail: function(err) {
                console.log(err);
            }

        });
    },

    updateTablePatologia: function(idCaso) {

        CasoService.findAllPatologiasDTOByidCaso(idCaso, {

            done: function(data) {
                var html = "Nenhuma associação encontrada.";

                if(data.length > 0)
                    data.forEach(function(patologiaDTO) {
                        html += "<tr>";
                        html += "<td>" + patologiaDTO.descricao + "</td>";
                        html += "<td><a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\" onclick=\"UICasosController.removePatologiaCaso('" + idCaso + "', '" + patologiaDTO.idPatologia + "');\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a></td>";
                        html += "</tr>";
                    });

                $("#id-caso-modal-patologia").val(idCaso);
                $("#table-patologias-body").html(html);
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
        this.updateChosenModalidade();
        this.updateTableModalidade(idCaso);
        $("#modal-modalidade").modal();
    },

    openModalPatologias: function(idCaso) {
        this.updateChosenPatologia();
        this.updateTablePatologia(idCaso);
        $("#modal-patologia").modal();
    },

    openModalCaso: function() {
        $("#id-caso-edicao").val('');
        $("#caso-titulo").val('');
        $("#caso-laudo").html('');
        $("#modal-caso").modal();
        $("#modal-caso").modal();
    },

    closeModalCaso: function () {
        $("#id-caso-edicao").val('');
        $("#caso-titulo").val('');
        $("#caso-laudo").html('');
        $("#modal-caso").modal('hide');
    },

    /**
     *  REMOVES
     */

    remove: function(idCaso) {
        CasoService.remove(idCaso, {

            done: function(data) {
                UICasosController.updateTableCasos();
                alert("Operação realizada com sucesso.");
            },

            err: function(err) {
                console.log(err);
            }

        });
    },

    removeCasoModalidade: function(idCaso, idModalidade) {
        CasoService.removeCasoModalidade(idCaso, idModalidade, {

            done: function(data) {
                UICasosController.updateTableModalidade(idCaso);
                alert("Operação realizada com sucesso.");
            },

            err: function(err) {
                console.log(err);
            }

        });
    },

    removePatologiaCaso: function(idCaso, idPatologia) {
        CasoService.removePatologiaCaso(idCaso, idPatologia, {

            done: function(data) {
                UICasosController.updateTablePatologia(idCaso);
                alert("Operação realizada com sucesso.");
            },

            err: function(err) {
                console.log(err);
            }

        });
    },

    /**
     * INITS
     */

    initChose: function() {
        $(".chosen-select").chosen();
    },

    initTable: function() {
        $("#table").dataTable();
    },

    init: function() {
        //this.initTable();
        this.initCasosDataTable();
        this.initChose();
        this.initSelectModalidades();
        this.initSelectPatologias();
    }

};