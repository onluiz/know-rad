<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">

<html>
<head>
    <meta charset=utf-8 />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui">

    <title>busca2</title>
    <link href="<c:url value='/assets/css/font-awesome.min.css' />" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css' />">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">-->
    <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet">
    <link href="<c:url value='/assets/css/jquery.qtip.min.css'/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value='/assets/css/jquery.gritter.css'/>" rel="stylesheet" type="text/css" />
    <style>
        html, body {
            overflow: hidden;
        }
    </style>

    <script src="<c:url value='/assets/js/fastclick.min.js' />"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

    <script src="<c:url value='/assets/js/handlebars.min.js'/>"></script>

    <script src="<c:url value='/assets/js/cytoscape.min.js'/>"></script>
    <!--<script src="../cytoscape.js/build/cytoscape.js"></script>-->

    <script src="<c:url value='/assets/js/jquery.qtip.min.js'/>"></script>

    <script src="<c:url value='/assets/js/cytoscape-qtip.js'/>"></script>

    <script src="<c:url value='/assets/js/bluebird.min.js'/>"></script>
    <script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/assets/js/typeahead.bundle.js'/>"></script>
    <script src="<c:url value='/assets/js/handlebars2.min.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.gritter.min.js'/>"></script>
    <script src="<c:url value='/assets/js/demo.js' />"></script>

    <script>
        var quantitativeData;

        function search2() {
            var search = $("#search").val();
            $.ajax({
                type: "GET",
                url: '<c:url value="/searchLaudos/search" />',
                cache: false,
                data: ({search: search})
            }).done(function (data) {

                var graphP = {
                    format_version: "1.0",
                    generated_by: "cytoscape-3.2.0",
                    target_cytoscapejs_version: "~2.1",
                    data: {
                        selected: true,
                        __Annotations: [],
                        shared_name: "WineCheeseNetwork",
                        SUID: 52,
                        name: "WineCheeseNetwork"

                    },

                    elements : {

                        nodes: [],
                        edges: []

                    }
                };

                var getRandomArbitrary = function(min, max) {
                    return Math.random() * (max - min) + min;
                };

                data.listLaudos.forEach(function(laudoResponse) {

                    laudoResponse.position.x = getRandomArbitrary(6000, 100000);
                    laudoResponse.position.y = getRandomArbitrary(6000, 100000);
//                    laudoResponse.data.NodeType = "Cheese";


                    graphP.elements.nodes.push(laudoResponse);

                });

                var xDoenca = 4000.0;

                data.listDoencas.forEach(function(doencaResponse) {

                    doencaResponse.position.x = getRandomArbitrary(0, 110000);
                    doencaResponse.position.y = getRandomArbitrary(0, 110000);
                    doencaResponse.data.NodeType = "RedWine";
                    doencaResponse.data.NodeTypeFormatted = doencaResponse.canonicalName;
                    doencaResponse.data.Strength = "5";
                    doencaResponse.data.Quality = "100";

                    graphP.elements.nodes.push(doencaResponse);

                });

//                setId(2);
//                setNome("PH");
//                setPalavras(new ArrayList<String>() {{
//                    add("mosaico");
//                    add("consolidações");
//                    add("mosaico e nods CL");
//                    add("aprisionamento lobular");
//                    add("PH crônica");
//                    add("PH");
//                }});
//
//                setSelected(Boolean.FALSE);
//                setCytoscape_alias_list(new String[]{"PH"});
//                setCanonicalName("PH");
//                setSUID("2");
//                setNodeType("RedWine");
//                setName("PH");
//                setShared_name("PH");
//                setNodeTypeFormatted("RedWine");

//                var blueNode = {
//                    id: 10000,
//                    selected: false,
//                    cytoscape_alias_list: ["Obrigado!"],
//                    canonicalName: "Obrigado!",
//                    shared_name: "Obrigado!",
//                    NodeTypeFormatted: "RedWine",
//                    NodeType: "RedWine",
//                    SUID: "10000",
//                    nome: "Obrigado!",
//                    name: "Obrigado!",
//
//                    position: {
//                        x: getRandomArbitrary(0, 110000),
//                        y: getRandomArbitrary(0, 110000)
//                    },
//
//                    data: {
//                        NodeType: "RedWine",
//                        NodeTypeFormatted: "RedWine!",
//                        Strength: "5",
//                        Quality: "100"
//                    }
//                };
//
//                graphP.elements.nodes.push(blueNode);

                data.listEdges.forEach(function(edgeResponse) {

                    graphP.elements.edges.push(edgeResponse);

                });

                quantitativeData = {
                    qtdLaudos: data.listLaudos.length,
                    qtdDoencas: data.listDoencas.length,
                    qtdEdges: data.listEdges.length
                };

                initGraphs(graphP);

            });
        }

        function showQuantitativeData() {

            var text = "Quantidade de Laudos: " + quantitativeData.qtdLaudos + "<br><br>";
            text += "Quantidade de Doenças: " + quantitativeData.qtdDoencas + "<br><br>";
            text += "Quantidade de Relacionamentos: " + quantitativeData.qtdEdges + "<br>";

            $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: 'Dados',
                // (string | mandatory) the text inside the notification
                text: text,
                sticky: true,
                time: '',
                class_name: 'gritter-light'
            });
        }

        function indexar() {

            $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: 'Indexando laudos...',
                // (string | mandatory) the text inside the notification
                text: "Indexando laudos...",
                sticky: true,
                time: '',
                class_name: 'gritter-light'
//                                            class_name: 'gritter-light'
            });

            $.ajax({
                type: "GET",
                url: '<c:url value="/searchLaudos/indexar" />',
                cache: false,
            }).done(function (data) {
                $.gritter.removeAll();

                $.gritter.add({
                    // (string | mandatory) the heading of the notification
                    title: 'Operação Concluída',
                    // (string | mandatory) the text inside the notification
                    text: "Laudos indexados.",
                    time: '3000',
//                                                class_name: 'gritter-light'
                });
            });
        }

        function searchLaudosById(id) {
            $.ajax({
                type: "GET",
                url: '<c:url value="/searchLaudos/search-dto-by-id" />',
                cache: false,
                data: ({id: id})
            }).done(function (data) {

                var html = "";
                html += "<div>";
                html += "<div class=\"bs-callout bs-callout-danger\"> ";
                html += "<b>Paciente: </b> " + data.nomePaciente;
                html += "<br>"
                html += "<b>ID: </b> " + data.id;
                html += "<br>"
                html += "<b>Título: </b> " + data.titulo;
                html += "<br>";
                html += "<b>Modalidade: </b> " + data.modalidade;
                html += "<br>";
                html += "</div>";

                $("#div-dados-paciente").html(html);
                $("#div-laudo").html(data.texto);
                $("#modal-laudo").modal();
            });
        }

        $(function() {
            $.extend($.gritter.options, {
//                position: 'bottom-left', // defaults to 'top-right' but can be 'bottom-left', 'bottom-right', 'top-left', 'top-right' (added in 1.7.1)
                fade_in_speed: 'medium', // how fast notifications fade in (string or int)
                fade_out_speed: 2000, // how fast the notices fade out
                time: 6000 // hang on the screen for...
            });

            search2();
        });

    </script>
