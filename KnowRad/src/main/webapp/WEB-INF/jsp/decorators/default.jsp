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

        <!-- JS -->
        <script type="text/javascript" src="<c:url value='/assets/js/jquery-2.1.4.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/assets/bootstrap/js/bootstrap.min.js' />"></script>

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
                            <a class="navbar-brand" href="<c:url value='/' />">KnowRad</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="http://getbootstrap.com/examples/theme/#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Opções <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Principal</li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Busca</a></li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Busca C/ Gráfos</a></li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Dados estátisticos, relatórios, estudar por pentaho aqui</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Cadastros Gerais</li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Cadastro/Lista Patologias</a></li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Cadastro/Lista Casos</a></li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Cadastro/Lista Palavras-chave</a></li>
                                    <li><a href="http://getbootstrap.com/examples/theme/#">Lista de Modalidades</a></li>
                                </ul>
                            </li>
                            </ul>
                            <form class="navbar-form navbar-right">
                                <div class="form-group">
                                    <input type="text" placeholder="buscar..." class="form-control">
                                </div>
                                <button type="submit" class="btn btn-success">Buscar</button>
                            </form>
                        </div><!--/.navbar-collapse -->
                    </div>
                </nav>
                </div>
            </div>
        </div>

        <div id="main-content" class="clearfix">
            <div id="page-content" class="clearfix">
                <decorator:body />
            </div>
        </div><!-- #main-content -->

    </body>

</html>