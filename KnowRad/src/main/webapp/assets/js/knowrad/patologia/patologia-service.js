"use strict"

var PatologiaService = {

    service: new AbstractService("patologia/"),

    search: function(searchText, limit, callback) {

        this.service.doGET("search", ({searchText: searchText, limit: limit}), callback);

    }

};