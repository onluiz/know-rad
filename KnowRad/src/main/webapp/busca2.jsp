<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">

<html>
<head>
    <meta charset=utf-8 />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui">

    <title>Wine and cheese</title>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">-->
    <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet">

    <script src="http://cdnjs.cloudflare.com/ajax/libs/fastclick/1.0.3/fastclick.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

    <script src="http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.2/handlebars.min.js"></script>

    <script src="http://cytoscape.github.io/cytoscape.js/api/cytoscape.js-latest/cytoscape.min.js"></script>
    <!--<script src="../cytoscape.js/build/cytoscape.js"></script>-->

    <script src="http://cdnjs.cloudflare.com/ajax/libs/qtip2/2.2.0/jquery.qtip.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/qtip2/2.2.0/jquery.qtip.min.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.rawgit.com/cytoscape/cytoscape.js-qtip/2.2.3/cytoscape-qtip.js"></script>

    <script src="http://cdnjs.cloudflare.com/ajax/libs/bluebird/1.2.2/bluebird.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.10.4/typeahead.bundle.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.2/handlebars.min.js"></script>
    <script src="<c:url value='/assets/js/demo.js' />"></script>

    <script>

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

                    "elements" : {
                        "nodes" : [

                            {
                                "data" : {
                                    "id" : "430",
                                    "Strength" : 5,
                                    "selected" : false,
                                    "cytoscape_alias_list" : [ "Aarauer Bierdeckel" ],
                                    "canonicalName" : "Aarauer Bierdeckel",
                                    "Milk" : "Raw cow's milk",
                                    "Synonym" : "Kuentener",
                                    "Quality" : 90,
                                    "Type" : "Semi-soft",
                                    "SUID" : 430,
                                    "NodeType" : "Cheese",
                                    "name" : "Aarauer Bierdeckel",
                                    "Country" : "Switzerland",
                                    "shared_name" : "Aarauer Bierdeckel"
                                },
                                "position" : {
                                    "x" : 4491.9853515625,
                                    "y" : 4520.1904296875
                                },
                                "selected" : false
                            },

                            {
                                "data" : {
                                    "id" : "429",
                                    "selected" : false,
                                    "cytoscape_alias_list" : [ "Bergues" ],
                                    "canonicalName" : "Bergues",
                                    "SUID" : 429,
                                    "NodeType" : "Cheese",
                                    "name" : "Bergues",
                                    "shared_name" : "Bergues"
                                },
                                "position" : {
                                    "x" : 4491.77880859375,
                                    "y" : 4647.23974609375
                                },
                                "selected" : false
                            },

                            {
                                "data" : {
                                    "id" : "426",
                                    "selected" : false,
                                    "cytoscape_alias_list" : [ "Beaujolais" ],
                                    "canonicalName" : "Beaujolais",
                                    "SUID" : 426,
                                    "NodeType" : "RedWine",
                                    "name" : "Beaujolais",
                                    "shared_name" : "Beaujolais"
                                },
                                "position" : {
                                    "x" : 1391.1080322265625,
                                    "y" : 4324.1015625
                                },
                                "selected" : false
                            }

                        ],

                        "edges" : [

                            {
                                "data" : {
                                    "id" : "1763",
                                    "source" : "430",
                                    "target" : "429",
                                    "selected" : false,
                                    "canonicalName" : "Aarauer Bierdeckel (cc) Bergues",
                                    "SUID" : 1763,
                                    "name" : "Aarauer Bierdeckel (cc) Bergues",
                                    "interaction" : "cc",
                                    "shared_interaction" : "cc",
                                    "shared_name" : "Aarauer Bierdeckel (cc) Bergues"
                                },
                                "selected" : false
                            }

                        ]
                    }
                };

                initGraphs(graphP);

            });
        }

    </script>
</head>
<body>
<div id="cy"></div>
<div id="loading">
    <span class="fa fa-refresh fa-spin"></span>
</div>

<div id="search-wrapper">
    <input type="text" class="form-control" id="search" autofocus placeholder="Search">
    <a href="#void" onclick="search2();">BUSCAR</a>
</div>

<div id="info">
</div>


<button id="reset" class="btn btn-default"><i class="fa fa-arrows-h"></i></button>

<button id="filter" class="btn btn-default"><i class="fa fa-filter"></i></button>

<div id="filters">
    <strong>Cheese</strong>
    <input id="soft" type="checkbox" checked></input><label for="soft">Soft</label><br />
    <input id="semi-soft" type="checkbox" checked></input><label for="semi-soft">Semi-soft</label><br />
    <input id="na" type="checkbox" checked></input><label for="na">N/A</label><br />
    <input id="semi-hard" type="checkbox" checked></input><label for="semi-hard">Semi-hard</label><br />
    <input id="hard" type="checkbox" checked></input><label for="hard">Hard</label>

    <hr />

    <strong>Drink</strong>
    <input id="white" type="checkbox" checked></input><label for="white">White wine</label><br />
    <input id="red" type="checkbox" checked></input><label for="red">Red wine</label><br />
    <input id="cider" type="checkbox" checked></input><label for="cider">Cider</label>
</div>

<a target="_blank" id="linkout" href="http://www.amazon.ca/Cheese-Connoisseurs-Guide-Worlds-Best/dp/1400050340/ref=sr_1_3?s=books&ie=UTF8&qid=1416109370&sr=1-3">Reference <i class="fa fa-external-link"></i></a>
</body>
</html>
