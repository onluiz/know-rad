"use strict"

var PatologiaService = {

    service: new AbstractService("patologia/"),

    remove: function(idPatologia, callback) {
        this.service.doGET("remove", ({"idPatologia": idPatologia}), callback);
    },

    /**
     * FINDS
     */

    findDTOById: function(idPatologia, callback) {
        this.service.doGET("findDTOById", ({"idPatologia": idPatologia}), callback);
    },

    /**
     * SAVES AND UPDATES
     */

    save: function(patologiaDTO, callback) {
        this.service.doPOST("save", JSON.stringify(patologiaDTO), callback);
    },

    update: function(patologiaDTO, callback) {
        this.service.doPOST("update", JSON.stringify(patologiaDTO), callback);
    },

    search: function(searchText, limit, callback) {
        this.service.doGET("search", ({searchText: searchText, limit: limit}), callback);
    }

};