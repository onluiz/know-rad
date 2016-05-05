"use strict";

var UIPatologiasController = {

    /**
     * DATATABLE
     **/

    initDataTable: function() {

        var table_list = $('#table_report').dataTable( {
            "bServerSide":true,
            "sAjaxSource": "listAjax",
            "fnServerData": function ( sSource, aoData, fnCallback ) {

                $.getJSON( sSource, aoData, function (json) {
                    fnCallback(json);
                });

            },
            "aoColumns": [
                {
                    "mDataProp": "nome", "bSortable": false
                },

                {
                    "mDataProp": "",
                    "sDefaultContent": "",
                    "bSortable": false,
                    "fnRender": function (oObj, sVal) {
                        var html = "";
                        html += "<div class=\"btn-group\">";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Editar\" onclick=\"UIPatologiasController.edit('" + oObj.aData.id + "');\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
                        //html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Patologias\" onclick=\"UICasosController.openModal('" + oObj.aData.id + "');\"><span class=\"glyphicon glyphicon-book\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\" onclick=\"UIPatologiasController.remove('" + oObj.aData.id + "');\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a>";
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

    edit: function(id) {

        $("#termos").removeAttr("hidden");

        PatologiaService.findPatologiaResponseById(id, {

            done: function(data) {
                var dto = data.patologiaDTO;
                $("#id-edicao").val(dto.id);
                $("#nome-edicao").val(dto.nome);
                UIPatologiasController.appendListTermos(data.listTermoDTO);
                $("#modal-edicao").modal();
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

        $("#modal-edicao").modal("hide");

        var dto = {
            id: $("#id-edicao").val(),
            nome: $("#nome-edicao").val()
        };

        var done = function() {
            $("#id-edicao").val("0");
            $("#nome-edicao").val("");
            UIPatologiasController.updateTable();
        }

        if(dto.id > 0) {
            PatologiaService.update(dto, {

                done: function(data) {
                    done();
                },

                err: function(err) {
                    console.log(err);
                }

            });

            return;
        }

        PatologiaService.save(dto, {

            done: function(data) {
                done();
            },

            err: function(err) {
                console.log(err);
            }

        });

    },

    saveTermo: function() {
        var dto = {
            id: 0,
            nomeTermo: $("#novo-termo").val(),
            idPatologia: $("#id-edicao").val()
        };

        PatologiaService.saveTermo(dto, {
            
            done: function() {
                PatologiaService.findListDTOByIdPatologia($("#id-edicao").val(), {

                    done: function(data) {
                        console.log("termo salvo");
                        $("#novo-termo").val("");
                        UIPatologiasController.appendListTermos(data);
                    },

                    fail: function(err) {
                        console.log(err);
                    }

                });
            },
            
            fail: function (err) {
                console.log("erro ao salvar termo");
                console.log(err);
            }
            
        })
    },

    appendListTermos: function(list) {
        var html = "";
        list.forEach(function(dto) {
            html += "<br><br>";
            html += "<input id=\"termo-" + dto.id + "\" type=\"text\" class=\"form-control\" value=\"" + dto.nomeTermo + "\">";
            html += "<a class=\"btn btn-info btn-xs\" onclick=\"UIPatologiasController.updateTermo(" + dto.id + ");\">Salvar</a>";
            html += "<a class=\"btn btn-danger btn-xs\" onclick=\"UIPatologiasController.removeTermo(" + dto.id + ");\">Remover</a>";
        })

        $("#list-termos").html(html);
    },

    updateTermo: function(id) {

        var dto = {
          id: id,
            nomeTermo: $("#termo-" + id).val()
        };

        PatologiaService.updateTermo(dto, {

            done: function() {
                console.log("termo atualizado");
            },

            fail: function(err) {
                console.log(err);
            }

        });

    },

    removeTermo: function(id) {

        PatologiaService.removeTermo(id, {

            done: function() {
                console.log("termo removido");
                PatologiaService.findListDTOByIdPatologia($("#id-edicao").val(), {

                    done: function(data) {
                        console.log("termo salvo");
                        UIPatologiasController.appendListTermos(data);
                    },

                    fail: function(err) {
                        console.log(err);
                    }

                });
            },

            fail: function(err) {
                console.log(err);
            }

        });

    },

    /**
     *  REMOVES
     */

    remove: function(id) {
        PatologiaService.remove(id, {

            done: function(data) {
                UIPatologiasController.updateTable();
                alert("Operação realizada com sucesso.");
            },

            err: function(err) {
                console.log(err);
            }

        });
    },

    openModal: function(id) {
        $("#termos").attr("hidden", "hidden");
        $("#modal-edicao").modal();
    },

    closeModal: function () {
        $("#id-edicao").val("0");
        $("#nome-edicao").val("");
        $("#modal-edicao").modal('hide');
    },

    updateTable: function() {
        $('#table_report').dataTable().fnDraw(false);
    },

    /**
     * INITS
     */

    initChose: function() {
        $(".chosen-select").chosen();
    },

    //initTable: function() {
    //    $("#table").dataTable();
    //},

    init: function() {
        this.initDataTable();
        //this.initCasosDataTable();
        //this.initChose();
        //this.initSelectModalidades();
        //this.initSelectPatologias();
    }

};