<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="description" content="overview & stats" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- CSS -->
        <link rel="stylesheet" href="<c:url value='/assets/bootstrap/css/bootstrap.min.css' />" />
        <link rel="stylesheet" href="<c:url value='/assets/bootstrap/css/bootstrap-theme.min.css' />" />
        <link href="<c:url value='/assets/css/jquery.gritter.css'/>" rel="stylesheet" type="text/css" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value='/assets/js/jquery-2.1.4.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/assets/bootstrap/js/bootstrap.min.js' />"></script>
        <script src="<c:url value='/assets/js/jquery.gritter.min.js'/>"></script>

        <!-- Service -->
        <script type="text/javascript" src="<c:url value='/assets/js/knowrad/abstract-service.js' />"></script>

        <script>
            var URL_BASE = "<c:url value='/' />";
            function indexar() {

                $.gritter.add({
                    // (string | mandatory) the heading of the notification
                    title: 'Indexando laudos...',
                    // (string | mandatory) the text inside the notification
                    text: "Indexando laudos...",
                    sticky: true,
                    time: '',
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
        </script>
        <title>KnowRad</title>
        <decorator:head />
    </head>

    <body>
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <div class="container-fluid" style="padding-right: 0px;">
                <nav class="navbar navbar-inverse navbar-fixed-top">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="<c:url value='/' />">TCC do Luiz</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                            <form class="navbar-form navbar-left">
                                <a class="btn btn-info" href="<c:url value='/' />">Busca</a>
                                <a href="<c:url value='/searchLaudos/busca2' />" class="btn btn-info" class="btn btn-success">Gráfos</a>
                                <a class="btn btn-info" href="<c:url value='/doenca/' />">Cadastro</a>
                                <ahref="#void" class="btn btn-info" class="btn btn-info" onclick="indexar();">Atualizar Índice</a>
                            </form>
                        </div><!--/.navbar-collapse -->
                    </div>
                </nav>
                </div>
            </div>
        </div>

        <div id="main-content" class="clearfix container">
            <div id="page-content" class="clearfix">
                <decorator:body />
            </div>
        </div><!-- #main-content -->

    </body>

</html>