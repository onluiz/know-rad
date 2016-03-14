<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <h1>Knowledge in Radiological Data</h1>

    <br>

    <a class="btn btn-info" href="<c:url value='/caso/' />">Casos</a>
    <a class="btn btn-info" href="<c:url value='/patologia/' />">Patologias</a>

    <h3>RoadMap</h3>
    <strike><b>1 - </b>Adicionar menu no sistema</strike>
    <br>
    <strike><b>2 - </b>Criar  tela de  cadastro de PatologiaCaso</strike>
        <strike><br>Transformar lista pra ajax</strike>
            <strike><br>fazer outras associações</strike>
                <strike><br>refatorar código da tela</strike>
                    <strike><br>implementar botão de remover</strike>
                        <strike><br>implementar new caso (salvar somente laudo e titulo. Associações podem ser feitas poteriormente).</strike>
    <br>
    <b>3 - </b>Criar tela de cadastro de PatologiaPalavraChave
    <br>
    <b>4 - </b>Criar tela de cadastro de Patologia (nessa mesma tela deve ser possivel de cadastrar Casos e Palavras Chaves)

    <img src="<c:url value='/assets/images/kdt.jpg' />"/>

</div>