<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .bs-callout {
        padding: 20px;
        margin: 20px 0;
        border: 1px solid #28a4c9;
        border-left-width: 5px;
        border-radius: 3px;
    }
</style>

<script>
    function searchLaudos() {
        $.ajax({
            type: "GET",
            url: '<c:url value="/searchLaudos/normal-search" />',
            cache: false,
            data: ({search: $("#campo-busca").val()})
        }).done(function (data) {

            var html = "";
            data.forEach(function(dto) {
                html += "<div>";
                html += "<div class=\"bs-callout bs-callout-danger\"> ";
                html += "<h4>" + dto.nomePaciente + "</h4>";
                html += "<b>ID: </b> " + dto.id;
                html += "<br>"
                html += "<b>Título: </b> " + dto.titulo;
                html += "<br>";
                html += "<b>Modalidade: </b> " + dto.modalidade;
                html += "<br>";
                html += "<a href=\"#void\" onclick=\"searchLaudosById('" + dto.id + "');\">ABRIR LAUDO</a>";
                html += "<br>";
                html += "<a href=\"#void\" onclick='openGraphs(" + dto.id + ");' target='_blank'>ABRIR GRÁFO</a>";
                html += "</div>";
            });

            $("#div-resultado").html(html);

        });
    }

    function openGraphs(id) {
        var url = '<c:url value="/searchLaudos/busca2?busca="/>' + id;
        window.open(url, '_blank');
    }

    function searchLaudosById(id) {
        $.ajax({
            type: "GET",
            url: '<c:url value="/searchLaudos/search-by-id" />',
            cache: false,
            data: ({id: id})
        }).done(function (data) {
            $("#div-laudo").html(data);
            $("#modal-laudo").modal();
        });
    }
</script>
<div class="container">
    <h1>Pesquisar no Índice</h1>

    <br>
    <div class="row">
        <div class="col-lg-8">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for..." id="campo-busca">
                <span class="input-group-btn">
                <a class="btn btn-info" type="button" onclick="searchLaudos();">Pesquisar</a>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
    </div>

    <div id="div-resultado"></div>

</div>

<div class="modal fade" id="modal-laudo" tabindex="-1" role="dialog" aria-labelledby="modal-laudo">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span>Laudo</span></h4>
            </div>
            <div class="modal-body">
                <div id="div-laudo" style="height: 350px; border:1px solid black; overflow-y: auto;" class="form-control"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn" onclick="$('#modal-laudo').modal('hide');">Fechar</button>
            </div>
        </div>
    </div>
</div>