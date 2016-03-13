"use strict";

var UIPatologiasController = {

    /**
     * DATATABLES
     */

    initPatologiasDataTable: function() {
        var table_list = $('#table_report').dataTable( {
            "bServerSide":true,
            "sAjaxSource": "listPatologiaAjax",
            "fnServerData": function ( sSource, aoData, fnCallback ) {

                $.getJSON( sSource, aoData, function (json) {
                    fnCallback(json);
                });

            },
            "aoColumns": [
                {
                    "mDataProp": "descricao", "bSortable": false
                },

                {
                    "mDataProp": "",
                    "sDefaultContent": "",
                    "bSortable": false,
                    "fnRender": function (oObj, sVal) {
                        var html = "";
                        html += "<div class=\"btn-group\">";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Editar\" onclick=\"UIPatologiasController.edit('" + oObj.aData.idPatologia + "');\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Patologias\" onclick=\"UICasosController.openModalPatologias('" + oObj.aData.idCaso + "');\"><span class=\"glyphicon glyphicon-book\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-info\" title=\"Modalidades\" onclick=\"UICasosController.openModalModalidades('" + oObj.aData.idCaso + "');\"><span class=\"glyphicon glyphicon-queen\" aria-hidden=\"true\"></span></a>";
                        html += "<a href=\"#void\" class=\"btn btn-xs btn-danger\" title=\"Remover\" onclick=\"UIPatologiasController.remove('" + oObj.aData.idPatologia + "');\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a>";
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
     * TABLES
     */

    updateTablePatologias: function() {
        $('#table_report').dataTable().fnDraw(false);
    },

    /**
     * MODALS
     */

    openModalPatologia: function() {
        $("#id-patologia-edicao").val('');
        $("#patologia-descricao").val('');
        $("#modal-patologia").modal();
    },

    closeModalPatologia: function() {
        $("#id-patologia-edicao").val('');
        $("#patologia-descricao").val('');
        $("#modal-patologia").modal('hide');
    },

    /**
     * SAVES
     */

    saveOrUpdate: function() {

        $("#modal-patologia").modal("hide");

        var patologiaDTO = {
            idPatologia: $("#id-patologia-edicao").val(),
            descricao: $("#patologia-descricao").val()
        };

        var done = function() {
            $("#id-patologia-edicao").val("");
            $("#patologia-descricao").val("");
            UIPatologiasController.updateTablePatologias();
            alert("Operação realizada com sucesso.");
        }

        if(patologiaDTO.idPatologia > 0) {
            PatologiaService.update(patologiaDTO, {

                done: function(data) {
                    done();
                },

                err: function(err) {
                    console.log(err);
                }

            });

            return;
        }

        PatologiaService.save(patologiaDTO, {

            done: function(data) {
                done();
            },

            err: function(err) {
                console.log(err);
            }

        });

    },

    edit: function(idPatologia) {

        PatologiaService.findDTOById(idPatologia, {

            done: function(data) {
                var patologiaDTO = data;
                $("#id-patologia-edicao").val(patologiaDTO.idPatologia);
                $("#patologia-descricao").val(patologiaDTO.descricao);
                $("#modal-patologia").modal();
            },

            fail: function(err) {
                console.log(err);
            }

        });

    },

    remove: function(idPatologia) {
        PatologiaService.remove(idPatologia, {

            done: function(data) {
                UIPatologiasController.updateTablePatologias();
                alert("Operação realizada com sucesso.");
            },

            err: function(err) {
                console.log(err);
            }

        });
    },

    init: function() {
        this.initPatologiasDataTable();
    }

};