</head>
<body>
<div id="cy"></div>
<div id="loading">
    <span class="fa fa-refresh fa-spin"></span>
</div>

<div id="search-wrapper">
    <input type="text" class="form-control" id="search" autofocus placeholder="Search">
</div>

<div id="info">
</div>


<button id="reset" class="btn btn-default"><i class="fa fa-arrows-h"></i></button>

<button id="filter" class="btn btn-default"><i class="fa fa-filter"></i></button>

<div id="filters">
    <%--<strong>Cheese</strong>--%>
    <%--<input id="soft" type="checkbox" checked></input><label for="soft">Soft</label><br />--%>
    <%--<input id="semi-soft" type="checkbox" checked></input><label for="semi-soft">Semi-soft</label><br />--%>
    <%--<input id="na" type="checkbox" checked></input><label for="na">N/A</label><br />--%>
    <%--<input id="semi-hard" type="checkbox" checked></input><label for="semi-hard">Semi-hard</label><br />--%>
    <%--<input id="hard" type="checkbox" checked></input><label for="hard">Hard</label>--%>

    <hr />

    <strong>Relacionamentos</strong>
    <%--<input id="white" type="checkbox" checked></input><label for="white">White wine</label><br />--%>
    <input id="red" type="checkbox" checked></input><label for="red">Doenças</label><br />
    <a class="btn btn-info" href="#void" onclick="showQuantitativeData();">Dados</a>
    <br><br>
    <a class="btn btn-info" href="<c:url value='/doenca/' />">Cadastro</a>
    <br><br>
    <a href="#void" class="btn btn-info" class="btn btn-info" onclick="indexar();">Atualizar Índice</a>
    <%--<input id="cider" type="checkbox" checked></input><label for="cider">Cider</label>--%>
</div>


<div class="modal fade" id="modal-laudo" tabindex="-1" role="dialog" aria-labelledby="modal-laudo">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span>Laudo</span></h4>
            </div>
            <div class="modal-body">
                <div id="div-dados-paciente"></div>
                <br>
                <div id="div-laudo" style="height: 350px; border:1px solid black; overflow-y: auto;" class="form-control"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn" onclick="$('#modal-laudo').modal('hide');">Fechar</button>
            </div>
        </div>
    </div>
</div>
<%--<a target="_blank" id="linkout" href="http://www.amazon.ca/Cheese-Connoisseurs-Guide-Worlds-Best/dp/1400050340/ref=sr_1_3?s=books&ie=UTF8&qid=1416109370&sr=1-3">Reference <i class="fa fa-external-link"></i></a>--%>
</body>
</html>
